package dev.milzipmoza.model.exception

import java.lang.RuntimeException

class PointLowerBoundException : RuntimeException {
    constructor() : super("Point 는 0 보다 낮은 수를 지정할 수 없습니다.")

    constructor(point: String) : super("$point 는 0보다 낮은 수를 지정할 수 없습니다.")
}
