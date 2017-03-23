package kmitl.sp.smp.service;

import kmitl.sp.smp.entity.Artist;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Jo on 3/24/2017.
 */
@Service
public class ControllerService {
    private final ArtistService artistService;

    @Inject
    public ControllerService(ArtistService artistService) {
        this.artistService = artistService;
    }

    public List<String> getAllArtistName() {
        return artistService.getAllArtist()
                .stream()
                .map(Artist::getArtist)
                .collect(Collectors.toList());
    }

}
