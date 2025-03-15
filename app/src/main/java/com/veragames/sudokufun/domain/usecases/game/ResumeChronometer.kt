package com.veragames.sudokufun.domain.usecases.game

import com.veragames.sudokufun.domain.repository.GameRepository
import javax.inject.Inject

class ResumeChronometer
    @Inject
    constructor(
        private val gameRepository: GameRepository,
    ) {
        suspend operator fun invoke() = gameRepository.resumeChronometer()
    }
