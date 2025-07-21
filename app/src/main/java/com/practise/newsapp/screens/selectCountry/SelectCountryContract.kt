package com.practise.newsapp.screens.selectCountry

import com.practise.newsapp.common.utils.Constants

class SelectCountryContract {
    data class State(
        var query: String = Constants.EMPTY_STRING,
        var countryList: List<Country> = emptyList(),
        var filteredCountryList: List<Country> = emptyList()
    )
}