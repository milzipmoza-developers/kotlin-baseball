package dev.milzipmoza.view

import dev.milzipmoza.model.Board
import dev.milzipmoza.model.Result

class OutputView {
    companion object {

        fun welcome() {
            println("Tic Tac Toe 게임에 오신 것을 환영합니다.")
        }

        fun firstUser() {
            println("처음 시작할 유저를 선택해주세요. O / X")
        }

        fun putPoint(turn: String) {
            println("$turn 의 턴 입니다. 어디에 배치할까요?")
            println("좌표를 콤마(,)로 구분하여 입력해주세요.")
        }

        fun printBoard(board: Board) {
            println(board.symbolize())
        }

        fun reInput(message: String?) {
            println(message)
            println("다시 입력해주세요.")
        }

        fun printWinner(winner: Result) {
            println(if (winner == Result(null)) {
                "결과는 ${winner.getWinner()} 입니다."
            } else {
                "${winner.getWinner()} 가 승리하였습니다."
            })
        }
    }
}