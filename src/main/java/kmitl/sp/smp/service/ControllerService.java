package kmitl.sp.smp.service;

import kmitl.sp.smp.model.server.response.ArtistResponse;
import kmitl.sp.smp.model.server.response.SearchSongsResponse;
import kmitl.sp.smp.model.server.response.SongResponse;

import java.util.List;

/**
 * Created by Jo on 3/25/2017.
 */
public interface ControllerService {
    List<ArtistResponse> getAllArtistName();

    SongResponse getSongById(String id);

    List<SongResponse> getAllSong();

    List<SongResponse> getSongsByIds(List<String> ids);

    List<SongResponse> getRandomSongs(int qty);

    List<SongResponse> getSuggestedMusicByUser(int userId, int qty);

    SearchSongsResponse searchSongsByKeyword(String keyword);
}
