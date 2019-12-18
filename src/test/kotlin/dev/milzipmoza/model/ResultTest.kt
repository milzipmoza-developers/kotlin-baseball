package dev.milzipmoza.model

import dev.milzipmoza.model.exception.CannotMakeResultException
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

internal class ResultTest {

    @Test
    fun `승자가 없을 때 getWinner 가 무승부를 반환한다`() {
        val noWinner = Result(null)
        assertThat(noWinner.getWinner()).isEqualTo("무승부")
    }

    @Test
    fun `승자가 있을 때 getWinner 가 승자를 반환한다`() {
        val noWinner = Result(Piece.O)
        assertThat(noWinner.getWinner()).isEqualTo("O")
    }

    @Test
    fun `말도 안되는 승자는 getWinner 가 CannotMakeResultException 를 발생시킨다`() {
        assertThrows<CannotMakeResultException> {
            Result(Piece.EMPTY)
        }
    }
}