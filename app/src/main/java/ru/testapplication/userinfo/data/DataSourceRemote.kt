package ru.testapplication.userinfo.data

import io.reactivex.Observable
import ru.testapplication.userinfo.DataSource

class DataSourceRemote(
    private val remoteProvider: RetrofitImplementation = RetrofitImplementation(),
) : DataSource<UsersData> {

    override fun getData(): Observable<UsersData> {
        return remoteProvider.getData()
    }
}