package ru.monopolio.offlinetransportmap.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import ru.monopolio.core.model.Route
import ru.monopolio.offlinetransportmap.repository.IRoutesRepository
import ru.monopolio.offlinetransportmap.repository.RoutesRepository


class RoutesListViewModel : ViewModel() {

    private val routesRepository: IRoutesRepository = RoutesRepository()
    // UI view models
    private val routesLiveData = MutableLiveData<List<Route>>()

    init {
        routesRepository
                .getRoutes()
                .subscribe({ r -> routesLiveData.value = r })
    }

    fun getRoutes(): LiveData<List<Route>> {
        return routesLiveData
    }
}