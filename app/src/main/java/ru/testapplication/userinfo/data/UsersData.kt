package ru.testapplication.userinfo.data

import com.google.gson.annotations.Expose

data class UsersData(
    @Expose
    val results: List<UserData>
)