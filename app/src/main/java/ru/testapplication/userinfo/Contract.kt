package ru.testapplication.userinfo

import io.reactivex.Observable
import ru.testapplication.userinfo.data.UsersData

const val BASE_API_URL = "https://randomuser.me/api/"

sealed interface AppState {
    data class Success(val data: UsersData) : AppState
    data class Error(val t: Throwable) : AppState
    data class Loading(val progress: Int? = null) : AppState
}

interface View {
    fun renderData(appState: AppState)
}

interface Interactor<T> {
    fun getData(isRemoteSource: Boolean): Observable<T>
}

interface Repository<T> {
    fun getData(): Observable<T>
}

interface DataSource<T> {
    fun getData(): Observable<T>
}
