package dev.milzipmoza.model

import dev.milzipmoza.model.exception.PointLowerBoundException
import dev.milzipmoza.model.exception.PointUpperBoundException
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

private const val POINT_UNDER_LOWER_BOUND = -1
private const val POINT_OVER_UPPER_BOUND = 3
private const val POINT_LOWER_BOUND = 0
private const val POINT_UPPER_BOUND = 2

private const val POINT_X_LOWER_BOUND_MESSAGE = "Point X 는 0보다 낮은 수를 지정할 수 없습니다."
private const val POINT_Y_LOWER_BOUND_MESSAGE = "Point Y 는 0보다 낮은 수를 지정할 수 없습니다."
private const val POINT_X_UPPER_BOUND_MESSAGE = "Point X 는 2보다 큰 수를 지정할 수 없습니다."
private const val POINT_Y_UPPER_BOUND_MESSAGE = "Point Y 는 2보다 큰 수를 지정할 수 없습니다."

internal class BoardTest {

    private lateinit var board: Board

    @BeforeEach
    internal fun setUp() {
        board = Board()
    }

    @Test
    internal fun `틱택토 게임을 진행하기 위한 Board 를 생성한다` () {
        assertNotNull(board)
    }

    @Test
    internal fun `새로운 Piece 를 성공적으로 배치한다` () {
        assertThat(board.putPiece(POINT_LOWER_BOUND, POINT_LOWER_BOUND, Piece.O)).isEqualTo(Piece.O)
    }

    @Test
    internal fun `X 가 0 보다 작을 경우 PointLowerBoundException 이 발생한다` () {
        val exception = assertThrows<PointLowerBoundException> {
            board.putPiece(POINT_UNDER_LOWER_BOUND, POINT_LOWER_BOUND, Piece.O)
        }

        assertThat(exception.message).isEqualTo(POINT_X_LOWER_BOUND_MESSAGE)
    }

    @Test
    internal fun `Y 가 0 보다 작을 경우 PointLowerBoundException 이 발생한다` () {
        val exception = assertThrows<PointLowerBoundException> {
            board.putPiece(POINT_LOWER_BOUND, POINT_UNDER_LOWER_BOUND, Piece.O)
        }

        assertThat(exception.message).isEqualTo(POINT_Y_LOWER_BOUND_MESSAGE)
    }

    @Test
    internal fun `X 가 2 보다 클 경우 PointUpperBoundException 이 발생한다` () {
        val exception = assertThrows<PointUpperBoundException> {
            board.putPiece(POINT_OVER_UPPER_BOUND, POINT_UPPER_BOUND, Piece.O)
        }

        assertThat(exception.message).isEqualTo(POINT_X_UPPER_BOUND_MESSAGE)
    }

    @Test
    internal fun `Y 가 2 보다 클 경우 PointUpperBoundException 이 발생한다` () {
        val exception = assertThrows<PointUpperBoundException> {
            board.putPiece(POINT_UPPER_BOUND, POINT_OVER_UPPER_BOUND, Piece.O)
        }

        assertThat(exception.message).isEqualTo(POINT_Y_UPPER_BOUND_MESSAGE)
    }
}