package ru.testapplication.userinfo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class UserData(
    @Expose
    val name: Name,
    @Expose
    val location: Location,
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
    val last: String
)

data class Location(
    @Expose
    val street: HouseAddress,
    @Expose
    val city: String,
    @Expose
    val country: String,
    @Expose
    val coordinates: Coordinates
)

data class HouseAddress(
    @Expose
    val number: Int,
    @Expose
    val name: String
)

data class Coordinates(
    @Expose
    val lat: String,
    @Expose
    val lon: String
)

data class DateOfBirthday(
    @Expose
    val date: String,
    @Expose
    val age: Int
)


data class Picture(
    @Expose
    val large: String,
    @Expose
    val medium: String,
    @Expose
    val thumbnail: String,
)
