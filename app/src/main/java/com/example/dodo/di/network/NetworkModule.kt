package com.example.dodo.di.network

import com.example.dodo.data.features.home.todoadd.remote.api.JusoApi
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Qualifier
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {

    private const val NAVER_BASE_URL = "https://naveropenapi.apigw.ntruss.com"
    private const val JUSO_BASE_URL = "https://business.juso.go.kr"

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class MapRetrofit

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class JusoRetrofit


    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)

        return OkHttpClient
            .Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }

    @Provides
    @Singleton
    @MapRetrofit
    fun provideRetrofitMap(okHttpClient: OkHttpClient, gson: Gson): Retrofit =
        Retrofit.Builder().apply {
            client(okHttpClient)
            baseUrl(NAVER_BASE_URL)
            addConverterFactory(GsonConverterFactory.create(gson))
        }.build()

    @Provides
    @Singleton
    @JusoRetrofit
    fun provideRetrofitJuso(okHttpClient: OkHttpClient, gson: Gson): Retrofit =
        Retrofit.Builder().apply {
            client(okHttpClient)
            baseUrl(JUSO_BASE_URL)
            addConverterFactory(GsonConverterFactory.create(gson))
        }.build()

    @Provides
    @Singleton
    fun provideGsonBuilder(): Gson =
        GsonBuilder()
            .setLenient()
            .create()

    @Provides
    fun provideJusoApi(@JusoRetrofit retrofit: Retrofit) : JusoApi =
        retrofit.create(JusoApi::class.java)
}