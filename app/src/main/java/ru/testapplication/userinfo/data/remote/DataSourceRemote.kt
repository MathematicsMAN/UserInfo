package ru.testapplication.userinfo.data.remote

import io.reactivex.Observable
import ru.testapplication.userinfo.DataSource
import ru.testapplication.userinfo.data.UsersData

class DataSourceRemote(
    private val remoteProvider: RetrofitImplementation = RetrofitImplementation(),
) : DataSource<UsersData> {

    override fun getData(): Observable<UsersData> {
        return remoteProvider.getData()
    }
}