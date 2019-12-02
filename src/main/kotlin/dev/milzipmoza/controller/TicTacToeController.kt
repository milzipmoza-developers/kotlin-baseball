package dev.milzipmoza.controller

import dev.milzipmoza.model.Board
import dev.milzipmoza.view.InputView
import dev.milzipmoza.view.OutputView
import java.lang.Exception

class TicTacToeController {
    private lateinit var board: Board

    fun init() {
        OutputView.welcome()
        OutputView.firstUser()
        startGame()
    }

    fun run(): Boolean {
        turnInput()
        showBoard()
        return !board.hasWinner() && !board.isBoardFull()
    }

    fun winner() {
        val winner = board.getWinner()
        OutputView.printWinner(winner)
    }

    private fun startGame(): Board {
        board = try {
            Board(InputView.getFirstUser())
        } catch (e: Exception) {
            OutputView.reInput(e.message)
            startGame()
        }
        return board
    }

    private fun turnInput() {
        OutputView.putPoint(board.turn.name)
        try {
            val points = InputView.getPutPoint()
            board.putPiece(points.first, points.second, board.turn)
        } catch (e: Exception) {
            OutputView.reInput(e.message)
            turnInput()
        }
    }

    private fun showBoard() {
        OutputView.printBoard(board)
    }
}