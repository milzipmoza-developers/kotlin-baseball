package dev.milzipmoza.model

import dev.milzipmoza.model.exception.IllegalFirstTurnException
import dev.milzipmoza.model.exception.NotYourTurnException
import dev.milzipmoza.model.exception.PointLowerBoundException
import dev.milzipmoza.model.exception.PointUpperBoundException
import java.util.stream.IntStream

private const val BOARD_SIZE = 3
private const val POINT_LOWER_BOUND = 0
private const val POINT_MIDDLE = 1
private const val POINT_UPPER_BOUND = 2
private const val JOINING_DELIMITER = "\n"

private const val FIRST_POINT = 0
private const val SECOND_POINT = 1
private const val THIRD_POINT = 2

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

    fun hasVerticalWinner() = lowerBoundToUpperBound().anyMatch { isVerticalWinner(it) }

    fun hasHorizontalWinner() = board.asSequence().any { it.hasWinner() }

    fun hasDiagonalWinner() =
        IntStream.rangeClosed(POINT_LOWER_BOUND, POINT_MIDDLE).anyMatch {
            checkRightDiagonal(it) || checkLeftDiagonal(it)
        } && !isMiddleEmpty()

    fun isBoardFull(): Boolean {
        return lowerBoundToUpperBound().allMatch { !board[it].hasEmpty() }
    }

    private fun lowerBoundToUpperBound() = IntStream.rangeClosed(POINT_LOWER_BOUND, POINT_UPPER_BOUND)

    private fun checkRightDiagonal(index: Int) =
        board[index] at index == board[index + 1] at index + 1

    private fun checkLeftDiagonal(index: Int) =
        board[2 - index] at index == board[index + 1] at 2 - (index + 1)

    private fun isMiddleEmpty() = (board[SECOND_POINT] at SECOND_POINT).isEmpty()

    private fun isVerticalWinner(verticalIdx: Int) =
        isVerticalSame(verticalIdx) && !hasVerticalSecondPoint(verticalIdx)

    private fun isVerticalSame(verticalIdx: Int) =
        board[FIRST_POINT] at verticalIdx == board[SECOND_POINT] at verticalIdx
                && board[SECOND_POINT] at verticalIdx == board[THIRD_POINT] at verticalIdx

    private fun hasVerticalSecondPoint(verticalIdx: Int) = (board[SECOND_POINT] at verticalIdx).isEmpty()

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