package client

import client.interceptor.InterceptorClient
import client.src.RUTES
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class Client {
     object ClientRetrofit{
        private lateinit var retrofit : Retrofit
         init{
             lateinit var retrofitClient : Retrofit
                if(!this::retrofit.isInitialized){
                    val interceptor = HttpLoggingInterceptor()
                    interceptor.level = HttpLoggingInterceptor.Level.BODY

                    val clientHttp = OkHttpClient.Builder()
                        .addInterceptor(interceptor)
                        .addInterceptor(InterceptorClient())
                        .connectTimeout(5, TimeUnit.SECONDS)
                        .callTimeout(5, TimeUnit.SECONDS)
                        .readTimeout(5, TimeUnit.SECONDS)
                        .writeTimeout(5, TimeUnit.SECONDS)
                        .build()

                    retrofitClient = Retrofit.Builder()
                        .client(clientHttp)
                        .baseUrl(RUTES.RUTE_BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
                }
             this::retrofit.set(retrofitClient)
        }

         fun getService( ServiceClass: Class<*>): Any{
             return this.retrofit.create(ServiceClass)
         }
    }

    object ClientEmail{
        lateinit var retrofit:Retrofit
        init{
            lateinit var retrofitClient : Retrofit
            if(!this::retrofit.isInitialized){
                val interceptor = HttpLoggingInterceptor()
                interceptor.level = HttpLoggingInterceptor.Level.BODY

                val clientHttp = OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .addInterceptor(InterceptorClient())
                    .connectTimeout(5, TimeUnit.SECONDS)
                    .callTimeout(5, TimeUnit.SECONDS)
                    .readTimeout(5, TimeUnit.SECONDS)
                    .writeTimeout(5, TimeUnit.SECONDS)
                    .build()

                retrofitClient = Retrofit.Builder()
                    .client(clientHttp)
                    .baseUrl(RUTES.RUTE_SERVICE_EMAIL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            this::retrofit.set(retrofitClient)
        }

        fun getService( ServiceClass: Class<*>): Any{
            return this.retrofit.create(ServiceClass)
        }
    }
}