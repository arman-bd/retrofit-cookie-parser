package arman_bd.github.io.cookieparser.retrofit;

import android.util.Log;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.HashSet;

import arman_bd.github.io.cookieparser.common.CommonConstants;
import okhttp3.Request;
import okhttp3.Response;

public class BasicInterceptor implements okhttp3.Interceptor {

    @NotNull
    @Override
    public Response intercept(@NotNull Chain chain) throws IOException {
        Request request = chain.request();


        // Place Authorization Cookie
        if(!CommonConstants.AUTH_COOKIE.equals("")){
            request = request.newBuilder()
                    .addHeader("Cookie", CommonConstants.AUTH_COOKIE)
                    .build();
        }

        // Place Authorization Token
        if(!CommonConstants.AUTH_TOKEN.equals("")){
            request = request.newBuilder()
                    .addHeader("Cookie", CommonConstants.AUTH_COOKIE)
                    .build();
        }

        Response response = chain.proceed(request);

        // Parse CSRF
        if(!response.headers("Set-Cookie").isEmpty()){
            HashSet<String> cookies = new HashSet<>();
            for (String header : response.headers("Set-Cookie")) {
                if(header.contains("XSRF-TOKEN=")){
                    // Found CSRF Token
                    CommonConstants.AUTH_COOKIE = header.replace("XSRF-TOKEN=", "").split(";")[0];
                }
            }
        }

        // Proceed to Response
        return response;
    }

}
