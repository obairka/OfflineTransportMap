package ru.monopolio.core.pathfinder

interface IStationFinder {

    fun path(start: INode, end: INode, graph: IGraph): List<INode>
}