package kmitl.sp.smp.service.implement;

import kmitl.sp.smp.entity.Artist;
import kmitl.sp.smp.repository.ArtistRepository;
import kmitl.sp.smp.service.ArtistService;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Jo on 3/24/2017.
 */
@Service
public class ArtistServiceImp implements ArtistService {
    private final ArtistRepository artistRepository;

    @Inject
    public ArtistServiceImp(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    public List<Artist> getAllArtist() {
        Iterable<Artist> artistIterator = artistRepository.findAll();
        List<Artist> artistNameList = new ArrayList<>();
        artistIterator.forEach(artistNameList::add);
        return artistNameList;
    }
}
