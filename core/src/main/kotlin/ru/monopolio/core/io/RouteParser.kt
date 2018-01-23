package ru.monopolio.core.io

import ru.monopolio.core.io.dto.RouteDto
import ru.monopolio.core.io.dto.TransportTypeDto

class RouteParser(val csvParser: ICsvParser) : IRouteParser {

    override fun parse(routeStr: String): RouteDto {
        val tokens = csvParser.parse(routeStr, ',')

        // id,name,transportType,stationIds,isCircular,isBidirectional
        if (tokens.size != 6) {
            throw IllegalStateException("Invalid format of '$routeStr', tokens: $tokens.")
        }

        val id = tokens[0].toInt()
        val name = tokens[1]
        val transportType = TransportTypeDto.valueOf(tokens[2])
        val stationIds = tokens[3].split(',').map { it.trim().toInt() }
        val isCircular = tokens[4].toBoolean()
        val isBidirectional = tokens[5].toBoolean()

        return RouteDto(id, name, transportType, stationIds, isCircular, isBidirectional)
    }

    override fun parseAll(routeStr: String): List<RouteDto> {
        return routeStr.lines().map { parse(it) }
    }
}