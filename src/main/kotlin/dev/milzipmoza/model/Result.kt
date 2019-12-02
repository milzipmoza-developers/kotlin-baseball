package dev.milzipmoza.model

import dev.milzipmoza.model.exception.CannotMakeResultException

class Result(winner: Piece?) {
    private var winner: Piece? = null

    init {
        if (winner != null && winner.isEmpty()) {
            throw CannotMakeResultException()
        }
        this.winner = winner
    }

    fun getWinner() = when (this.winner) {
        null -> "무승부"
        else -> this.winner!!.name
    }
}