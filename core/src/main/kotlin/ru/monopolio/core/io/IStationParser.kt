package ru.monopolio.core.io

import ru.monopolio.core.io.dto.StationDto

interface IStationParser {

    fun parse(stationStr: String): StationDto

    fun parseAll(stationsStr: String): List<StationDto>
}