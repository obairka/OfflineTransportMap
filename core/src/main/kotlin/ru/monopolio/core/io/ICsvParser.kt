package ru.monopolio.core.io

interface ICsvParser {

    fun parse(line: String, separator: Char): List<String>
}