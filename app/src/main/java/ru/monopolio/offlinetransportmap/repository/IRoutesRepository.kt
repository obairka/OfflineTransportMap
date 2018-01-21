package ru.monopolio.offlinetransportmap.repository

import io.reactivex.Single
import ru.monopolio.core.model.Route


interface IRoutesRepository {

    fun getRoutes(): Single<List<Route>>

}