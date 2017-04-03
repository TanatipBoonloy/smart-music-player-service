package kmitl.sp.smp.model.server.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Created by Jo on 4/3/2017.
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LoginWithFacebookRequest {
    @JsonProperty("facebook_id")
    private String facebookId;
}
