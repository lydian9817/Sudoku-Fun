package com.veragames.sudokufun.domain.usecases.game

import com.veragames.sudokufun.domain.model.BoardSize
import com.veragames.sudokufun.domain.repository.GameRepository
import javax.inject.Inject

class LoadBoard
    @Inject
    constructor(
        private val gameRepository: GameRepository,
    ) {
        suspend operator fun invoke(size: BoardSize) {
            gameRepository.loadBoard(size)
        }
    }
