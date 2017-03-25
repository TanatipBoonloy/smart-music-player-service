package kmitl.sp.smp.service.implement;

import kmitl.sp.smp.entity.SuggestedMusic;
import kmitl.sp.smp.repository.SuggestedMusicRepository;
import kmitl.sp.smp.service.SuggestedMusicService;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by Jo on 3/26/2017.
 */
@Service
public class SuggestedMusicServiceImp implements SuggestedMusicService {
    private final SuggestedMusicRepository suggestedMusicRepository;

    @Inject
    public SuggestedMusicServiceImp(SuggestedMusicRepository suggestedMusicRepository) {
        this.suggestedMusicRepository = suggestedMusicRepository;
    }

    @Override
    public List<SuggestedMusic> getLatestSuggestMusicByUserId(int userId, int qty) {
        return suggestedMusicRepository.getLatestSuggestedMusic(userId, qty);
    }
}
