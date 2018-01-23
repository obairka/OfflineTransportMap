package ru.monopolio.core.io.dto

data class RouteDto(
        val id: Int,
        val name: String,
        val transportType: TransportTypeDto,
        val stationIds: List<Int>,
        val isCircular: Boolean,
        val isBidirectional: Boolean
)