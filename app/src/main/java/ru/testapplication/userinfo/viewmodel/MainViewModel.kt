package ru.testapplication.userinfo.viewmodel

import io.reactivex.observers.DisposableObserver
import ru.testapplication.userinfo.AppState
import ru.testapplication.userinfo.data.remote.DataSourceRemote
import ru.testapplication.userinfo.interactor.MainInteractor
import ru.testapplication.userinfo.repository.RepoImpl

class MainViewModel(
    private val interactor: MainInteractor = MainInteractor(
        remoteRepository = RepoImpl(DataSourceRemote()),
        localRepository = RepoImpl(DataSourceRemote())
    ),
) : BaseViewModel<AppState>() {

    fun getUsersData(isOnline: Boolean) {
        compositeDisposable.add(interactor.getData(isOnline)
            .subscribeOn(schedulerProvider.io)
            .observeOn(schedulerProvider.ui)
            .doOnSubscribe {
                stateLiveData.value = AppState.Loading(null)
            }
            .subscribeWith(getObservable())
        )
    }

    private fun getObservable(): DisposableObserver<AppState> {
        return object : DisposableObserver<AppState>() {
            override fun onNext(appState: AppState) {
                stateLiveData.value = appState
            }

            override fun onError(e: Throwable) {
                stateLiveData.value = AppState.Error(e)
            }

            override fun onComplete() = Unit
        }
    }
}