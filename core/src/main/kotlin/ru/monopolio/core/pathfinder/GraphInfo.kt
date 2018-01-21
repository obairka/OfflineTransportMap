package ru.monopolio.core.pathfinder

data class GraphInfo(
        val graph: IGraph,
        val stationToNode: Map<String, MutableList<Node>>
)