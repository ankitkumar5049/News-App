package com.practise.newsapp.screens.selectCountry

import com.google.gson.annotations.SerializedName

data class Country(
    @SerializedName("name") val name: String,
    @SerializedName("flag") val flag: String,
    @SerializedName("code") val code: String,
    @SerializedName("dial_code") val dialCode: String
)