package dev.milzipmoza.model.exception

import java.lang.RuntimeException

class PointUpperBoundException : RuntimeException {
    constructor() : super("Point 는 2 보다 큰 수를 지정할 수 없습니다.")

    constructor(point: String) : super("$point 는 2보다 큰 수를 지정할 수 없습니다.")
}