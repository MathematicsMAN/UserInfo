package ru.testapplication.userinfo.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.lifecycle.ViewModelProvider
import ru.testapplication.userinfo.AppState
import ru.testapplication.userinfo.R
import ru.testapplication.userinfo.View
import ru.testapplication.userinfo.data.UserData
import ru.testapplication.userinfo.databinding.AcMainBinding
import ru.testapplication.userinfo.viewmodel.MainViewModel

class MainActivity : BaseActivity<AppState>(), View {

    private lateinit var binding: AcMainBinding

    override val model: MainViewModel by lazy {
        ViewModelProvider.NewInstanceFactory().create(MainViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = AcMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.reloadFab.setOnClickListener {
            model.getUsersData(true)
        }
    }

    override fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Success -> {
                val dataModel = appState.data.results
                if (dataModel.isEmpty()) {
                    showErrorScreen(getString(R.string.empty_server_response_on_success))
                } else {
                    val userData = dataModel.first()

                    showViewSuccess()
                    fillingForm(userData)
                    binding.buttonCall.setOnClickListener {
                        val intent = Intent(
                            Intent.ACTION_DIAL,
                            Uri.fromParts("tel", userData.phone, null)
                        )

                        startActivity(intent)
                    }
                    binding.buttonMap.setOnClickListener {
                        val coordinates = userData.location.coordinates
                        val intentUri = Uri.parse(
                            "google.streetview:cbll=${coordinates.lat},${coordinates.lon}"
                        )
                        val intent = Intent(Intent.ACTION_VIEW, intentUri)
                        intent.setPackage("com.google.android.apps.maps")
                        startActivity(intent)
                    }
                }
            }
            is AppState.Loading -> {
                showViewLoading()
                if (appState.progress != null) {
                    binding.progressBarHorizontal.visibility = VISIBLE
                    binding.progressBarRound.visibility = GONE
                    binding.progressBarHorizontal.progress = appState.progress
                } else {
                    binding.progressBarHorizontal.visibility = GONE
                    binding.progressBarRound.visibility = VISIBLE
                }
            }
            is AppState.Error -> {
                showErrorScreen(appState.t.message)
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun fillingForm(userData: UserData) {

        binding.textFullName.editText?.setText("${userData.name.first} ${userData.name.last}")
        binding.textDateOfBirthday.editText?.setText(getDate(userData.dateOfBirthday.date))
        binding.textPhoneNumber.editText?.setText(userData.phone)
        binding.textCountry.editText?.setText(userData.location.country)
        binding.textCity.editText?.setText(userData.location.city)
        binding.textStreet.editText?.setText(userData.location.street.name)
        binding.textCoordinateLatitude.editText?.setText(
            userData.location.coordinates.lat
        )
        binding.textCoordinateLongitude.editText?.setText(
            userData.location.coordinates.lon
        )
    }

    private fun getDate(date: String): String {
        return date.substringBefore(DELIMITER_IN_DATE)
    }

    private fun showErrorScreen(error: String?) {
        showViewError()
        binding.errorTextview.text = error ?: getString(R.string.undefined_error)
        binding.reloadButton.setOnClickListener {
            model.getUsersData(true)
        }
    }

    private fun showViewSuccess() {
        binding.successLinearLayout.visibility = VISIBLE
        binding.loadingFrameLayout.visibility = GONE
        binding.errorLinearLayout.visibility = GONE
    }

    private fun showViewLoading() {
        binding.successLinearLayout.visibility = GONE
        binding.loadingFrameLayout.visibility = VISIBLE
        binding.errorLinearLayout.visibility = GONE
    }

    private fun showViewError() {
        binding.successLinearLayout.visibility = GONE
        binding.loadingFrameLayout.visibility = GONE
        binding.errorLinearLayout.visibility = VISIBLE
    }

    companion object {
        private const val DELIMITER_IN_DATE = "T"
        private const val DATE_FORMAT: String = "yyyy-MM-dd'T'HH:mm:ssZ"
    }
}