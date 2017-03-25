package kmitl.sp.smp.service;

import kmitl.sp.smp.entity.MusicInformation;

import java.util.List;

/**
 * Created by Jo on 3/26/2017.
 */
public interface MusicInformationService {
    List<MusicInformation> getAllMusic();

    MusicInformation getMusicById(String id);

    List<MusicInformation> getRandomMusic(int qty);
}
