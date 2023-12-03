package com.angelmascarell.deliveryApp.core.dependencyinjection

import com.angelmascarell.deliveryApp.core.security.PasswordHash
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CryptoModule {
    @Singleton
    @Provides
    fun provideCryptoPassword(): PasswordHash = PasswordHash()
}