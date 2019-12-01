package dev.milzipmoza.model

import dev.milzipmoza.model.exception.IllegalFirstTurnException
import dev.milzipmoza.model.exception.NotYourTurnException
import dev.milzipmoza.model.exception.PointLowerBoundException
import dev.milzipmoza.model.exception.PointUpperBoundException
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

private const val POINT_UNDER_LOWER_BOUND = -1
private const val POINT_OVER_UPPER_BOUND = 3
private const val POINT_LOWER_BOUND = 0
private const val POINT_UPPER_BOUND = 2
private val START_TURN = Piece.O

internal class BoardTest {

    private lateinit var board: Board

    @BeforeEach
    internal fun setUp() {
        board = Board(START_TURN)
    }

    @Test
    internal fun `틱택토 게임을 진행하기 위한 Board 를 생성한다`() {
        assertNotNull(board)
    }

    @Test
    internal fun `새로운 Piece 를 성공적으로 배치한다`() {
        assertThat(board.putPiece(POINT_LOWER_BOUND, POINT_LOWER_BOUND, Piece.O)).isEqualTo(Piece.O)
    }

    @Test
    internal fun `X 가 0 보다 작을 경우 PointLowerBoundException 이 발생한다`() {
        assertThrows<PointLowerBoundException> {
            board.putPiece(POINT_UNDER_LOWER_BOUND, POINT_LOWER_BOUND, Piece.O)
        }
    }

    @Test
    internal fun `Y 가 0 보다 작을 경우 PointLowerBoundException 이 발생한다`() {
        assertThrows<PointLowerBoundException> {
            board.putPiece(POINT_LOWER_BOUND, POINT_UNDER_LOWER_BOUND, Piece.O)
        }
    }

    @Test
    internal fun `X 가 2 보다 클 경우 PointUpperBoundException 이 발생한다`() {
        assertThrows<PointUpperBoundException> {
            board.putPiece(POINT_OVER_UPPER_BOUND, POINT_UPPER_BOUND, Piece.O)
        }
    }

    @Test
    internal fun `Y 가 2 보다 클 경우 PointUpperBoundException 이 발생한다`() {
        assertThrows<PointUpperBoundException> {
            board.putPiece(POINT_UPPER_BOUND, POINT_OVER_UPPER_BOUND, Piece.O)
        }
    }

    @Test
    internal fun `init 된 Board 의 symbolize 메서드 동작을 확인한다`() {
        val symbolizeBoard = board.symbolize()
        println(symbolizeBoard)

        assertThat(symbolizeBoard).isEqualTo(
            "[   ][   ][   ]\n" +
            "[   ][   ][   ]\n" +
            "[   ][   ][   ]"
        )
    }

    @Test
    internal fun `변경사항이 있는 Board 의 symbolize 메서드 동작을 확인한다`() {
        board.putPiece(0, 0, Piece.O)
        board.putPiece(0, 2, Piece.X)
        board.putPiece(1, 1, Piece.O)
        board.putPiece(1, 2, Piece.X)
        board.putPiece(2, 2, Piece.O)

        val symbolizeBoard = board.symbolize()
        println(symbolizeBoard)

        assertThat(symbolizeBoard).isEqualTo(
            "[ O ][   ][ X ]\n" +
            "[   ][ O ][ X ]\n" +
            "[   ][   ][ O ]"
        )
    }

    @Test
    internal fun `첫 턴이 아닌 유저가 먼저 움직이면 NotYourTurnException 이 발생한다`() {
        assertThrows<NotYourTurnException> {
            board.putPiece(0, 1, Piece.X)
        }
    }

    @Test
    internal fun `동일한 유저가 연속으로 움직이면 NotYourTurnException 이 발생한다`() {
        board.putPiece(0, 0, Piece.O)
        assertThrows<NotYourTurnException> {
            board.putPiece(0, 1, Piece.O)
        }
    }

    @Test
    internal fun `첫 턴을 EMPTY 로 시작하면 IllegalFirstTurnException 이 발생한다`() {
        assertThrows<IllegalFirstTurnException> {
            Board(Piece.EMPTY)
        }
    }
}