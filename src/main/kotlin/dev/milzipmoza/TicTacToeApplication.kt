package dev.milzipmoza

import dev.milzipmoza.controller.TicTacToeController

fun main() {
    val game = TicTacToeController()

    game.init()
    while (game.run()) {
    }
    game.winner()
}