package ru.monopolio.core.model.path

import ru.monopolio.core.model.Route
import ru.monopolio.core.model.Station


data class SubRoute(
        val route: Route,
        val start: Station,
        val end: Station)