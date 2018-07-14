package com.mayi.data.api;

import com.mayi.data.utils.EntityUtils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Singleton;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.protobuf.ProtoConverterFactory;

@Singleton
public class ApiClient {
    private static final int DEFAULT_TIME_OUT = 30;
    private static final int DEFAULT_READ_TIME_OUT = 30;

    @Inject
    ApiClient() {

    }

    public ApiService getApiService() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS);//连接超时时间
        builder.writeTimeout(DEFAULT_READ_TIME_OUT,TimeUnit.SECONDS);//写操作 超时时间
        builder.readTimeout(DEFAULT_READ_TIME_OUT,TimeUnit.SECONDS);//读操作超时时间
        builder.retryOnConnectionFailure(true);
        builder.addInterceptor(chain -> {
            Request origin = chain.request();
            Request request = origin.newBuilder()
                    .header("User-Agent","Android")
                    .build();
            return chain.proceed(request);
        });

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Apis.BASE_URL)
                .addConverterFactory(ProtoConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(EntityUtils.gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(builder.build())
                .build();
        ApiService apiService = retrofit.create(ApiService.class);
        return apiService;
    }
}
