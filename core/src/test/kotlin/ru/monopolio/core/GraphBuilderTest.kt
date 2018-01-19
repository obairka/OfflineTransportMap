package ru.monopolio.core

import org.junit.Test
import ru.monopolio.core.model.Location
import ru.monopolio.core.model.Route
import ru.monopolio.core.model.Station
import ru.monopolio.core.model.TransportType
import ru.monopolio.core.pathfinder.Edge
import ru.monopolio.core.pathfinder.GraphBuilder
import ru.monopolio.core.pathfinder.IDistanceCalculator
import ru.monopolio.core.pathfinder.Node
import kotlin.test.assertEquals


class GraphBuilderTest {

    @Test
    fun testTwoNode() {

        val distanceCalculator = object : IDistanceCalculator {

            override fun calc(first: Station, second: Station, route: Route): Double {
                return 1.0
            }

            override fun calc(
                    startStation: Station,
                    startRoute: Route,
                    endStation: Station,
                    endRoute: Route
            ): Double {
                return 2.0
            }
        }

        val graphBuilder = GraphBuilder(distanceCalculator)

        val station1 = Station("Station1", Location(0.0, 0.0))
        val station2 = Station("Station2", Location(0.0, 0.0))

        val route = Route(
                "Route",
                TransportType.BUS,
                arrayOf(station1, station2),
                false,
                true
        )
        graphBuilder.addRoute(route)

        val graph = graphBuilder.build()

        val node1 = Node(route, station1)
        val node2 = Node(route, station2)

        assertEquals(arrayListOf(node1, node2), graph.getNodes())
        assertEquals(arrayListOf(Edge(node1, node2, 1.0)),
                graph.getNeighbours(node1).filter { it.from == node1 && it.to == node2 })
    }
}