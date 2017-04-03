package kmitl.sp.smp.service.implement;

import kmitl.sp.smp.entity.Artist;
import kmitl.sp.smp.entity.MusicInformation;
import kmitl.sp.smp.entity.SuggestedMusic;
import kmitl.sp.smp.entity.User;
import kmitl.sp.smp.exception.ResourceNotFoundException;
import kmitl.sp.smp.model.server.request.LearnDataRequest;
import kmitl.sp.smp.model.server.request.LoginWithFacebookRequest;
import kmitl.sp.smp.model.server.response.ArtistResponse;
import kmitl.sp.smp.model.server.response.UserResponse;
import kmitl.sp.smp.model.server.response.SearchSongsResponse;
import kmitl.sp.smp.model.server.response.SongResponse;
import kmitl.sp.smp.service.*;
import kmitl.sp.smp.util.ConvertClassUtil;
import kmitl.sp.smp.util.ThrowExceptionUtil;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Jo on 3/24/2017.
 */
@Service
public class ControllerServiceImp implements ControllerService {
    private final ArtistService artistService;
    private final MusicInformationService musicInformationService;
    private final SuggestedMusicService suggestedMusicService;
    private final UserMusicService userMusicService;
    private final UserService userService;

    @Inject
    public ControllerServiceImp(ArtistService artistService, MusicInformationService musicInformationService,
                                SuggestedMusicService suggestedMusicService, UserMusicService userMusicService,
                                UserService userService) {
        this.artistService = artistService;
        this.musicInformationService = musicInformationService;
        this.suggestedMusicService = suggestedMusicService;
        this.userMusicService = userMusicService;
        this.userService = userService;
    }

    public List<ArtistResponse> getAllArtistName() {
        return new ThrowExceptionUtil<>(Artist.class).checkIfListIsNull(artistService.getAllArtist())
                .stream()
                .map(artist -> {
                    ArtistResponse response = new ArtistResponse();
                    response.setArtist(artist.getArtist());
                    return response;
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<SongResponse> getRandomSongs(int qty) {
        return ConvertClassUtil.convertMusicInformationListToSongResponseList(
                new ThrowExceptionUtil<>(MusicInformation.class).checkIfListIsNull(musicInformationService.getRandomMusic(qty))
        );
    }

    @Override
    public List<SongResponse> getSuggestedMusicByUser(String userId, int qty) {
        int userFK = getUserFKFromUserId(userId);
        return new ThrowExceptionUtil<>(SuggestedMusic.class).checkIfListIsNull(suggestedMusicService.getLatestSuggestMusicByUserId(userFK, qty))
                .stream()
                .map(suggestedMusic -> {
                    MusicInformation musicInformation = musicInformationService.getMusicById(suggestedMusic.getMusicId());
                    return ConvertClassUtil.convertMusicInformationToSongResponse(musicInformation);
                })
                .collect(Collectors.toList());
    }

    @Override
    public SongResponse getSongById(String id) {
        return ConvertClassUtil.convertMusicInformationToSongResponse(
                new ThrowExceptionUtil<>(MusicInformation.class).checkIfItemIsNull(musicInformationService.getMusicById(id))
        );
    }

    @Override
    public List<SongResponse> getAllSong() {
        return ConvertClassUtil.convertMusicInformationListToSongResponseList(
                new ThrowExceptionUtil<>(MusicInformation.class).checkIfListIsNull(musicInformationService.getAllMusic())
        );
    }

    @Override
    public List<SongResponse> getSongsByIds(List<String> ids) {
        return ConvertClassUtil.convertMusicInformationListToSongResponseList(
                new ThrowExceptionUtil<>(MusicInformation.class).checkIfListIsNull(musicInformationService.getMusicsByIds(ids))
        );
    }

    @Override
    public SearchSongsResponse searchSongsByKeyword(String keyword) {
        List<SongResponse> byArtist = ConvertClassUtil.
                convertMusicInformationListToSongResponseList(musicInformationService.getMusicsByArtistKeyword(keyword));

        List<SongResponse> byName = ConvertClassUtil.
                convertMusicInformationListToSongResponseList(musicInformationService.getMusicsByNameKeyword(keyword));

        if (byArtist.size() < 1 && byName.size() < 1) {
            throw new ResourceNotFoundException("'" + keyword + "' Not Found");
        }

        SearchSongsResponse searchSongsResponse = new SearchSongsResponse();
        searchSongsResponse.setByName(byName);
        searchSongsResponse.setByArtist(byArtist);
        return searchSongsResponse;
    }

    @Override
    public Boolean learnData(String userId, LearnDataRequest request) {
        int userFK = getUserFKFromUserId(userId);
        request.getMusicListenedList()
                .forEach(musicListened ->
                        userMusicService.createUserMusic(userFK, musicListened.getSongId(), musicListened.getListenTime())
                );
        return true;
    }

    @Override
    public UserResponse getNewUserId() {
        User user = userService.createNewUser();
        new ThrowExceptionUtil<>(User.class).checkIfItemIsNull(user);
        UserResponse userResponse = new UserResponse();
        userResponse.setUserId(user.getUserId());
        return userResponse;
    }

    @Override
    public UserResponse loginWithFacebook(LoginWithFacebookRequest request) {
        String facebookId = request.getFacebookId();
        User user = userService.getUserByFacebookId(facebookId);

        if (user != null) {
            return ConvertClassUtil.convertUserToUserResponse(user);
        } else {
            User newUser = userService.createNewUser(facebookId);
            return ConvertClassUtil.convertUserToUserResponse(new ThrowExceptionUtil<>(User.class).checkIfItemIsNull(newUser));
        }
    }

    private Integer getUserFKFromUserId(String userId) {
        User user = userService.getUserByUserId(userId);
        new ThrowExceptionUtil<>(User.class).checkIfItemIsNull(user);
        return user.getId();
    }
}
