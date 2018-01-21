package ru.monopolio.core.pathfinder

interface IPathFinder {

    fun path(start: INode, end: INode, graph: IGraph): NodePath

}