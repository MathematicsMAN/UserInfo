package ru.testapplication.userinfo.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RoomUser(
    @PrimaryKey val uuid: String,
    val nameTitle: String,
    val nameFirst: String,
    val nameLast: String,

    val locationStreetName: String,
    val locationCity: String,
    val locationCountry: String,
    val locationCoordinatesLatitude: String,
    val locationCoordinatesLongitude: String,

    val dateOfBirthday: String,
    val phone: String,
    val pictureMediumUrl: String,
)