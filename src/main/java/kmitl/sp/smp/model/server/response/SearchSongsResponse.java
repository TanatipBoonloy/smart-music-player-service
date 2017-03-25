package kmitl.sp.smp.model.server.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * Created by Jo on 3/26/2017.
 */
@Data
public class SearchSongsResponse {
    @JsonProperty("by_name")
    private List<SongResponse> byName;

    @JsonProperty("by_artists")
    private List<SongResponse> byArtist;
}
