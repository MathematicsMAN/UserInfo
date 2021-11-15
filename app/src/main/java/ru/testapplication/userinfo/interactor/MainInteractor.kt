package ru.testapplication.userinfo.interactor

import io.reactivex.Observable
import ru.testapplication.userinfo.AppState
import ru.testapplication.userinfo.Interactor
import ru.testapplication.userinfo.Repository
import ru.testapplication.userinfo.data.UsersData

class MainInteractor(
    private val remoteRepository: Repository<UsersData>,
    private val localRepository: Repository<UsersData>,
) : Interactor<AppState> {

    override fun getData(isRemoteSource: Boolean): Observable<AppState> {
        return if (isRemoteSource) {
            remoteRepository.getData().map { AppState.Success(it) }
        } else {
            localRepository.getData().map { AppState.Success(it) }
        }
    }
}