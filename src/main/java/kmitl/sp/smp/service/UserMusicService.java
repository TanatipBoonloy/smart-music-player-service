package kmitl.sp.smp.service;


import kmitl.sp.smp.entity.UserMusic;

import java.util.List;

/**
 * Created by Jo on 3/26/2017.
 */
public interface UserMusicService {
    List<UserMusic> getAllUserMusic();

    UserMusic createUserMusic(UserMusic userMusic);

    UserMusic createUserMusic(Integer userId, String musicId, Integer listenTime);
}
