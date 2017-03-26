package kmitl.sp.smp.model.server.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Created by Jo on 3/26/2017.
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MusicListened {
    @JsonProperty("song_id")
    private String songId;

    @JsonProperty("listen_time")
    private Integer listenTime;
}
