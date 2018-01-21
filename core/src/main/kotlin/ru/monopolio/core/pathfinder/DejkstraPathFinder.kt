package ru.monopolio.core.pathfinder


class DejkstraPathFinder : IPathFinder {

    override fun path(start: INode, end: INode, graph: IGraph): NodePath {
        val visitedNodes = HashSet<INode>()
        val distances = HashMap<INode, Double>()
        val pathes = HashMap<INode, ArrayList<INode>>()
        val nodes = graph.getNodes()
        var currentNode = start

        nodes.forEach {
            distances.put(it, Double.MAX_VALUE)
            pathes.put(it, arrayListOf())
        }
        distances[start] = 0.0
        pathes[start]!!.add(start)

        while (true) {
            visitedNodes.add(currentNode)

            val neighbours = graph
                    .getNeighbours(currentNode)
                    .filter { n -> !visitedNodes.contains(n.to) }
                    .sortedBy { distances[it.to] }

            neighbours.forEach {
                if (distances[it.to]!! > distances[currentNode]!! + it.weight) {
                    distances[it.to] = distances[currentNode]!! + it.weight
                    val newPath = ArrayList(pathes[currentNode])
                    newPath.add(it.to)
                    pathes[it.to] = newPath
                }
            }

            if (visitedNodes.size == nodes.size) {
                break
            } else {
                currentNode = nodes
                        .filter { !visitedNodes.contains(it) }
                        .sortedBy { distances[it] }
                        .first()
            }
        }

        return NodePath(pathes[end]!!.toList(), distances[end]!!)
    }

}