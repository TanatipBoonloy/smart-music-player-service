package kmitl.sp.smp.model.server.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * Created by Jo on 3/26/2017.
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LearnDataRequest {
    @JsonProperty("musics_listened")
    private List<MusicListened> musicListenedList;
}
