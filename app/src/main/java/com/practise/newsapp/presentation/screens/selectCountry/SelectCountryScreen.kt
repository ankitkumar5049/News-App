package com.practise.newsapp.presentation.screens.selectCountry

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.practise.newsapp.common.dimensions.dimen_mdpi
import com.practise.newsapp.presentation.uiComponents.NewsTopBar
import com.practise.newsapp.presentation.uiComponents.SearchBarInputField
import com.practise.newsapp.presentation.uiComponents.SubHeadingText
import com.practise.newsapp.common.utils.CommonContentDescription
import com.practise.newsapp.common.utils.CommonString
import com.practise.newsapp.ui.theme.NewsAppTheme

@Composable
fun SelectCountryScreen(viewModel: SelectCountryViewModel) {

    val context = LocalContext.current

    LaunchedEffect(key1 = Unit) {
        val countries = viewModel.loadCountriesFromAssets(context)
        viewModel.state = viewModel.state.copy(
            countryList = countries,
            filteredCountryList = countries
        )
    }


    Box {
        Scaffold(
            modifier = Modifier
                .background(NewsAppTheme.customColors.topBar)
                .systemBarsPadding(),
            topBar = {
                NewsTopBar(
                    title = CommonString.SELECT_YOUR_COUNTRY,
                )
            }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(
                        start = dimen_mdpi.x_2_0,
                        end = dimen_mdpi.x_2_0,
                        bottom = dimen_mdpi.x_2_0
                    )
                    .padding(innerPadding)
            ) {
                SearchBarInputField(
                    value = viewModel.state.query,
                    onValueChange = {
                        viewModel.state = viewModel.state.copy(
                            query = it,
                            filteredCountryList = viewModel.filterCountryList(it)
                        )
                    },
                    trailingIcon = {
                        Icon(
                            Icons.Filled.Search,
                            contentDescription = CommonContentDescription.TOGGLE_PASSWORD_VISIBILITY,
                            modifier = Modifier
                                .requiredSize(48.dp)
                                .padding(dimen_mdpi.x_1_25)
                        )
                    },
                )

                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    items(viewModel.state.filteredCountryList.size) { index ->
                        CountryListItem(
                            country = viewModel.state.filteredCountryList[index]
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun CountryListItem(
    country: Country,
    modifier: Modifier = Modifier
){
    Row(
        modifier = Modifier
            .padding(dimen_mdpi.x_2_0)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Start
    ) {
        SubHeadingText(
            inputText = country.flag
        )

        Spacer(modifier = Modifier.padding(dimen_mdpi.x_1_25))

        SubHeadingText(
            inputText = country.name
        )

        Spacer(modifier = Modifier.padding(dimen_mdpi.x_1_25))
    }
}