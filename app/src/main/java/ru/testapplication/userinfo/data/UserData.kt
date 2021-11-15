package ru.testapplication.userinfo.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class UserData(
    @Expose
    val name: Name,
    @Expose
    val location: Location,
    @Expose
    val login: Login,
    @Expose
    @SerializedName("dob")
    val dateOfBirthday: DateOfBirthday,
    @Expose
    val phone: String,
    @Expose
    val picture: Picture,
)

data class Name(
    @Expose
    val title: String,
    @Expose
    val first: String,
    @Expose
    val last: String,
)

data class Location(
    @Expose
    val street: HouseAddress,
    @Expose
    val city: String,
    @Expose
    val country: String,
    @Expose
    val coordinates: Coordinates,
)

data class HouseAddress(
    @Expose
    val number: Int,
    @Expose
    val name: String,
)

data class Coordinates(
    @Expose
    @SerializedName("latitude")
    val lat: String,
    @Expose
    @SerializedName("longitude")
    val lon: String,
)

data class DateOfBirthday(
    @Expose
    val date: String,
    @Expose
    val age: Int,
)

data class Picture(
    @Expose
    val large: String,
    @Expose
    val medium: String,
    @Expose
    val thumbnail: String,
)

data class Login(
    @Expose
    val uuid: String
)