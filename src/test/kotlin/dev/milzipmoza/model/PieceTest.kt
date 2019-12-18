package dev.milzipmoza.model

import dev.milzipmoza.model.exception.InvalidTurnException
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

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

    @Test
    internal fun `O 일 때 changeTurn 이 성공한다`() {
        assertThat(Piece.O.nextTurn()).isEqualTo(Piece.X)
    }

    @Test
    internal fun `X 일 때 changeTurn 이 성공한다`() {
        assertThat(Piece.X.nextTurn()).isEqualTo(Piece.O)
    }

    @Test
    internal fun `EMPTY 일 때 changeTurn 이 InvalidTurnException 을 발생시킨다`() {
        assertThrows<InvalidTurnException> {
            Piece.EMPTY.nextTurn()
        }
    }
}