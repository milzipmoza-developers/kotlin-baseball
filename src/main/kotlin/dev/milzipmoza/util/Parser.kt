package dev.milzipmoza.util

import dev.milzipmoza.model.Piece

class Parser {
    companion object {
        fun stringToPiece(input: String) = Piece.of(input)

        fun stringToPoints(input: String): Pair<Int, Int> {
            val token = input.split(",")
            val pointX = token[1].toInt()
            val pointY = token[0].toInt()
            return pointX to pointY
        }
    }
}