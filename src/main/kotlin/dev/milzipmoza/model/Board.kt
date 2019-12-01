package dev.milzipmoza.model

import dev.milzipmoza.model.exception.PointLowerBoundException
import dev.milzipmoza.model.exception.PointUpperBoundException

private const val BOARD_SIZE = 3
private const val POINT_LOWER_BOUND = 0
private const val POINT_UPPER_BOUND = 2
private const val POINT_X = "Point X"
private const val POINT_Y = "Point Y"
private const val POINTS = "Point X and Y"

class Board {

    private val board = Array(BOARD_SIZE) { Line() }

    fun putPiece(pointX: Int, pointY: Int, piece: Piece): Piece {
        checkLowerBound(pointX, pointY)
        checkUpperBound(pointX, pointY)
        return board[pointX].putPiece(pointY, piece)
    }

    private fun checkLowerBound(pointX: Int, pointY: Int) {
        if (pointX < POINT_LOWER_BOUND || pointY < POINT_LOWER_BOUND) {
            throw PointLowerBoundException(lowerBoundWhichPoint(pointX, pointY))
        }
    }

    private fun lowerBoundWhichPoint(pointX: Int, pointY: Int): String {
        return when {
            pointX < POINT_LOWER_BOUND -> POINT_X
            pointY < POINT_LOWER_BOUND -> POINT_Y
            else -> POINTS
        }
    }

    private fun checkUpperBound(pointX: Int, pointY: Int) {
        if (pointX > POINT_UPPER_BOUND || pointY > POINT_UPPER_BOUND) {
            throw PointUpperBoundException(upperBoundWhichPoint(pointX, pointY))
        }
    }

    private fun upperBoundWhichPoint(pointX: Int, pointY: Int): String {
        return when {
            pointX > POINT_UPPER_BOUND -> POINT_X
            pointY > POINT_UPPER_BOUND -> POINT_Y
            else -> POINTS
        }
    }
}