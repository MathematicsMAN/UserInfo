package ru.testapplication.userinfo.data

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import ru.testapplication.userinfo.BASE_API_URL
import ru.testapplication.userinfo.DataSource

class RetrofitImplementation : DataSource<UsersData> {

    override fun getData(): Observable<UsersData> {
        return createRetrofit().create<ApiService>().getUsers()
    }

    private fun createRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(createOkHttpClient())
            .build()
    }

    private fun createOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor().setLevel(
                    HttpLoggingInterceptor.Level.BODY
                )
            )
            .build()
    }
}
