package dev.milzipmoza.model.exception

import java.lang.RuntimeException

class IllegalFirstTurnException : RuntimeException("Piece.EMPTY 로 시작할 수 없습니다.")