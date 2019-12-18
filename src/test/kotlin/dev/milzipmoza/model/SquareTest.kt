package dev.milzipmoza.model

import dev.milzipmoza.model.exception.AlreadyOccupiedSquareException
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

internal class SquareTest {

    private lateinit var square: Square

    @BeforeEach
    internal fun setUp() {
        square = Square()
    }

    @Test
    internal fun `Square 를 성공적으로 생성한다`() {
        assertNotNull(square)
    }

    @Test
    internal fun `Square 의 초기 상태는 Empty 이다` () {
        assertThat(square.piece).isEqualTo(Piece.EMPTY)
    }

    @Test
    internal fun `Square 의 status 가 EMPTY 일 때, 상태를 변경할 수 있다` () {
        assertThat(square put Piece.O).isEqualTo(Piece.O)
    }

    @Test
    internal fun `Square 의 status 가 EMPTY 가 아닐 때, 상태를 변경할 수 없다` () {
        square put Piece.O

        assertThrows<AlreadyOccupiedSquareException> {
            square put Piece.X
        }
    }

    @Test
    internal fun `Square 의 status 가 EMPTY 일 때, symbolize 의 동작을 확인한다` () {
        assertThat(square.symbolize()).isEqualTo("[   ]")
    }

    @Test
    internal fun `Square 의 status 를 O 로 변경한 후, symbolize 의 동작을 확인한다` () {
        square put Piece.O

        assertThat(square.symbolize()).isEqualTo("[ O ]")
    }

    @Test
    internal fun `Square 의 status 가 X 로 변경한 후, symbolize 의 동작을 확인한다` () {
        square put Piece.X

        assertThat(square.symbolize()).isEqualTo("[ X ]")
    }

    @Test
    internal fun `Square isEmpty 메서드가 Piece EMPTY 인 경우 true 를 반환한다` () {
        assertThat(square.isEmpty()).isTrue()
    }

    @Test
    internal fun `Square isEmpty 메서드가 Piece O 인 경우 false 를 반환한다` () {
        square put Piece.O
        assertThat(square.isEmpty()).isFalse()
    }

    @Test
    internal fun `Square isEmpty 메서드가 Piece X 인 경우 false 를 반환한다` () {
        square put Piece.X
        assertThat(square.isEmpty()).isFalse()
    }
}