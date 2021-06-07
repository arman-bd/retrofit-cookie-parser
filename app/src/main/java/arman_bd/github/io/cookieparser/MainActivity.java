package arman_bd.github.io.cookieparser;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import arman_bd.github.io.cookieparser.common.CommonConstants;
import arman_bd.github.io.cookieparser.retrofit.IRequests;
import arman_bd.github.io.cookieparser.retrofit.RetrofitService;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    IRequests requests;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize Retrofit [ Use DI if possible ]
        requests = new RetrofitService().getClient().create(IRequests.class);

        findViewById(R.id.btnRequest).setOnClickListener(v -> {
            // Get CSRF as Pre-Request
            requests.getCSRF().enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                    ((TextView) findViewById(R.id.csrfToken)).setText(CommonConstants.AUTH_COOKIE);

                    // Login
                    requests.accountLogin("user@example.com", "123456").enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            // Login Response [ If Any ]
                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {

                        }
                    });

                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {

                }
            });
        });




    }
}