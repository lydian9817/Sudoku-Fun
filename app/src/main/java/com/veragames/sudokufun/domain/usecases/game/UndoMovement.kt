package com.veragames.sudokufun.domain.usecases.game

import com.veragames.sudokufun.domain.repository.GameRepository
import javax.inject.Inject

class UndoMovement
    @Inject
    constructor(
        private val gameRepository: GameRepository,
    ) {
        suspend operator fun invoke(): Boolean = gameRepository.undoMovement()
    }
