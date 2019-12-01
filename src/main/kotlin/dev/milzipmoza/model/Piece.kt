package dev.milzipmoza.model

private const val EMPTY_SYMBOL = " "

enum class Piece {
    EMPTY, O, X;

    fun symbolize(): String {
        return when (EMPTY) {
            this -> EMPTY_SYMBOL
            else -> this.name
        }
    }
}