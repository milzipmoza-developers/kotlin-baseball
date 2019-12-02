package dev.milzipmoza.model

import dev.milzipmoza.model.exception.PointLowerBoundException
import dev.milzipmoza.model.exception.PointUpperBoundException
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

private const val FIRST_POINT = 0
private const val SECOND_POINT = 1
private const val THIRD_POINT = 2
private const val POINT_UNDER_LOWER_BOUND = -1
private const val POINT_OVER_UPPER_BOUND = 3

internal class LineTest {

    private lateinit var line: Line

    @BeforeEach
    fun setUp() {
        line = Line()
    }

    @Test
    fun `Line 을 성공적으로 생성한다` () {
        assertThat(line at FIRST_POINT).isEqualTo(Piece.EMPTY)
        assertThat(line at SECOND_POINT).isEqualTo(Piece.EMPTY)
        assertThat(line at THIRD_POINT).isEqualTo(Piece.EMPTY)
    }

    @Test
    fun `범위 내에 있는 Line 에 Piece 를 놓을 수 있다` () {
        assertThat(line.putPiece(SECOND_POINT, Piece.O)).isEqualTo(Piece.O)
    }

    @Test
    fun `0 보다 작은 point 에 put 을 시도하면 PointLowerBoundException 을 발생 시킨다` () {
        assertThrows<PointLowerBoundException> {
            line.putPiece(POINT_UNDER_LOWER_BOUND, Piece.O)
        }
    }

    @Test
    fun `2 보다 큰 point 에 put 을 시도하면 PointUpperBoundException 을 발생 시킨다` () {
        assertThrows<PointUpperBoundException> {
            line.putPiece(POINT_OVER_UPPER_BOUND, Piece.O)
        }
    }

    @Test
    fun `0 보다 작은 point 에 get 을 시도하면 PointLowerBoundException 을 발생 시킨다` () {
        assertThrows<PointLowerBoundException> {
            line at POINT_UNDER_LOWER_BOUND
        }
    }

    @Test
    fun `2 보다 큰 point 에 get 을 시도하면 PointUpperBoundException 을 발생 시킨다` () {
        assertThrows<PointUpperBoundException> {
            line at POINT_OVER_UPPER_BOUND
        }
    }

    @Test
    fun `초기화 된 Line 에 대해 symbolize 메서드의 동작을 확인한다` () {
        val line = Line()

        assertThat(line.symbolize()).isEqualTo("[   ][   ][   ]")
    }

    @Test
    fun `point 0이 X, point 2이 O 인 Line 에 대해 symbolize 메서드의 동작을 확인한다` () {
        val line = Line()

        line.putPiece(FIRST_POINT, Piece.X)
        line.putPiece(THIRD_POINT, Piece.O)

        assertThat(line.symbolize()).isEqualTo("[ X ][   ][ O ]")
    }

    @Test
    fun `line 에서 모두 같은 모양인 경우 hasWinner 메서드가 true 를 반환한다` () {
        // line 이 모두 같은 모양인 경우
        line.putPiece(FIRST_POINT, Piece.O)
        line.putPiece(SECOND_POINT, Piece.O)
        line.putPiece(THIRD_POINT, Piece.O)

        assertThat(line.hasWinner()).isEqualTo(true)
    }

    @Test
    fun `line 에서 모두 같은 모양인데 Empty 인 경우 hasWinner 메서드가 false 를 반환한다` () {
        // line 이 모두 같은 모양인데 Piece.EMPTY
        assertThat(line.hasWinner()).isEqualTo(false)
    }

    @Test
    fun `line 에서 모두 같은 모양이 아닌 경우 hasWinner 메서드가 false 를 반환한다` () {
        // line 이 모두 같은 모양이 아닌
        line.putPiece(FIRST_POINT, Piece.O)
        line.putPiece(SECOND_POINT, Piece.X)
        line.putPiece(THIRD_POINT, Piece.O)

        assertThat(line.hasWinner()).isEqualTo(false)
    }
}