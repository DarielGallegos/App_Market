package client

import client.interceptor.InterceptorClient
import client.src.RUTES
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class Client {
    object ClientRetrofit {
        private val retrofit: Retrofit by lazy {
            val interceptor = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }

            val clientHttp = OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .addInterceptor(InterceptorClient())
                .connectTimeout(30, TimeUnit.SECONDS)
                .callTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .build()

            Retrofit.Builder()
                .client(clientHttp)
                .baseUrl(RUTES.RUTE_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        fun <T> getService(serviceClass: Class<T>): T {
            return retrofit.create(serviceClass)
        }
    }

    object ClientEmail {
        private val retrofit: Retrofit by lazy {
            val interceptor = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }

            val clientHttp = OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .addInterceptor(InterceptorClient())
                .connectTimeout(5, TimeUnit.SECONDS)
                .callTimeout(5, TimeUnit.SECONDS)
                .readTimeout(5, TimeUnit.SECONDS)
                .writeTimeout(5, TimeUnit.SECONDS)
                .build()

            Retrofit.Builder()
                .client(clientHttp)
                .baseUrl(RUTES.RUTE_SERVICE_EMAIL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        fun <T> getService(serviceClass: Class<T>): T {
            return retrofit.create(serviceClass)
        }
    }
}
