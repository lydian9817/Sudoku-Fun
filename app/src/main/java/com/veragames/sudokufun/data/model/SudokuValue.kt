package com.veragames.sudokufun.data.model

enum class SudokuValue(
    val value: Char,
) {
    EMPTY(' '),
    ONE('1'),
    TWO('2'),
    THREE('3'),
    FOUR('4'),
    FIVE('5'),
    SIX('6'),
    SEVEN('7'),
    EIGHT('8'),
    NINE('9'),
    A('A'),
    B('B'),
    C('C'),
    D('D'),
    E('E'),
    F('F'),
    G('G'),
    ;

    companion object {
        val NINE_VALUES = listOf(ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE)
        val SIXTEEN_VALUES =
            listOf(ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, A, B, C, D, E, F, G)
    }
}
