package kmitl.sp.smp.service.implement;

import kmitl.sp.smp.entity.MusicInformation;
import kmitl.sp.smp.model.server.response.ArtistResponse;
import kmitl.sp.smp.model.server.response.SongResponse;
import kmitl.sp.smp.service.ArtistService;
import kmitl.sp.smp.service.ControllerService;
import kmitl.sp.smp.service.MusicInformationService;
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

    @Inject
    public ControllerServiceImp(ArtistService artistService, MusicInformationService musicInformationService) {
        this.artistService = artistService;
        this.musicInformationService = musicInformationService;
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
                .map(musicInformation -> {
                    SongResponse response = new SongResponse();
                    response.setSongId(musicInformation.getId());
                    response.setName(musicInformation.getName());
                    response.setArtist(musicInformation.getArtist());
                    response.setStreamingUrl(musicInformation.getUrl());
                    return response;
                })
                .collect(Collectors.toList());
    }
}
