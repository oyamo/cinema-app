package com.oyamo.sinema.common.di

import android.content.Context
import com.oyamo.sinema.data.remote.NetClient
import com.oyamo.sinema.data.remote.NetRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {
    @Provides
    fun provideRepository(@ApplicationContext ctx: Context): NetRepositoryImpl {
        return NetRepositoryImpl(
            NetClient
        )
    }
}
