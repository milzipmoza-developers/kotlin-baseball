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
    fun setUp() {
        board = Board(START_TURN)
    }

    @Test
    fun `틱택토 게임을 진행하기 위한 Board 를 생성한다`() {
        assertNotNull(board)
    }

    @Test
    fun `새로운 Piece 를 성공적으로 배치한다`() {
        assertThat(board.putPiece(POINT_LOWER_BOUND, POINT_LOWER_BOUND, Piece.O)).isEqualTo(Piece.O)
    }

    @Test
    fun `X 가 0 보다 작을 경우 PointLowerBoundException 이 발생한다`() {
        assertThrows<PointLowerBoundException> {
            board.putPiece(POINT_UNDER_LOWER_BOUND, POINT_LOWER_BOUND, Piece.O)
        }
    }

    @Test
    fun `Y 가 0 보다 작을 경우 PointLowerBoundException 이 발생한다`() {
        assertThrows<PointLowerBoundException> {
            board.putPiece(POINT_LOWER_BOUND, POINT_UNDER_LOWER_BOUND, Piece.O)
        }
    }

    @Test
    fun `X 가 2 보다 클 경우 PointUpperBoundException 이 발생한다`() {
        assertThrows<PointUpperBoundException> {
            board.putPiece(POINT_OVER_UPPER_BOUND, POINT_UPPER_BOUND, Piece.O)
        }
    }

    @Test
    fun `Y 가 2 보다 클 경우 PointUpperBoundException 이 발생한다`() {
        assertThrows<PointUpperBoundException> {
            board.putPiece(POINT_UPPER_BOUND, POINT_OVER_UPPER_BOUND, Piece.O)
        }
    }

    @Test
    fun `init 된 Board 의 symbolize 메서드 동작을 확인한다`() {
        val symbolizeBoard = board.symbolize()
        println(symbolizeBoard)

        assertThat(symbolizeBoard).isEqualTo(
            "[   ][   ][   ]\n" +
            "[   ][   ][   ]\n" +
            "[   ][   ][   ]"
        )
    }

    @Test
    fun `변경사항이 있는 Board 의 symbolize 메서드 동작을 확인한다`() {
        makeLeftDiagonalWinner()

        val symbolizeBoard = board.symbolize()
        println(symbolizeBoard)

        assertThat(symbolizeBoard).isEqualTo(
            "[ O ][   ][ X ]\n" +
            "[   ][ O ][ X ]\n" +
            "[   ][   ][ O ]"
        )
    }

    @Test
    fun `첫 턴이 아닌 유저가 먼저 움직이면 NotYourTurnException 이 발생한다`() {
        assertThrows<NotYourTurnException> {
            board.putPiece(0, 1, Piece.X)
        }
    }

    @Test
    fun `동일한 유저가 연속으로 움직이면 NotYourTurnException 이 발생한다`() {
        board.putPiece(0, 0, Piece.O)
        assertThrows<NotYourTurnException> {
            board.putPiece(0, 1, Piece.O)
        }
    }

    @Test
    fun `첫 턴을 EMPTY 로 시작하면 IllegalFirstTurnException 이 발생한다`() {
        assertThrows<IllegalFirstTurnException> {
            Board(Piece.EMPTY)
        }
    }

    @Test
    fun `수직으로 3연속 등장하는 모양이 있으면 hasVerticalWinner 가 true 를 반환한다`() {
        makeVerticalWinner()

        val symbolizeBoard = board.symbolize()
        println(symbolizeBoard)

        assertThat(board.hasVerticalWinner()).isTrue()
    }

    @Test
    fun `수직으로 3연속 등장하는 모양이 Empty 이면 있으면 hasVerticalWinner 가 false 를 반환한다`() {
        makeVerticalNoWinnerWithTripleEmpty()

        val symbolizeBoard = board.symbolize()
        println(symbolizeBoard)

        assertThat(board.hasVerticalWinner()).isFalse()
    }

    @Test
    fun `수평으로 3연속 등장하는 모양이 있으면 hasHorizontalWinner 가 true 를 반환한다`() {
        makeHorizontalWinner()

        val symbolizeBoard = board.symbolize()
        println(symbolizeBoard)

        assertThat(board.hasHorizontalWinner()).isTrue()
    }

    @Test
    fun `수평으로 3연속 등장하는 모양이 Empty 이면 hasHorizontalWinner 가 false 를 반환한다`() {
        makeHorizontalNoWinnerWithTripleEmpty()

        val symbolizeBoard = board.symbolize()
        println(symbolizeBoard)

        assertThat(board.hasHorizontalWinner()).isFalse()
    }

    @Test
    fun `왼쪽 대각선으로 3연속 등장하는 모양이 있으면 hasDiagonalWinner 가 true 를 반환한다`() {
        makeLeftDiagonalWinner()

        val symbolizeBoard = board.symbolize()
        println(symbolizeBoard)

        assertThat(board.hasDiagonalWinner()).isTrue()
    }

    @Test
    fun `오른쪽 대각선으로 3연속 등장하는 모양이 있으면 hasDiagonalWinner 가 true 를 반환한다`() {
        makeRightDiagonalWinner()

        val symbolizeBoard = board.symbolize()
        println(symbolizeBoard)

        assertThat(board.hasDiagonalWinner()).isTrue()
    }

    @Test
    fun `가득 차는 경우 isFull 이 true 를 반환한다` () {
        makeBoardFullWithoutWinner()

        val symbolizeBoard = board.symbolize()
        println(symbolizeBoard)

        assertThat(board.isBoardFull()).isTrue()
    }

    @Test
    fun `가득 차지 않는 경우 isFull 이 false 를 반환한다` () {
        makeVerticalWinner()

        val symbolizeBoard = board.symbolize()
        println(symbolizeBoard)

        assertThat(board.isBoardFull()).isFalse()
    }

    @Test
    fun `Winner 가 있는 경우 Winner 를 찾는다`() {
        makeVerticalWinner()

        val symbolizeBoard = board.symbolize()
        println(symbolizeBoard)

        assertThat(board.getWinner()).isEqualTo(Result(Piece.O))
    }

    @Test
    fun `Winner 가 없는 경우 경우 Winner 를 찾지 못한다`() {
        makeBoardFullWithoutWinner()

        val symbolizeBoard = board.symbolize()
        println(symbolizeBoard)

        assertThat(board.getWinner()).isEqualTo(Result(null))
    }

    private fun makeLeftDiagonalWinner() {
        board.putPiece(0, 0, Piece.O)
        board.putPiece(0, 2, Piece.X)
        board.putPiece(1, 1, Piece.O)
        board.putPiece(1, 2, Piece.X)
        board.putPiece(2, 2, Piece.O)
    }

    private fun makeRightDiagonalWinner() {
        board.putPiece(0, 1, Piece.O)
        board.putPiece(0, 2, Piece.X)
        board.putPiece(2, 2, Piece.O)
        board.putPiece(1, 1, Piece.X)
        board.putPiece(1, 2, Piece.O)
        board.putPiece(2, 0, Piece.X)
    }

    private fun makeHorizontalWinner() {
        board.putPiece(0, 0, Piece.O)
        board.putPiece(2, 0, Piece.X)
        board.putPiece(0, 1, Piece.O)
        board.putPiece(1, 0, Piece.X)
        board.putPiece(0, 2, Piece.O)
    }

    private fun makeHorizontalNoWinnerWithTripleEmpty() {
        board.putPiece(1, 1, Piece.O)
        board.putPiece(0, 0, Piece.X)
        board.putPiece(0, 1, Piece.O)
        board.putPiece(1, 0, Piece.X)
        board.putPiece(0, 2, Piece.O)
    }

    private fun makeVerticalWinner() {
        board.putPiece(0, 1, Piece.O)
        board.putPiece(0, 2, Piece.X)
        board.putPiece(1, 1, Piece.O)
        board.putPiece(1, 2, Piece.X)
        board.putPiece(2, 1, Piece.O)
    }

    private fun makeVerticalNoWinnerWithTripleEmpty() {
        board.putPiece(0, 1, Piece.O)
        board.putPiece(0, 2, Piece.X)
        board.putPiece(1, 1, Piece.O)
        board.putPiece(2, 1, Piece.X)
        board.putPiece(1, 2, Piece.O)
    }

    private fun makeBoardFullWithoutWinner() {
        board.putPiece(1, 0, Piece.O)
        board.putPiece(0, 2, Piece.X)
        board.putPiece(0, 1, Piece.O)
        board.putPiece(0, 0, Piece.X)
        board.putPiece(1, 1, Piece.O)
        board.putPiece(1, 2, Piece.X)
        board.putPiece(2, 0, Piece.O)
        board.putPiece(2, 1, Piece.X)
        board.putPiece(2, 2, Piece.O)
    }
}
