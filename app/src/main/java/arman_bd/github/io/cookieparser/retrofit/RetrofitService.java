package arman_bd.github.io.cookieparser.retrofit;

import arman_bd.github.io.cookieparser.common.CommonConstants;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService {

    private final Retrofit retrofit;

    public RetrofitService() {
        Retrofit.Builder builder = new retrofit2.Retrofit.Builder()
                .baseUrl(CommonConstants.API_BASE_URL)
                .client(okHttpClientBuilder())
                .addConverterFactory(GsonConverterFactory.create());
        retrofit = builder.build();
    }

    private OkHttpClient okHttpClientBuilder() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.addInterceptor(new BasicInterceptor());
        return builder.build();
    }

    public retrofit2.Retrofit getClient() {
        return retrofit;
    }

}
