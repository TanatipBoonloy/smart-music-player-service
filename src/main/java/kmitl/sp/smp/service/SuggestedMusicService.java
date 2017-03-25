package kmitl.sp.smp.service;

import kmitl.sp.smp.entity.SuggestedMusic;

import java.util.List;

/**
 * Created by Jo on 3/26/2017.
 */
public interface SuggestedMusicService {
    List<SuggestedMusic> getLatestSuggestMusicByUserId(int userId, int qty);
}