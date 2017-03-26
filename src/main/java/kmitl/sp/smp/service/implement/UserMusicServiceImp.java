package kmitl.sp.smp.service.implement;

import kmitl.sp.smp.entity.UserMusic;
import kmitl.sp.smp.repository.UserMusicRepository;
import kmitl.sp.smp.service.UserMusicService;
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
        List<UserMusic> userMusicList = new ArrayList<>();
        userMusicRepository.findAll().forEach(userMusicList::add);
        return userMusicList;
    }

    @Override
    public UserMusic createUserMusic(UserMusic userMusic) {
        return userMusicRepository.save(userMusic);
    }

    @Override
    public UserMusic createUserMusic(Integer userId, String musicId, Integer listenTime) {

        UserMusic userMusic = new UserMusic();
        userMusic.setUserId(userId);
        userMusic.setMusicId(musicId);
        userMusic.setListenTime(listenTime);
        userMusic.setTimestamp(new Date());
        return userMusicRepository.save(userMusic);
    }
}
