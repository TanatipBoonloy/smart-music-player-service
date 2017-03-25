package kmitl.sp.smp.service;

import kmitl.sp.smp.model.server.response.ArtistResponse;

import java.util.List;

/**
 * Created by Jo on 3/25/2017.
 */
public interface ControllerService {
    List<ArtistResponse> getAllArtistName();
}
