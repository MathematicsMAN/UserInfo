package ru.testapplication.userinfo.ui.image

interface IImageLoader<T> {
    fun loadTo(url: String, target: T)
}