package ru.monopolio.core.pathfinder

import ru.monopolio.core.model.Route
import ru.monopolio.core.model.Station

class GraphBuilder(private val distanceCalculator: IDistanceCalculator) {

    private val graph: Graph = Graph()
    private val nodes: MutableMap<String, MutableList<Node>> = HashMap()

    fun addRoute(route: Route) {
        var prevNode: Node? = null
        var prevStation: Station? = null

        for (station in route.array) {
            val node = Node(route, station)

            if (prevStation != null && prevNode != null) {
                val d = distanceCalculator.calc(prevStation, station, route)
                graph.add(prevNode, node, d, route.isBiDirectional)
            }

            prevNode = node
            prevStation = station

            for (stationNode in nodes[station.name] ?: arrayListOf()) {
                val d = distanceCalculator.calc(
                        stationNode.station,
                        stationNode.route,
                        node.station,
                        node.route)
                graph.add(stationNode, node, d, true)
            }
            addNode(node)

        }
    }

    fun build(): GraphInfo = GraphInfo(graph, nodes)

    private fun addNode(node: Node) {
        if (!nodes.containsKey(node.station.name)) {
            nodes[node.station.name] = arrayListOf()
        }

        nodes[node.station.name]!!.add(node)
    }

}