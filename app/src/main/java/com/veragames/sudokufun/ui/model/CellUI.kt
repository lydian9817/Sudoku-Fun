package com.veragames.sudokufun.ui.model

import com.veragames.sudokufun.data.model.Cell
import com.veragames.sudokufun.domain.model.CellStatus

data class CellUI(
    val cell: Cell = Cell(' ', 0, 0, 0),
    val status: CellStatus = CellStatus.NORMAL,
)
