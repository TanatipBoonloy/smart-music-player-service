package kmitl.sp.smp.repository;

import kmitl.sp.smp.entity.MusicInformation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.inject.Singleton;
import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;

/**
 * Created by Jo on 3/26/2017.
 */
@Singleton
@Transactional
public interface MusicInformationRepository extends CrudRepository<MusicInformation, String> {
    @Query(value = "SELECT * FROM music_information ORDER BY rand() LIMIT ?1", nativeQuery = true)
    List<MusicInformation> getRandomMusic(int qty);

    @Query(value = "SELECT * FROM music_information WHERE music_information.artist LIKE ?1 " +
            "UNION SELECT * FROM music_information WHERE music_information.artist LIKE ?2 ", nativeQuery = true)
    List<MusicInformation> getMusicsByArtistKeyword(String matchFirst, String matchOthers);

    @Query(value = "SELECT * FROM music_information WHERE music_information.name LIKE ?1 " +
            "UNION SELECT * FROM music_information WHERE music_information.name LIKE ?2 ", nativeQuery = true)
    List<MusicInformation> getMusicsByNameKeyword(String matchFirst, String matchOthers);

    List<MusicInformation> findByIdIn(Collection<String> ids);
}
