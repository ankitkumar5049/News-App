package com.practise.newsapp.common.utils

data class ApiState(
    var isLoading: Boolean = false,
    var pullToRefresh: Boolean = false,
    var isDeleteApiSuccessFul: Boolean = false,
    var text: String = Constants.EMPTY_STRING,//instead of using this use [apiErrorMessage] as title and [apiErrorDescription] as description for NYEBottomSheetView
    var textIncorrectPassword: String = Constants.EMPTY_STRING,
    var isApiSuccessful: Boolean = true,
    var enabled: Boolean = true,
    var successScreen: Boolean = false,
    var moveToDownTime: Boolean = false,
    var apiErrorMessage: String = Constants.EMPTY_STRING,
    var customErrorMessage: String = Constants.EMPTY_STRING,
    var customErrorDescription: String = Constants.EMPTY_STRING,
    var apiErrorDescription: String = Constants.EMPTY_STRING,//use this property for showing api error body description
    var showErrorBottomSheet: Boolean = false,//will remove this after zero usage
    var isInCoolingPeriod: Boolean = false,
    var isInitialApiSuccess: Boolean = false,
    val isBottomSheetInitialized: Boolean = false,
    var coolingPeriodTimeLeft: Double = 0.0
)
