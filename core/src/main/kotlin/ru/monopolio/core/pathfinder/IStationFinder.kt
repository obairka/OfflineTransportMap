package ru.monopolio.core.pathfinder

import ru.monopolio.core.model.Station
import ru.monopolio.core.model.path.Path

interface IStationFinder {

    fun path(start: Station, end: Station): Path
}