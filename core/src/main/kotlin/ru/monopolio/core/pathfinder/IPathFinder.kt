package ru.monopolio.core.pathfinder

import ru.monopolio.core.model.path.Path


interface IPathFinder {

    fun find(): Path
}