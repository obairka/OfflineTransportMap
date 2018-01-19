package ru.monopolio.core.pathfinder

import ru.monopolio.core.model.Route
import ru.monopolio.core.model.Station

class Node(
        val route: Route,
        val station: Station
) : INode {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Node

        if (route != other.route) return false
        if (station != other.station) return false

        return true
    }

    override fun hashCode(): Int {
        var result = route.hashCode()
        result = 31 * result + station.hashCode()
        return result
    }

    override fun toString(): String {
        return "Node(route=$route, station=$station)"
    }


}