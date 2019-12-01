package dev.milzipmoza.model

import dev.milzipmoza.model.exception.AlreadyOccupiedSquareException

class Square {

    var piece: Piece = Piece.EMPTY

    fun putPiece(newPiece: Piece): Piece {
        if (this.piece != Piece.EMPTY) {
            throw AlreadyOccupiedSquareException()
        }
        this.piece = newPiece
        return this.piece
    }

    fun symbolize(): String {
        return "[ ${piece.symbolize()} ]"
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