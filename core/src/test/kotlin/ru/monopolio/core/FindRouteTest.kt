package ru.monopolio.core

import org.junit.Test
import ru.monopolio.core.model.Location
import ru.monopolio.core.model.Route
import ru.monopolio.core.model.Station
import ru.monopolio.core.model.TransportType
import ru.monopolio.core.pathfinder.*
import kotlin.test.assertEquals

class FindRouteTest {

    @Test
    fun testOneRoute() {
        val station1 = Station("Station1", Location(0.0, 0.0))
        val station2 = Station("Station2", Location(0.0, 0.0))
        val station3 = Station("Station3", Location(0.0, 0.0))
        val route = Route(
                "Route",
                TransportType.BUS,
                arrayOf(
                        station1,
                        station2,
                        station3
                ),
                false,
                false
        )

        val builder = GraphBuilder(object : IDistanceCalculator {

            override fun calc(first: Station, second: Station, route: Route): Double {
                return 1.0
            }

            override fun calc(startStation: Station, startRoute: Route, endStation: Station, endRoute: Route): Double {
                return 1.0
            }
        })

        builder.addRoute(route)
        val graph = builder.build().graph

        val pathFinder = DejkstraPathFinder()

        val start = graph
                .getNodes()
                .first { (it as Node).station == station1 }

        val end = graph
                .getNodes()
                .first { (it as Node).station == station3 }

        val path = pathFinder.path(start, end, graph).path

        assertEquals(station1, (path[0] as Node).station)
        assertEquals(station2, (path[1] as Node).station)
        assertEquals(station3, (path[2] as Node).station)
    }

    @Test
    fun testOneRoutePathFinder() {
        val station1 = Station("Station1", Location(0.0, 0.0))
        val station2 = Station("Station2", Location(0.0, 0.0))
        val station3 = Station("Station3", Location(0.0, 0.0))
        val route = Route(
                "Route",
                TransportType.BUS,
                arrayOf(
                        station1,
                        station2,
                        station3
                ),
                false,
                false
        )

        val builder = GraphBuilder(object : IDistanceCalculator {

            override fun calc(first: Station, second: Station, route: Route): Double {
                return 1.0
            }

            override fun calc(startStation: Station, startRoute: Route, endStation: Station, endRoute: Route): Double {
                return 1.0
            }
        })

        builder.addRoute(route)
        val graph = builder.build()

        val pathFinder = StationPathFinder(graph)

        val path = pathFinder.find(station1, station3)

        assertEquals(station1, path.subRoutes.first().path[0])
        assertEquals(station2, path.subRoutes.first().path[1])
        assertEquals(station3, path.subRoutes.first().path[2])

        assertEquals(route, path.subRoutes.first().route)
    }

    @Test
    fun testTwoRoutePathFinder() {
        val station1 = Station("StationA1", Location(0.0, 0.0))
        val station2 = Station("StationA2", Location(0.0, 0.0))
        val station3 = Station("StationA3", Location(0.0, 0.0))

        val station4 = Station("StationB1", Location(0.0, 0.0))
        val station5 = Station("StationB2", Location(0.0, 0.0))

        val routeA = Route(
                "RouteA",
                TransportType.BUS,
                arrayOf(
                        station1,
                        station2,
                        station3
                ),
                false,
                false
        )
        val routeB = Route(
                "RouteB",
                TransportType.BUS,
                arrayOf(
                        station4,
                        station2,
                        station5
                ),
                false,
                false
        )

        val builder = GraphBuilder(object : IDistanceCalculator {

            override fun calc(first: Station, second: Station, route: Route): Double {
                return 1.0
            }

            override fun calc(startStation: Station, startRoute: Route, endStation: Station, endRoute: Route): Double {
                return 1.0
            }
        })

        builder.addRoute(routeA)
        builder.addRoute(routeB)
        val graph = builder.build()

        val pathFinder = StationPathFinder(graph)

        val path = pathFinder.find(station1, station5)

        assertEquals(2, path.subRoutes[0].path.size)
        assertEquals(2, path.subRoutes[1].path.size)

        assertEquals(station1, path.subRoutes[0].path[0])
        assertEquals(station2, path.subRoutes[0].path[1])

        assertEquals(station2, path.subRoutes[1].path[0])
        assertEquals(station5, path.subRoutes[1].path[1])

        assertEquals(routeA, path.subRoutes[0].route)
        assertEquals(routeB, path.subRoutes[1].route)
    }
}