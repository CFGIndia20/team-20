package com.example.umeed.data.network;

import com.example.umeed.data.PrefManager;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * User: Aman
 * Date: 29-12-2019
 * Time: 01:45 PM
 */
public class RetrofitService {
    private static final String BASE_URL = "http://96275e0db8c3.ngrok.io/";
    private static String TOKEN;
    private static Retrofit retrofit;

    public static String getTOKEN() {
        return TOKEN;
    }

//    public static class NullOnEmptyConverterFactory extends Converter.Factory {
//
//        @Override
//        public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
//            final Converter<ResponseBody, ?> delegate = retrofit.nextResponseBodyConverter(this, type, annotations);
//            return new Converter<ResponseBody, Object>() {
//                @Override
//                public Object convert(ResponseBody body) throws IOException {
//                    if (body.contentLength() == 0) return null;
//                    return delegate.convert(body);
//                }
//            };
//        }
//    }

    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
//            TOKEN = PrefManager.getInstance().getDeviceToken();
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.level(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client = new OkHttpClient.Builder().addInterceptor(chain -> {
                Request newRequest = chain.request();
//                List<String> custom = newRequest.headers().values("@");
//                Log.e("headers", "getRetrofitInstance: " + custom.size());
//                if (custom.size() > 0) {
//                    Log.e("headers", "getRetrofitInstance: inside if");
//
//                    newRequest = newRequest.newBuilder().removeHeader("@").build();
//                } else {
                newRequest = newRequest.newBuilder()
//                        .header("Authorization", "Bearer " + TOKEN)
                        .build();
//                }

                return chain.proceed(newRequest);
            })
                    .connectTimeout(30, TimeUnit.SECONDS)
                    .writeTimeout(30, TimeUnit.SECONDS)
                    .readTimeout(30, TimeUnit.SECONDS)
                    .addInterceptor(interceptor)
                    .build();
            Gson gson = new GsonBuilder()
                    .serializeNulls()
                    .setLenient()
                    .create();
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
//                    .addConverterFactory(new NullOnEmptyConverterFactory())
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(client)
                    .build();
        }
        return retrofit;
    }

}
