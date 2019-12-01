package dev.milzipmoza.model

import dev.milzipmoza.model.exception.InvalidTurnException

private const val EMPTY_SYMBOL = " "

enum class Piece {
    EMPTY, O, X;

    fun symbolize() = when (EMPTY) {
        this -> EMPTY_SYMBOL
        else -> this.name
    }

    fun nextTurn(): Piece = when (this) {
        O -> X
        X -> O
        else -> throw InvalidTurnException()
    }
}