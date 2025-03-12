package com.veragames.sudokufun.domain.usecases.game

import com.veragames.sudokufun.domain.repository.GameRepository
import javax.inject.Inject

class GetBoard
    @Inject
    constructor(
        private val gameRepository: GameRepository,
    ) {
        suspend operator fun invoke() = gameRepository.getBoard()
    }
