package dev.milzipmoza.model

import dev.milzipmoza.model.exception.IllegalFirstTurnException
import dev.milzipmoza.model.exception.NotYourTurnException
import dev.milzipmoza.model.exception.PointLowerBoundException
import dev.milzipmoza.model.exception.PointUpperBoundException

private const val BOARD_SIZE = 3
private const val POINT_LOWER_BOUND = 0
private const val POINT_UPPER_BOUND = 2
private const val JOINING_DELIMITER = "\n"

class Board(internal var turn: Piece) {
    private val board = Array(BOARD_SIZE) { Line() }

    init {
        checkValidFirstTurn()
    }

    fun putPiece(pointX: Int, pointY: Int, piece: Piece): Piece {
        checkTurn(piece)
        checkLowerBound(pointX)
        checkUpperBound(pointX)
        return updateBoard(pointX, pointY, piece)
    }

    fun symbolize() = board.asSequence().joinToString(JOINING_DELIMITER) { it.symbolize() }

    private fun updateBoard(pointX: Int, pointY: Int, piece: Piece): Piece {
        val updatedPiece = board[pointX].putPiece(pointY, piece)
        this.turn = piece.nextTurn()
        return updatedPiece
    }

    private fun checkValidFirstTurn() {
        if (turn == Piece.EMPTY) {
            throw IllegalFirstTurnException()
        }
    }

    private fun checkTurn(piece: Piece) {
        if (this.turn != piece) {
            throw NotYourTurnException()
        }
    }

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