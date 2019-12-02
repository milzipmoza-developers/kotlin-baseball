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

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Result

        if (winner != other.winner) return false

        return true
    }

    override fun hashCode(): Int {
        return winner?.hashCode() ?: 0
    }


}