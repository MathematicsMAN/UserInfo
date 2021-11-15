package ru.testapplication.userinfo.data

import io.reactivex.Observable
import retrofit2.http.GET
import ru.testapplication.userinfo.BASE_API_URL

interface ApiService {
    @GET(BASE_API_URL)
    fun getUsers(): Observable<UsersData>
}
