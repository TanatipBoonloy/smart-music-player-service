package kmitl.sp.smp.service.implement;

import kmitl.sp.smp.entity.Artist;
import kmitl.sp.smp.model.server.response.ArtistResponse;
import kmitl.sp.smp.service.ArtistService;
import kmitl.sp.smp.service.ControllerService;
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

    @Inject
    public ControllerServiceImp(ArtistService artistService) {
        this.artistService = artistService;
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
}
