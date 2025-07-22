package com.practise.newsapp.presentation.screens.selectCountry

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.practise.newsapp.common.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SelectCountryViewModel @Inject constructor(): BaseViewModel() {
    var state by mutableStateOf(SelectCountryContract.State())


    fun loadCountriesFromAssets(context: Context): List<Country> {
        val json = context.assets.open("countries.json").bufferedReader().use { it.readText() }
        val type = object : TypeToken<List<Country>>() {}.type
        return Gson().fromJson(json, type)
    }

    fun filterCountryList(query: String): List<Country> {
        return state.countryList.filter { country ->
            country.name.contains(query, ignoreCase = true)
        }

    }
}