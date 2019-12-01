package dev.milzipmoza.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

private const val EMPTY_PIECE = " "
private const val O_PIECE = "O"
private const val X_PIECE = "X"

internal class PieceTest {

    @Test
    internal fun `Piece 의 EMPTY 에 대하여 symbolize() 를 확인한다` () {
        val emptyPiece = Piece.EMPTY

        assertThat(emptyPiece.symbolize()).isEqualTo(EMPTY_PIECE)
    }

    @Test
    internal fun `Piece 의 O 에 대하여 symbolize() 를 확인한다` () {
        val pieceO = Piece.O

        assertThat(pieceO.symbolize()).isEqualTo(O_PIECE)
    }

    @Test
    internal fun `Piece 의 X 에 대하여 symbolize() 를 확인한다` () {
        val pieceX = Piece.X

        assertThat(pieceX.symbolize()).isEqualTo(X_PIECE)
    }
}