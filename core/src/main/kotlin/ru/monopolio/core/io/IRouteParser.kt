package ru.monopolio.core.io

import ru.monopolio.core.io.dto.RouteDto

interface IRouteParser {

    fun parse(routeStr: String): RouteDto

    fun parseAll(routeStr: String): List<RouteDto>
}