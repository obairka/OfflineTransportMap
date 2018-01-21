package ru.monopolio.core.io

// TODO need to test
fun main(args: Array<String>) {
    val stationParser = StationParser(CsvParser())
    val stations = stationParser.parseAll(
            "1,\"Bus Stop 1\",120.4,0.34,\n" +
                    "2,\"Bus Stop 2\",123.4,1.34,"
    )
    println(stations)

    val routeParser = RouteParser(CsvParser())
    val routes = routeParser.parseAll(
            "1,\"Bus Route 1\",BUS,\"1,2\",false,true,\n" +
                    "2,\"Bus Route 2\",BUS,\"1, 3\",false,true,"
    )
    println(routes)
}

data class StationDto(val id: Int, val name: String, val lat: Double, val lng: Double)

enum class TransportType {
    BUS,
    METRO
}

data class RouteDto(val id: Int, val name: String, val transportType: TransportType, val stationIds: List<Int>, val isCircular: Boolean, val isBidirectional: Boolean)

interface ICsvParser {
    fun parse(line: String, separator: Char): List<String>
}

class CsvParser : ICsvParser {
    override fun parse(line: String, separator: Char): List<String> {
        val result = mutableListOf<String>()
        var builder = StringBuilder()
        var quotes = 0

        for (ch in line) {
            when {
                ch == '"' -> {
                    quotes++
                    builder.append(ch)
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

interface IStationParser {
    fun parse(stationStr: String): StationDto
    fun parseAll(stationsStr: String): List<StationDto>
}

class StationParser(val csvParser: ICsvParser) : IStationParser {
    override fun parse(stationStr: String): StationDto {
        var tokens = csvParser.parse(stationStr, ',')

        // id,name,lat,lng
        if (tokens.size != 4) {
            throw IllegalStateException("Invalid format of $stationStr, tokens: $tokens.")
        }

        val id = tokens[0].toInt()
        val name = tokens[1]
        val lat = tokens[2].toDouble()
        val lng = tokens[3].toDouble()

        return StationDto(id, name, lat, lng)
    }

    override fun parseAll(stationsStr: String): List<StationDto> {
        return stationsStr.lines().map { parse(it) }
    }
}

interface IRouteParser {
    fun parse(routeStr: String): RouteDto
    fun parseAll(routeStr: String): List<RouteDto>
}

class RouteParser(val csvParser: ICsvParser) : IRouteParser {
    override fun parse(routeStr: String): RouteDto {
        val tokens = csvParser.parse(routeStr, ',')

        // id,name,transportType,stationIds,isCircular,isBidirectional
        if (tokens.size != 6) {
            throw IllegalStateException("Invalid format of '$routeStr', tokens: $tokens.")
        }

        val id = tokens[0].toInt()
        val name = tokens[1]
        val transportType = TransportType.valueOf(tokens[2])
        val stationIds = tokens[3].trim('"').split(',').map { it.trim().toInt() }
        val isCircular = tokens[4].toBoolean()
        val isBidirectional = tokens[5].toBoolean()

        return RouteDto(id, name, transportType, stationIds, isCircular, isBidirectional)
    }

    override fun parseAll(routeStr: String): List<RouteDto> {
        return routeStr.lines().map { parse(it) }
    }
}