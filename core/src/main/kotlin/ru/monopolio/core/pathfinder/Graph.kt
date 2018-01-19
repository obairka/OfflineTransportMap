package ru.monopolio.core.pathfinder

class Graph : IGraph {
    private val graph: MutableMap<INode, MutableList<Edge>> = HashMap()

    fun add(from: INode, to: INode, weight: Double, isBiDirectional: Boolean) {
        if (!graph.containsKey(from)) {
            graph[from] = arrayListOf<Edge>()

        }
        if (!graph.containsKey(to)) {
            graph[to] = arrayListOf<Edge>()
        }

        graph[from]!!.add(Edge(from, to, weight))
        if (isBiDirectional) graph[to]!!.add(Edge(to, from, weight))
    }

    override fun getNeighbours(node: INode): List<Edge> {
        return graph[node] ?: arrayListOf<Edge>()
    }

    override fun getNodes(): List<INode> {
        return graph.keys.toList()
    }
}