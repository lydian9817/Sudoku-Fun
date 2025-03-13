package com.veragames.sudokufun.domain.di

import com.veragames.sudokufun.domain.repository.GameRepository
import com.veragames.sudokufun.domain.usecases.game.EraseCellValue
import com.veragames.sudokufun.domain.usecases.game.GameUseCases
import com.veragames.sudokufun.domain.usecases.game.GetBoard
import com.veragames.sudokufun.domain.usecases.game.LoadBoard
import com.veragames.sudokufun.domain.usecases.game.SetCellValue
import com.veragames.sudokufun.domain.usecases.game.UndoMovement
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCasesModule {
    @Provides
    @Singleton
    fun bindGameUseCases(gameRepository: GameRepository): GameUseCases =
        GameUseCases(
            loadBoard = LoadBoard(gameRepository),
            getBoard = GetBoard(gameRepository),
            setCellValue = SetCellValue(gameRepository),
            eraseCellValue = EraseCellValue(gameRepository),
            undoMovement = UndoMovement(gameRepository),
        )
}
