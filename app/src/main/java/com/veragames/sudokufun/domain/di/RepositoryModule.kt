package com.veragames.sudokufun.domain.di

import com.veragames.sudokufun.domain.repository.GameRepository
import com.veragames.sudokufun.domain.repository.GameRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindGameRepository(gameRepositoryImplImpl: GameRepositoryImpl): GameRepository
}
