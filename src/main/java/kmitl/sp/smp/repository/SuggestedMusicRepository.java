package kmitl.sp.smp.repository;

import kmitl.sp.smp.entity.SuggestedMusic;
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
public interface SuggestedMusicRepository extends CrudRepository<SuggestedMusic, Integer> {
    @Query(value = "SELECT * FROM suggested_music WHERE user_id = ?1 ORDER BY `timestamp` DESC LIMIT ?2 ",
            nativeQuery = true)
    List<SuggestedMusic> getLatestSuggestedMusic(int userId, int qty);
}
