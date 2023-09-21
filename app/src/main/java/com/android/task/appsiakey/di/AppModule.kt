package com.android.task.appsiakey.di

import android.content.Context
import com.android.task.appsiakey.api.AppsKeyApiInterface
import com.android.task.appsiakey.utils.Constants.Companion.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    //    Provide Context
    @Provides
    @Singleton
    fun getContext(@ApplicationContext context: Context): Context {
        return context
    }

    @Provides
    @Singleton
    fun getRetroServiceInstance(retrofit: Retrofit): AppsKeyApiInterface {
        return retrofit.create(AppsKeyApiInterface::class.java)
    }

    @Provides
    @Singleton
    fun getRetroInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}
