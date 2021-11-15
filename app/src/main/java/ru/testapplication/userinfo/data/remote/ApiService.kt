package ru.testapplication.userinfo.data.remote

import io.reactivex.Observable
import retrofit2.http.GET
import ru.testapplication.userinfo.BASE_API_URL
import ru.testapplication.userinfo.data.UsersData

interface ApiService {
    @GET(BASE_API_URL)
    fun getUsers(): Observable<UsersData>
}
