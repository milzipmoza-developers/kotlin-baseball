package dev.milzipmoza.model

import dev.milzipmoza.model.exception.PointLowerBoundException
import dev.milzipmoza.model.exception.PointUpperBoundException

class Line {

    private val line = Array(3) { Square() }

    fun getPiece(point: Int): Piece {
        checkLowerBound(point)
        checkUpperBound(point)
        return line[point].piece
    }

    fun putPiece(point: Int, piece: Piece): Piece {
        checkLowerBound(point)
        checkUpperBound(point)
        return line[point].putPiece(piece)
    }

    private fun checkLowerBound(point: Int) {
        if (point < 0) {
            throw PointLowerBoundException()
        }
    }

    private fun checkUpperBound(point: Int) {
        if (point > 2) {
            throw PointUpperBoundException()
        }
    }
}