package ru.monopolio.core.pathfinder

import ru.monopolio.core.model.Station
import ru.monopolio.core.model.path.Path
import ru.monopolio.core.model.path.SubRoute


class StationPathFinder(private val graphInfo: GraphInfo) : IStationPathFinder {

    private val pathFinder: IPathFinder = DejkstraPathFinder()

    override fun find(from: Station, to: Station): Path {

        val fromNodes = graphInfo.stationToNode[from.name]
        val toNodes = graphInfo.stationToNode[to.name]

        var minPath: NodePath? = null

        fromNodes!!.forEach { f ->
            toNodes!!.forEach { t ->
                val path = pathFinder.path(f, t, graphInfo.graph)

                minPath.let {
                    if (it == null || it.weight > path.weight) {
                        minPath = path
                    }
                }
            }
        }

        var currentRoute = (minPath!!.path[0] as Node).route
        var currentPath = ArrayList<Station>()
        val path = ArrayList<SubRoute>()

        for (p in minPath!!.path) {
            val node = p as Node
            if (currentRoute != node.route) {
                path.add(SubRoute(currentRoute, currentPath))
                currentRoute = node.route
                currentPath = ArrayList()
            }
            currentPath.add(node.station)
        }
        path.add(SubRoute(currentRoute, currentPath))

        return Path(path, minPath!!.weight)
    }
}