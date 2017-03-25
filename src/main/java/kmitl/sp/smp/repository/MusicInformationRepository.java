package kmitl.sp.smp.repository;

import kmitl.sp.smp.entity.MusicInformation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.inject.Singleton;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Jo on 3/26/2017.
 */
@Singleton
@Transactional
public interface MusicInformationRepository extends CrudRepository<MusicInformation, String> {
    @Query(value = "SELECT * FROM music_information ORDER BY rand() LIMIT ?1", nativeQuery = true)
    List<MusicInformation> getRandomMusic(int qty);
}
