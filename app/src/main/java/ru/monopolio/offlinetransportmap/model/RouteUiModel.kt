package ru.monopolio.offlinetransportmap.model

import ru.monopolio.core.model.TransportType

class RouteUiModel(
        val name: String,
        val transportType: TransportType,
        val array: Array<StationUiModel>,
        val isCircular: Boolean,
        val isBiDirectional: Boolean)