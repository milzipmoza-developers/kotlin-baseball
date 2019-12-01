package dev.milzipmoza.model

import dev.milzipmoza.model.exception.PointLowerBoundException
import dev.milzipmoza.model.exception.PointUpperBoundException

class Line {

    private val line = Array(3) { Square() }

    fun getPiece(point: Int): Piece {
        checkPoint(point)
        return line[point].piece
    }

    fun putPiece(point: Int, piece: Piece): Piece {
        checkPoint(point)
        return line[point].putPiece(piece)
    }

    private fun checkPoint(point: Int) {
        checkLowerBound(point)
        checkUpperBound(point)
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