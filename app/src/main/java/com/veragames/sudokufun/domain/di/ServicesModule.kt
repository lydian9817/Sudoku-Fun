package com.veragames.sudokufun.domain.di

import com.veragames.sudokufun.domain.services.GameService
import com.veragames.sudokufun.domain.usecases.GameUseCases
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ServicesModule {
    @Binds
    @Singleton
    abstract fun bindGameService(gameServiceImpl: GameService): GameUseCases
}
