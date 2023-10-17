package com.bosta.ahmedkhaled.di

import android.app.Application
import android.content.Context
import com.bosta.ahmedkhaled.data.remote.ApiInterface
import com.bosta.ahmedkhaled.data.repository.AlbumsApiRepoImpl
import com.bosta.ahmedkhaled.data.repository.PhotosApiRepoImpl
import com.bosta.ahmedkhaled.data.repository.UsersApiRepoImpl
import com.bosta.ahmedkhaled.domain.repository.AlbumsApIRepo
import com.bosta.ahmedkhaled.domain.repository.PhotosApIRepo
import com.bosta.ahmedkhaled.domain.repository.UsersApIRepo
import com.bosta.ahmedkhaled.utils.Constants.BASE_URL
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideApiInterface(): ApiInterface {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiInterface::class.java)
    }

    @Provides
    @Singleton
    fun provideApplicationContext(application: Application): Context {
        return application.applicationContext
    }
}


@Module
@InstallIn(SingletonComponent::class)
abstract class UsersPort {
    @Binds
    @Singleton
    abstract fun bindApiRepo(impl: UsersApiRepoImpl): UsersApIRepo
}

@Module
@InstallIn(SingletonComponent::class)
abstract class AlbumsPort {
    @Binds
    @Singleton
    abstract fun bindApiRepo(impl: AlbumsApiRepoImpl): AlbumsApIRepo
}

@Module
@InstallIn(SingletonComponent::class)
abstract class PhotosPort {
    @Binds
    @Singleton
    abstract fun bindApiRepo(impl: PhotosApiRepoImpl): PhotosApIRepo
}