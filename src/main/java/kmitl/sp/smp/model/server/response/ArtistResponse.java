package kmitl.sp.smp.model.server.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Created by Jo on 3/25/2017.
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ArtistResponse {
    @JsonProperty("artist")
    private String artist;
}
