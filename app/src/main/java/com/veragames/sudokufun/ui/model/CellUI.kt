package com.veragames.sudokufun.ui.model

import com.veragames.sudokufun.domain.model.CellStatus

data class CellUI(
    val value: Char,
    val status: CellStatus,
)
