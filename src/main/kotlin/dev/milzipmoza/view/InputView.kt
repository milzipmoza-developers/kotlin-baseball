package dev.milzipmoza.view

import dev.milzipmoza.util.Parser

class InputView {
    companion object {

        fun getFirstUser() = Parser.stringToPiece(readLine()!!.toString())

        fun getPutPoint() = Parser.stringToPoints(readLine()!!.toString())
    }
}