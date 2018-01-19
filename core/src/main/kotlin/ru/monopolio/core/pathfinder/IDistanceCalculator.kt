package ru.monopolio.core.pathfinder

import ru.monopolio.core.model.Route
import ru.monopolio.core.model.Station

interface IDistanceCalculator {

    fun calc(first: Station, second: Station, route: Route): Double

    fun calc(startStation: Station, startRoute: Route, endStation: Station, endRoute: Route): Double
}