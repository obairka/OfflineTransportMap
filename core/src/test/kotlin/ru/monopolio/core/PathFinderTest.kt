package ru.monopolio.core

import org.junit.Test
import ru.monopolio.core.pathfinder.DejkstraPathFinder
import ru.monopolio.core.pathfinder.Graph
import ru.monopolio.core.pathfinder.INode
import kotlin.test.assertEquals


class PathFinderTest {

    @Test
    fun testTwoNodes() {

        val first = MockNode(1)
        val second = MockNode(2)

        val graph = Graph()

        graph.add(first, second, 1.0, false)

        val pathFinder = DejkstraPathFinder()

        val path = pathFinder.path(first, second, graph)

        assertEquals(arrayListOf(first, second), path)

    }

    @Test
    fun testThreeNodes() {

        val first = MockNode(1)
        val second = MockNode(2)
        val third = MockNode(3)

        val graph = Graph()

        graph.add(first, second, 1.0, true)
        graph.add(second, third, 1.0, true)
        graph.add(first, third, 1.0, true)

        val pathFinder = DejkstraPathFinder()

        val path = pathFinder.path(first, third, graph)

        assertEquals(arrayListOf(first, third), path)

    }

    @Test
    fun testFourNodes() {

        val first = MockNode(1)
        val second = MockNode(2)
        val third = MockNode(3)
        val fourth = MockNode(4)

        val graph = Graph()

        graph.add(first, second, 1.0, false)
        graph.add(second, third, 1.0, false)
        graph.add(third, first, 1.0, false)
        graph.add(third, fourth, 1.0, false)

        val pathFinder = DejkstraPathFinder()

        val path = pathFinder.path(first, fourth, graph)

        assertEquals(arrayListOf(first, second, third, fourth), path)

    }

    @Test
    fun testFourBiNodes() {

        val first = MockNode(1)
        val second = MockNode(2)
        val third = MockNode(3)
        val fourth = MockNode(4)

        val graph = Graph()

        graph.add(first, second, 1.0, false)
        graph.add(second, third, 1.0, false)
        graph.add(third, first, 1.0, true)
        graph.add(third, fourth, 1.0, false)

        val pathFinder = DejkstraPathFinder()

        val path = pathFinder.path(first, fourth, graph)

        assertEquals(arrayListOf(first, third, fourth), path)

    }

    @Test
    fun testFourBiWeightNodes() {

        val first = MockNode(1)
        val second = MockNode(2)
        val third = MockNode(3)
        val fourth = MockNode(4)

        val graph = Graph()

        graph.add(first, second, 1.0, false)
        graph.add(second, third, 1.0, false)
        graph.add(third, first, 4.0, true)
        graph.add(third, fourth, 1.0, false)

        val pathFinder = DejkstraPathFinder()

        val path = pathFinder.path(first, fourth, graph)

        assertEquals(arrayListOf(first, second, third, fourth), path)

    }

    data class MockNode(val id: Int) : INode
}