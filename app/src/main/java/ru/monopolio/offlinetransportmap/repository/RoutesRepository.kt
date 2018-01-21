package ru.monopolio.offlinetransportmap.repository

import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import ru.monopolio.core.model.Location
import ru.monopolio.core.model.Route
import ru.monopolio.core.model.Station
import ru.monopolio.core.model.TransportType


class RoutesRepository : IRoutesRepository {

    private var routes: List<Route>

    init {
        val stations = arrayOf(
                Station("Цветной проезд", Location(0.0, 0.0)),
                Station("ВЦ", Location(0.0, 0.0)),
                Station("Юность", Location(0.0, 0.0)),
                Station("Проспект строителей", Location(0.0, 0.0)),
                Station("Речной вокзал", Location(0.0, 0.0)),

                Station("Студенческая", Location(0.0, 0.0)),
                Station("Карла Маркса", Location(0.0, 0.0)),
                Station("Октябрская", Location(0.0, 0.0)),
                Station("Площадь Ленина", Location(0.0, 0.0)),
                Station("Сибирская", Location(0.0, 0.0))
        )

        routes = arrayListOf(
                Route(
                        "35",
                        TransportType.BUS,
                        arrayOf(stations[0], stations[1], stations[2], stations[3], stations[4]),
                        false,
                        true
                ),
                Route(
                        "Метро. Линия 1",
                        TransportType.METRO,
                        arrayOf(stations[5], stations[6], stations[4], stations[7], stations[8], stations[9]),
                        false,
                        true
                )
        )
    }

    override fun getRoutes(): Single<List<Route>> {
        return Single
                .fromCallable { routes }
                .subscribeOn(Schedulers.io())
    }

}