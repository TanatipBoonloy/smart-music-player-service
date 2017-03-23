package kmitl.sp.smp.repository;

import kmitl.sp.smp.entity.Artist;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

/**
 * Created by Jo on 3/24/2017.
 */
@Transactional
public interface ArtistRepository extends CrudRepository<Artist, String> {
}
