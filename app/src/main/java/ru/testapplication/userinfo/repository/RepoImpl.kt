package ru.testapplication.userinfo.repository

import io.reactivex.Observable
import ru.testapplication.userinfo.DataSource
import ru.testapplication.userinfo.Repository
import ru.testapplication.userinfo.data.UsersData

class RepoImpl(
    private val dataSource: DataSource<UsersData>
) : Repository<UsersData> {

    override fun getData(): Observable<UsersData> {
        return dataSource.getData()
    }
}