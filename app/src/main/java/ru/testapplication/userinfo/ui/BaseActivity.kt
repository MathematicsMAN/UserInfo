package ru.testapplication.userinfo.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.testapplication.userinfo.AppState
import ru.testapplication.userinfo.viewmodel.BaseViewModel

abstract class BaseActivity<T : AppState> : AppCompatActivity() {

    abstract val model: BaseViewModel<T>

    abstract fun renderData(appState: T)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        model.getStateLiveData().observe(this) { renderData(it) }
    }
}