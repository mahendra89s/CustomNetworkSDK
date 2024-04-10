package com.example.networksdk.data.di

import com.example.networksdk.data.MovieNetworkSDK
import com.example.networksdk.data.datasource.MoviesDataSource
import com.example.networksdk.data.datasource.MoviesDataSourceImpl
import com.example.networksdk.data.interceptor.ApiKeyInterceptor
import com.example.networksdk.data.repository.MoviesRepository
import com.example.networksdk.data.repository.MoviesRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {

    @Provides
    @Singleton
    fun provideMovieDataSource(
        retrofit: Retrofit
    ): MoviesDataSource = MoviesDataSourceImpl(retrofit)

    @Provides
    @Singleton
    fun provideMovieRepository(
        moviesDataSource: MoviesDataSource
    ): MoviesRepository = MoviesRepositoryImpl(moviesDataSource)


    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient
    ): Retrofit = Retrofit
        .Builder()
        .baseUrl("https://api.themoviedb.org/3/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

    @Provides
    @Singleton
    fun provideClient(
        apiKeyInterceptor: ApiKeyInterceptor
    ): OkHttpClient = OkHttpClient
        .Builder()
        .addInterceptor(
            HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
        )
        .addInterceptor(apiKeyInterceptor)
        .build()

    @Provides
    @Singleton
    fun provideMovieNetworkSdk(
        moviesRepository: MoviesRepository
    ): MovieNetworkSDK = MovieNetworkSDK(moviesRepository)
}