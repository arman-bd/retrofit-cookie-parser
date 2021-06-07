package arman_bd.github.io.cookieparser.retrofit;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface IRequests {

    @GET("/sanctum/csrf-cookie")
    Call<ResponseBody> getCSRF();

    @FormUrlEncoded
    @POST("/api/login")
    Call<ResponseBody> accountLogin(@Field("email") String email, @Field("password") String password);

}
