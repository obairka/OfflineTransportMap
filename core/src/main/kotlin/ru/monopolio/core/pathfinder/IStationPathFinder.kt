package ru.monopolio.core.pathfinder

import ru.monopolio.core.model.Station
import ru.monopolio.core.model.path.Path


interface IStationPathFinder {

    fun find(from: Station, to: Station): Path
}