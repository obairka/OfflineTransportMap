package ru.monopolio.core.pathfinder

interface IGraph {

    fun getNeighbours(node: INode): List<Edge>

    fun getNodes(): List<INode>
}