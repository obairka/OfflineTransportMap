package ru.monopolio.core.io

import ru.monopolio.core.io.dto.StationDto

class StationParser(val csvParser: ICsvParser) : IStationParser {

    override fun parse(stationStr: String): StationDto {
        val tokens = csvParser.parse(stationStr, ',')

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