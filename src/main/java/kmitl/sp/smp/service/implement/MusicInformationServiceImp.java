package kmitl.sp.smp.service.implement;

import kmitl.sp.smp.entity.MusicInformation;
import kmitl.sp.smp.repository.MusicInformationRepository;
import kmitl.sp.smp.service.MusicInformationService;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jo on 3/26/2017.
 */
@Service
public class MusicInformationServiceImp implements MusicInformationService {
    private final MusicInformationRepository musicInformationRepository;

    @Inject
    public MusicInformationServiceImp(MusicInformationRepository musicInformationRepository) {
        this.musicInformationRepository = musicInformationRepository;
    }

    @Override
    public List<MusicInformation> getAllMusic() {
        List<MusicInformation> musicInformationList = new ArrayList<>();
        musicInformationRepository.findAll()
                .forEach(musicInformationList::add);
        return musicInformationList;
    }

    @Override
    public MusicInformation getMusicById(String id) {
        return musicInformationRepository.findOne(id);
    }

    @Override
    public List<MusicInformation> getRandomMusic(int qty) {
        return musicInformationRepository.getRandomMusic(qty);
    }

    @Override
    public List<MusicInformation> getMusicsByIds(List<String> ids) {
        return musicInformationRepository.findByIdIn(ids);
    }
}
