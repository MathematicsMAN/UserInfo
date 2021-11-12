package ru.testapplication.userinfo

import io.reactivex.rxjava3.core.Observable

sealed interface AppState {
    data class Success(val data: List<UserData>): AppState
    data class Error(val t: Throwable): AppState
    data class Loading(val progress: Int? = null): AppState
}

interface View {
    fun renderData(appState: AppState)
}

interface Presenter<T: AppState, V: View> {
    fun attachView(view: V)

    fun detachView(view: V)

    fun getData(user: UserData, isOnline: Boolean)
}

interface Interactor<T> {
    fun getData(user: UserData, isRemoteSource: Boolean): Observable<T>
}

interface Repository<T> {
    fun getData(user: UserData): Observable<T>
}

interface DataSource<T> {
    fun getData(user: UserData): Observable<T>
}
