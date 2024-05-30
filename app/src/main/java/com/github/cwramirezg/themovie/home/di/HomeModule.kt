package com.github.cwramirezg.themovie.home.di

import android.content.Context
import androidx.room.Room
import com.github.cwramirezg.themovie.home.data.local.HomeDao
import com.github.cwramirezg.themovie.home.data.local.HomeDatabase
import com.github.cwramirezg.themovie.home.data.remote.HomeApi
import com.github.cwramirezg.themovie.home.data.repository.HomeRepositoryImpl
import com.github.cwramirezg.themovie.home.domain.repository.HomeRepository
import com.github.cwramirezg.themovie.home.domain.video.usecase.GetVideosUseCase
import com.github.cwramirezg.themovie.home.domain.video.usecase.RequestVideosUseCase
import com.github.cwramirezg.themovie.home.domain.video.usecase.VideoUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HomeModule {

    @Singleton
    @Provides
    fun provideVideoUseCase(repository: HomeRepository): VideoUseCases {
        return VideoUseCases(
            getVideos = GetVideosUseCase(repository),
            requestVideos = RequestVideosUseCase(repository)
        )
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(
            HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
        ).build()
    }

    @Singleton
    @Provides
    fun provideHomeApi(cliente: OkHttpClient): HomeApi {
        return Retrofit
            .Builder()
            .baseUrl(HomeApi.BASE_URL)
            .client(cliente)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(HomeApi::class.java)
    }

    @Singleton
    @Provides
    fun provideHomeDao(@ApplicationContext context: Context): HomeDao {
        return Room.databaseBuilder(
            context,
            HomeDatabase::class.java,
            "the_movie_db",
        ).build().dao
    }

    @Singleton
    @Provides
    fun provideHomeRepository(
        dao: HomeDao,
        api: HomeApi
    ): HomeRepository {
        return HomeRepositoryImpl(dao = dao, api = api)
    }

}