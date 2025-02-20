package com.example.kdassistant.server

// hilt
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier
// okhttp
import okhttp3.OkHttpClient
// retrofit
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class AuthInterceptorOkhttpClient

@Module
@InstallIn(SingletonComponent::class)
object InterceptorModule {

    @AuthInterceptorOkhttpClient
    @Provides
    fun provideAuthInterceptorOkHttpClient(
        authInterceptor: AuthInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .build()
    }
}

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModel {
    private const val protocol = "http"
    private const val ip = "10.255.6.53"
    private const val port = "8000"
    private const val version = "v1"
    private const val BASE_URL = "${protocol}://${ip}:${port}/${version}/"

    @Provides
    fun provideServerApi(
        @AuthInterceptorOkhttpClient client: OkHttpClient
    ): ApiServer {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiServer::class.java)
    }

//    val okHttpClient = OkHttpClient.Builder()
//        .addInterceptor(AuthInterceptor(MainActivity.appContext))
//        .build()
//
//    val api: ApiServer by lazy {
//    }
}
