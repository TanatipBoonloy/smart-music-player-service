package kmitl.sp.smp.util;

import kmitl.sp.smp.entity.MusicInformation;
import kmitl.sp.smp.model.server.response.SongResponse;

/**
 * Created by Jo on 3/26/2017.
 */
public class ConvertClassUtil {
    public static SongResponse convertMusicInformationToSongResponse(MusicInformation musicInformation){
        SongResponse response = new SongResponse();
        response.setSongId(musicInformation.getId());
        response.setName(musicInformation.getName());
        response.setArtist(musicInformation.getArtist());
        response.setStreamingUrl(musicInformation.getUrl());
        return response;
    }
}
