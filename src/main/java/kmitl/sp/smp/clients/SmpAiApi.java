package kmitl.sp.smp.clients;

import kmitl.sp.smp.model.server.response.base.ApiBaseResponse;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

import javax.inject.Inject;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Created by Jo on 4/4/2017.
 */
@Service
public class SmpAiApi {
    private String url;

    private long timeout = 5000;
    private ApiInterface apiInterface;


    interface ApiInterface {
        @GET("api/v1/suggested/{userId}")
        Call<Void> updateSuggestedSongs(@Path("userId") String userId);
    }

    @Inject
    public SmpAiApi(@Value("${baseurl.aiservice}") String smpAiBaseUrl) {
        this.url = smpAiBaseUrl;
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(timeout, TimeUnit.MILLISECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .client(client)
                .addConverterFactory(JacksonConverterFactory.create())
                .build();

        apiInterface = retrofit.create(ApiInterface.class);
    }

    public Void updateSuggestedSongs(String userId) {
        Call<Void> call = apiInterface.updateSuggestedSongs(userId);
        try {
            Response<Void> response = call.execute();
            if (response.isSuccessful()) {
                return response.body();
            } else {
                throw new InternalError(response.errorBody().string());
            }
        } catch (IOException e) {
            throw new InternalError("Cannot update Suggested Music");
        }
    }
}
