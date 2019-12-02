package dev.milzipmoza.model.exception

import java.lang.RuntimeException

class NoWinnerAtLineException : RuntimeException("Line 에는 승자가 존재하지 않습니다.")
