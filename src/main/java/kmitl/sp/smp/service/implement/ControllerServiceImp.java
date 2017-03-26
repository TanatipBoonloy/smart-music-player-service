package kmitl.sp.smp.service.implement;

import kmitl.sp.smp.entity.MusicInformation;
import kmitl.sp.smp.model.server.request.LearnDataRequest;
import kmitl.sp.smp.model.server.response.ArtistResponse;
import kmitl.sp.smp.model.server.response.SearchSongsResponse;
import kmitl.sp.smp.model.server.response.SongResponse;
import kmitl.sp.smp.service.*;
import kmitl.sp.smp.util.ConvertClassUtil;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
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

    @Inject
    public ControllerServiceImp(ArtistService artistService, MusicInformationService musicInformationService,
                                SuggestedMusicService suggestedMusicService, UserMusicService userMusicService) {
        this.artistService = artistService;
        this.musicInformationService = musicInformationService;
        this.suggestedMusicService = suggestedMusicService;
        this.userMusicService = userMusicService;
    }

    public List<ArtistResponse> getAllArtistName() {
        return artistService.getAllArtist()
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
        return ConvertClassUtil.
                convertMusicInformationListToSongResponseList(musicInformationService.getRandomMusic(qty));
    }

    @Override
    public List<SongResponse> getSuggestedMusicByUser(int userId, int qty) {
        return suggestedMusicService.getLatestSuggestMusicByUserId(userId, qty)
                .stream()
                .map(suggestedMusic -> {
                    MusicInformation musicInformation = musicInformationService.getMusicById(suggestedMusic.getMusicId());
                    return ConvertClassUtil.convertMusicInformationToSongResponse(musicInformation);
                })
                .collect(Collectors.toList());
    }

    @Override
    public SongResponse getSongById(String id) {
        return ConvertClassUtil.convertMusicInformationToSongResponse(musicInformationService.getMusicById(id));
    }

    @Override
    public List<SongResponse> getAllSong() {
        return ConvertClassUtil.
                convertMusicInformationListToSongResponseList(musicInformationService.getAllMusic());
    }

    @Override
    public List<SongResponse> getSongsByIds(List<String> ids) {
        return ConvertClassUtil.
                convertMusicInformationListToSongResponseList(musicInformationService.getMusicsByIds(ids));
    }

    @Override
    public SearchSongsResponse searchSongsByKeyword(String keyword) {
        List<SongResponse> byArtist = ConvertClassUtil.
                convertMusicInformationListToSongResponseList(musicInformationService.getMusicsByArtistKeyword(keyword));

        List<SongResponse> byName = ConvertClassUtil.
                convertMusicInformationListToSongResponseList(musicInformationService.getMusicsByNameKeyword(keyword));

        SearchSongsResponse searchSongsResponse = new SearchSongsResponse();
        searchSongsResponse.setByName(byName);
        searchSongsResponse.setByArtist(byArtist);
        return searchSongsResponse;
    }

    @Override
    public Boolean learnData(Integer userId, LearnDataRequest request) {
        request.getMusicListenedList()
                .forEach(musicListened ->
                        userMusicService.createUserMusic(userId, musicListened.getSongId(), musicListened.getListenTime())
                );
        return true;
    }
}
