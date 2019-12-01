package dev.milzipmoza.model

import dev.milzipmoza.model.exception.PointLowerBoundException
import dev.milzipmoza.model.exception.PointUpperBoundException

private const val JOINING_DELIMITER = ""
private const val POINT_LOWER_BOUND = 0
private const val POINT_UPPER_BOUND = 2
private const val LINE_SIZE = 3

class Line {

    private val line = Array(LINE_SIZE) { Square() }

    infix fun at(point: Int): Piece {
        checkLowerBound(point)
        checkUpperBound(point)
        return line[point].piece
    }

    fun putPiece(point: Int, piece: Piece): Piece {
        checkLowerBound(point)
        checkUpperBound(point)
        return line[point] put piece
    }

    fun symbolize(): String = line.asSequence().joinToString(JOINING_DELIMITER) { it.symbolize() }

    private fun checkLowerBound(point: Int) {
        if (point < POINT_LOWER_BOUND) {
            throw PointLowerBoundException()
        }
    }

    private fun checkUpperBound(point: Int) {
        if (point > POINT_UPPER_BOUND) {
            throw PointUpperBoundException()
        }
    }
}