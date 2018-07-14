package am.ith.myapplication;


import android.app.Application;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import am.ith.myapplication.webservice.Service;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AppApplication extends Application {

    public static AppApplication appApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        appApplication =  this;
    }

    public Service getNetworkService() {
        return initRetrofit(AppConstants.BASE_URL).create(Service.class);
    }

    private Retrofit initRetrofit(String baseUrl) {

        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(addCache())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }



    private OkHttpClient addCache(){
        int cacheSize = 10 * 1024 * 1024; // 10 MB

        // Create Cache
        Cache cache = null;
        try {
            cache = new Cache(new File(getCacheDir(), "http"),cacheSize );
        } catch (Exception e) {
            e.getMessage();
        }

        // Create OkHttpClient and add Time Out
        OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder()
                .cache(cache)
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS);


        // Add Cache-Control Interceptor
        okHttpClient.networkInterceptors().add(mCacheControlInterceptor);
        return okHttpClient.build();

    }
    private static final Interceptor mCacheControlInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();

            // Add Cache Control only for GET methods
            if (request.method().equals("GET")) {
                request.newBuilder()
                        .header("Cache-Control", "only-if-cached")
                        .build();
            }

            Response response = chain.proceed(request);

            return response.newBuilder()
                    .header("Cache-Control", "public, max-age=86400")
                    .build();
        }
    };
}
