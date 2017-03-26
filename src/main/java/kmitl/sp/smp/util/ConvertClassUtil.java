package kmitl.sp.smp.util;

import kmitl.sp.smp.entity.MusicInformation;
import kmitl.sp.smp.model.server.response.SongResponse;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Jo on 3/26/2017.
 */
public class ConvertClassUtil {
    public static SongResponse convertMusicInformationToSongResponse(MusicInformation musicInformation) {
        SongResponse response = new SongResponse();
        response.setSongId(musicInformation.getId());
        response.setName(musicInformation.getName());
        response.setArtist(musicInformation.getArtist());
        response.setStreamingUrl(musicInformation.getUrl());
        return response;
    }

    public static List<SongResponse> convertMusicInformationListToSongResponseList(List<MusicInformation> musicInformationList) {
        return musicInformationList.stream()
                .map(ConvertClassUtil::convertMusicInformationToSongResponse)
                .collect(Collectors.toList());
    }
}
