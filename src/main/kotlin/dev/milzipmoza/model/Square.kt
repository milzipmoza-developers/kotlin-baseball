package dev.milzipmoza.model

import dev.milzipmoza.model.exception.AlreadyOccupiedSquareException

class Square {

    var piece: Piece = Piece.EMPTY

    infix fun put(piece: Piece): Piece {
        checkSquare()
        this.piece = piece
        return this.piece
    }

    private fun checkSquare() {
        if (this.piece != Piece.EMPTY) {
            throw AlreadyOccupiedSquareException()
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Square

        if (piece != other.piece) return false

        return true
    }

    override fun hashCode(): Int {
        return piece.hashCode()
    }
}