package kmitl.sp.smp.service.implement;

import kmitl.sp.smp.entity.MusicInformation;
import kmitl.sp.smp.repository.MusicInformationRepository;
import kmitl.sp.smp.service.MusicInformationService;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

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
        return getAll();
    }

    @Override
    public MusicInformation getMusicById(String id) {
        return getById(id);
    }

    @Override
    public List<MusicInformation> getRandomMusic(int qty) {
        return getRandom(qty);
    }

    @Override
    public List<MusicInformation> getMusicsByIds(List<String> ids) {
        return getListByIds(ids);
    }

    @Override
    public List<MusicInformation> getMusicsByNameKeyword(String keyword) {
        return getByNameKeyword(keyword);
    }

    @Override
    public List<MusicInformation> getMusicsByArtistKeyword(String keyword) {
        return getByArtistKeyword(keyword);
    }

    private List<MusicInformation> getAll() {
        List<MusicInformation> musicInformationList = new ArrayList<>();
        musicInformationRepository.findAll()
                .forEach(musicInformationList::add);
        return musicInformationList;
    }

    private MusicInformation getById(String id) {
        return musicInformationRepository.findOne(id);
    }

    private List<MusicInformation> getRandom(int qty) {
        return musicInformationRepository.getRandomMusic(qty);
    }

    private List<MusicInformation> getListByIds(List<String> ids) {
        return musicInformationRepository.findByIdIn(ids);
    }

    private List<MusicInformation> getByNameKeyword(String keyword) {
        BiFunction<String, String, List<MusicInformation>> biFunction = musicInformationRepository::getMusicsByNameKeyword;
        return searchByKeyword(biFunction, keyword);
    }

    private List<MusicInformation> getByArtistKeyword(String keyword) {
        BiFunction<String, String, List<MusicInformation>> biFunction = musicInformationRepository::getMusicsByArtistKeyword;
        return searchByKeyword(biFunction, keyword);
    }

    private List<MusicInformation> searchByKeyword(BiFunction<String, String, List<MusicInformation>> biFunction, String keyword) {
        String matchFirst = keyword + "%";
        String matchOthers = "%" + keyword + "%";
        return biFunction.apply(matchFirst, matchOthers);
    }
}
