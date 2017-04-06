package kmitl.sp.smp.model.server.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Created by Jo on 3/24/2017.
 */
@Data
public class SongResponse {
    @JsonProperty("song_id")
    private String songId;

    @JsonProperty("name")
    private String name;

    @JsonProperty("artist")
    private String artist;

    @JsonProperty("duration")
    private Integer duration;

    @JsonProperty("streaming_url")
    private String streamingUrl;

    @JsonProperty("thumbnail_url")
    private String thumbnailUrl;
}
