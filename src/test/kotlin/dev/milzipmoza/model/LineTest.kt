package dev.milzipmoza.model

import dev.milzipmoza.model.exception.PointLowerBoundException
import dev.milzipmoza.model.exception.PointUpperBoundException
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

internal class LineTest {

    private lateinit var line: Line

    @BeforeEach
    internal fun setUp() {
        line = Line()
    }

    @Test
    internal fun `Line 을 성공적으로 생성한다` () {
        assertThat(line at 0).isEqualTo(Piece.EMPTY)
        assertThat(line at 1).isEqualTo(Piece.EMPTY)
        assertThat(line at 2).isEqualTo(Piece.EMPTY)
    }

    @Test
    internal fun `범위 내에 있는 Line 에 Piece 를 놓을 수 있다 ` () {
        assertThat(line.putPiece(1, Piece.O)).isEqualTo(Piece.O)
    }

    @Test
    internal fun `0 보다 작은 point 에 put 을 시도하면 PointLowerBoundException 을 발생 시킨다 ` () {
        assertThrows<PointLowerBoundException> {
            line.putPiece(-1, Piece.O)
        }
    }

    @Test
    internal fun `2 보다 큰 point 에 put 을 시도하면 PointUpperBoundException 을 발생 시킨다 ` () {
        assertThrows<PointUpperBoundException> {
            line.putPiece(3, Piece.O)
        }
    }

    @Test
    internal fun `0 보다 작은 point 에 get 을 시도하면 PointLowerBoundException 을 발생 시킨다 ` () {
        assertThrows<PointLowerBoundException> {
            line at -1
        }
    }

    @Test
    internal fun `2 보다 큰 point 에 get 을 시도하면 PointUpperBoundException 을 발생 시킨다 ` () {
        assertThrows<PointUpperBoundException> {
            line at 3
        }
    }
}