package ru.monopolio.core.io

class CsvParser : ICsvParser {

    override fun parse(line: String, separator: Char): List<String> {
        val result = mutableListOf<String>()
        var builder = StringBuilder()
        var quotes = 0

        for (ch in line) {
            when {
                ch == '"' -> {
                    quotes++
                    //builder.append(ch)
                }
                (ch == '\n') || (ch == '\r') -> {
                }
                (ch == separator) && (quotes % 2 == 0) -> {
                    result.add(builder.toString())
                    builder = StringBuilder()
                }
                else -> builder.append(ch)
            }
        }

        return result
    }
}