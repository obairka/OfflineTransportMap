package ru.monopolio.core.io

import org.junit.Test
import ru.monopolio.core.io.dto.RouteDto
import ru.monopolio.core.io.dto.StationDto
import ru.monopolio.core.io.dto.TransportTypeDto
import kotlin.test.assertEquals

class ParserTest {

    @Test
    fun testStationParser() {
        val stationParser = StationParser(CsvParser())
        val stations = stationParser.parseAll(
                "1,\"Bus Stop 1\",120.4,0.34,\n" +
                        "2,\"Bus Stop 2\",123.4,1.34,"
        )
        assertEquals(StationDto(1, "Bus Stop 1", 120.4, 0.34), stations[0])
        assertEquals(StationDto(2, "Bus Stop 2", 123.4, 1.34), stations[1])
    }

    @Test
    fun testRouterParser() {
        val routeParser = RouteParser(CsvParser())
        val routes = routeParser.parseAll(
                "1,\"Bus Route 1\",BUS,\"1,2\",false,true,\n" +
                        "2,\"Bus Route 2\",TROLLEYBUS,\"1, 3\",false,true,"
        )
        assertEquals(RouteDto(
                1,
                "Bus Route 1",
                TransportTypeDto.BUS,
                arrayListOf(1, 2),
                false,
                true
        ), routes[0])
        assertEquals(RouteDto(
                2,
                "Bus Route 2",
                TransportTypeDto.TROLLEYBUS,
                arrayListOf(1, 3),
                false,
                true
        ), routes[1])
    }
}