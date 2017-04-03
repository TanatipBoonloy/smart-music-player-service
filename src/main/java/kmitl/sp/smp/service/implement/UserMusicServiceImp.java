package kmitl.sp.smp.service.implement;

import kmitl.sp.smp.entity.UserMusic;
import kmitl.sp.smp.repository.UserMusicRepository;
import kmitl.sp.smp.service.UserMusicService;
import kmitl.sp.smp.util.ThrowExceptionUtil;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Jo on 3/26/2017.
 */
@Service
public class UserMusicServiceImp implements UserMusicService{
    private final UserMusicRepository userMusicRepository;

    @Inject
    public UserMusicServiceImp(UserMusicRepository userMusicRepository) {
        this.userMusicRepository = userMusicRepository;
    }
    @Override
    public List<UserMusic> getAllUserMusic() {
        return getAll();
    }

    @Override
    public UserMusic createUserMusic(UserMusic userMusic) {
        UserMusic created = create(userMusic);
        return new ThrowExceptionUtil<>(UserMusic.class).checkIfItemIsNull(created);
    }

    @Override
    public UserMusic createUserMusic(Integer userId, String musicId, Integer listenTime) {
        UserMusic created = create(userId,musicId,listenTime);
        return new ThrowExceptionUtil<>(UserMusic.class).checkIfItemIsNull(created);
    }

    private List<UserMusic> getAll() {
        List<UserMusic> userMusicList = new ArrayList<>();
        userMusicRepository.findAll().forEach(userMusicList::add);
        return userMusicList;
    }

    private UserMusic create(UserMusic userMusic){
        return userMusicRepository.save(userMusic);
    }

    private UserMusic create(Integer userId, String musicId, Integer listenTime){
        UserMusic userMusic = new UserMusic();
        userMusic.setUserId(userId);
        userMusic.setMusicId(musicId);
        userMusic.setListenTime(listenTime);
        userMusic.setTimestamp(new Date());
        return userMusicRepository.save(userMusic);
    }
}
