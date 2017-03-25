package kmitl.sp.smp.service.implement;

import kmitl.sp.smp.entity.MusicInformation;
import kmitl.sp.smp.model.server.response.ArtistResponse;
import kmitl.sp.smp.model.server.response.SongResponse;
import kmitl.sp.smp.service.ArtistService;
import kmitl.sp.smp.service.ControllerService;
import kmitl.sp.smp.service.MusicInformationService;
import kmitl.sp.smp.service.SuggestedMusicService;
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

    @Inject
    public ControllerServiceImp(ArtistService artistService, MusicInformationService musicInformationService,
                                SuggestedMusicService suggestedMusicService) {
        this.artistService = artistService;
        this.musicInformationService = musicInformationService;
        this.suggestedMusicService = suggestedMusicService;
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
        List<MusicInformation> musicInformationList = musicInformationService.getRandomMusic(qty);
        return musicInformationList.stream()
                .map(ConvertClassUtil::convertMusicInformationToSongResponse)
                .collect(Collectors.toList());
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
        return musicInformationService.getAllMusic()
                .stream()
                .map(ConvertClassUtil::convertMusicInformationToSongResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<SongResponse> getSongsByIds(List<String> ids) {
        return musicInformationService.getMusicsByIds(ids)
                .stream()
                .map(ConvertClassUtil::convertMusicInformationToSongResponse)
                .collect(Collectors.toList());
    }
}
