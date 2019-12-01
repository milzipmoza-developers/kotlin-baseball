package dev.milzipmoza.model

import dev.milzipmoza.model.exception.PointLowerBoundException
import dev.milzipmoza.model.exception.PointUpperBoundException

private const val BOARD_SIZE = 3
private const val POINT_LOWER_BOUND = 0
private const val POINT_UPPER_BOUND = 2
private const val JOINING_DELIMITER = "\n"

class Board {

    private val board = Array(BOARD_SIZE) { Line() }

    fun putPiece(pointX: Int, pointY: Int, piece: Piece): Piece {
        checkLowerBound(pointX)
        checkUpperBound(pointX)
        return board[pointX].putPiece(pointY, piece)
    }

    fun symbolize() = board.asSequence().joinToString(JOINING_DELIMITER) { it.symbolize() }

    private fun checkLowerBound(pointX: Int) {
        if (pointX < POINT_LOWER_BOUND) {
            throw PointLowerBoundException()
        }
    }

    private fun checkUpperBound(pointX: Int) {
        if (pointX > POINT_UPPER_BOUND) {
            throw PointUpperBoundException()
        }
    }
}