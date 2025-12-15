package com.practise.newsapp.presentation.uiComponents

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.TextUnit
import com.practise.newsapp.common.dimensions.dimen_mdpi
import com.practise.newsapp.common.utils.CommonUtilities
import com.practise.newsapp.common.utils.Constants
import com.practise.newsapp.ui.theme.BluePrimary
import com.practise.newsapp.ui.theme.NewsAppTheme
import com.practise.newsapp.ui.theme.NewsAppTheme.customColors
import com.practise.newsapp.ui.theme.NewsAppTheme.dimens
import com.practise.newsapp.ui.theme.OsloGray


@Composable
fun CommonTextInputFields(
    modifier: Modifier = Modifier,
    rowModifier: Modifier = Modifier,
    enabled: Boolean = true,
    value: String,
    maxChar: Int,
    borderColor: Color = Color.Black,
    onValueChange: (newValue: String) -> Unit,
    onFocusedChange: ((FocusState) -> Unit)? = null,
    labelString: String = Constants.EMPTY_STRING,
    labelTextColor: Color? = null,
    labelResource: Int = 0,
    readOnly: Boolean = false,
    backgroundColor: Color = customColors.textFieldBackground,
    useDisabledColorsOnly: Boolean = false,
    maxLines: Int = 1,
    singleLine: Boolean = true,
    trailingIcon: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    errorText: String = Constants.EMPTY_STRING,
    errorTextColor: Color = MaterialTheme.colorScheme.error,
    isSensitiveInformation: Boolean = false,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    enforceVisualTransformation: Boolean = false,
    semanticName: String,
    labelAsteriskRequired: Boolean = false,
    textFieldColors: TextFieldColors = if (useDisabledColorsOnly) {
        TextFieldDefaults.colors(
            disabledTextColor = OsloGray,
            disabledIndicatorColor = Color.Transparent,
            disabledContainerColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            cursorColor = BluePrimary,
            errorCursorColor = BluePrimary,
            focusedLabelColor = MaterialTheme.colorScheme.primary,
            unfocusedLabelColor = OsloGray
        )
    } else {
        TextFieldDefaults.colors(
            disabledTextColor = Color.Black,
            disabledIndicatorColor = Color.Transparent,
            disabledContainerColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            cursorColor = BluePrimary,
            errorCursorColor = BluePrimary,
            focusedLabelColor = MaterialTheme.colorScheme.primary,
            unfocusedLabelColor = OsloGray
        )
    }
){
    val keyboardController = LocalSoftwareKeyboardController.current
    var isFocused by remember { mutableStateOf(false) }
    var errorMsg by remember { mutableStateOf(errorText) }

    if(isSensitiveInformation){
        CommonUtilities.DisableScreenCapture()
    }

    Row(modifier = rowModifier) {
        Column {
            Text(
                buildAnnotatedString {
                    append(labelString)
                    if (labelAsteriskRequired) {
                        withStyle(style = SpanStyle(color = Color.Red)) {
                            append("*")
                        }
                    }
                },
                style = MaterialTheme.typography.bodySmall.copy(
                    fontWeight = FontWeight.Normal,
                ),
                color = labelTextColor ?: customColors.secondPrimary
            )


            Spacer(Modifier.height(dimen_mdpi.x_0_75))

            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .onFocusChanged {
                        isFocused = it.isFocused
                        onFocusedChange?.invoke(it)
                    }
                    .clip(RoundedCornerShape(dimens.x_4_dp))
                    .background(backgroundColor)
                    .border(
                        width = dimens.x_1_dp,
//                        color = borderColor
//                            ?: if (isFocused) customColors.border else Color.Black,
                        color = borderColor,
                        shape = RoundedCornerShape(dimens.x_4_dp)
                    )
                    .then(modifier)
                    .semantics { contentDescription = semanticName },
                enabled = enabled,
                readOnly = readOnly,
                value = value,
                onValueChange = {
                    if (it.length <= maxChar) {
                        errorMsg = Constants.EMPTY_STRING
                        onValueChange(CommonUtilities.trimSpaces(it))
                    }
                },
                textStyle = MaterialTheme.typography.bodySmall.copy(
                    fontWeight = FontWeight.Normal,
                ),
                maxLines = maxLines,
                singleLine = singleLine,
                trailingIcon = trailingIcon,
                leadingIcon = leadingIcon,
                isError = errorMsg.isNotEmpty(),
                colors = textFieldColors,
                visualTransformation = if (enforceVisualTransformation || !isFocused) visualTransformation else VisualTransformation.None
//                label = {
//                    if(labelAsteriskRequired){
//                        LabelWithAsterisk(
//                            labelString = labelString,
//                            labelId = labelResource,
//                            value = value,
//                            labelTextColor = labelTextColor,
//                            isFocused = isFocused
//                        )
//                    }else{
//                        Text(
//                            labelString.ifEmpty { stringResource(id = labelResource) },
//                            style = LocalTextStyle.current.copy(
//                                fontWeight = FontWeight.Normal,
//                                fontSize = if (value.isNotEmpty() || isFocused) NewsAppTheme.fontSizes.x_0_80 else NewsAppTheme.fontSizes.x_1_2,
//                                color = labelTextColor
//                                    ?: if ((value.isEmpty() && !isFocused) || useDisabledColorsOnly) {
//                                        Color.Black
//                                    } else {
//                                        MaterialTheme.colorScheme.primary
//                                    }
//                            )
//                        )
//                    }
//                }

            )

            if (errorText.isNotEmpty()) {
                Text(
                    buildAnnotatedString {
//                        if (errorMsg.isNotEmpty()) {
                            withStyle(style = SpanStyle(color = Color.Red)) {
                                append("!")
                                append(errorText)
                            }
//                        }

                    },
                    modifier = Modifier.padding(top = dimen_mdpi.x_0_75),
                    style = MaterialTheme.typography.bodySmall.copy(
                        fontWeight = FontWeight.Normal,
                    ),
                    color = errorTextColor,
                )
            }
        }
    }
}

@Composable
fun HeadingText(
    inputText: String,
    modifier: Modifier = Modifier,
    textColor: Color,
){
    Text(
        text = inputText,
        style = MaterialTheme.typography.displayMedium.copy(
            fontWeight = FontWeight.Bold,
        ),
        color = textColor,
        modifier = modifier
    )
}

@Composable
fun LargeHeadingText(
    inputText: String,
    modifier: Modifier = Modifier,
    textColor: Color,
){
    Text(
        text = inputText,
        style = MaterialTheme.typography.displayLarge.copy(
            fontWeight = FontWeight.Bold,
        ),
        color = textColor,
        modifier = modifier
    )
}

@Composable
fun SubHeadingText(
    inputText: String,
    fontWeight: FontWeight = FontWeight.Normal,
    modifier: Modifier = Modifier,
    textColor: Color = customColors.secondPrimary,
    maxLines: Int = 10
){
    Text(
        text = inputText,
        style = MaterialTheme.typography.bodyMedium.copy(
            fontWeight = fontWeight,
        ),
        color = textColor,
        modifier = modifier,
        maxLines = maxLines
    )
}

@Composable
fun CommonTextField(
    inputText: String,
    clickable: Boolean = false,
    link: String? = null,
    textAlign: TextAlign = TextAlign.Center,
    isLink: Boolean = false,
    style: TextStyle = LocalTextStyle.current.copy(
        fontWeight = FontWeight.Normal,
        fontSize = NewsAppTheme.fontSizes.x_1_25,
    ),
    onClick: () -> Unit? = {},
    modifier: Modifier,
    contentColor: Color = customColors.textPrimary
){
    Text(
        text = inputText,
        color = if(isLink) customColors.primary else contentColor,
        textAlign = textAlign,
        style = style,
        modifier = if(clickable) Modifier.clickable {
            onClick()
        } else Modifier.then(modifier)
    )
}

@Composable
fun SubtitleText(
    modifier: Modifier = Modifier,
    align: TextAlign = TextAlign.Center,
    color: Color = NewsAppTheme.customColors.primary,
    text: String = "",
    ellipsis: Boolean = false,
    lineHeight: TextUnit = TextUnit.Unspecified,
    fontWeight: FontWeight = FontWeight.Normal,
    style: TextStyle = MaterialTheme.typography.headlineSmall,
    textDecoration: TextDecoration? = null
) {
    Text(
        textAlign = align,
        text = text,
        color = color,
        style = style,
        overflow = if (ellipsis) TextOverflow.Ellipsis else TextOverflow.Clip,
        maxLines = if (ellipsis) 1 else Int.MAX_VALUE,
        lineHeight = lineHeight,
        fontWeight = fontWeight,
        modifier = modifier,
        textDecoration =textDecoration
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommonDropdownField(
    modifier: Modifier = Modifier,
    rowModifier: Modifier = Modifier,
    enabled: Boolean = true,
    selectedValue: String,
    options: List<String>,
    onValueSelected: (String) -> Unit,
    labelString: String = Constants.EMPTY_STRING,
    labelTextColor: Color? = null,
    backgroundColor: Color = customColors.textFieldBackground,
    borderColor: Color = Color.Black,
    errorText: String = Constants.EMPTY_STRING,
    errorTextColor: Color = MaterialTheme.colorScheme.error,
    semanticName: String,
    labelAsteriskRequired: Boolean = false,
    trailingIcon: @Composable (() -> Unit)? = {
        Icon(
            imageVector = Icons.Default.ArrowDropDown,
            contentDescription = null
        )
    },
    textFieldColors: TextFieldColors = TextFieldDefaults.colors(
        disabledIndicatorColor = Color.Transparent,
        focusedIndicatorColor = Color.Transparent,
        unfocusedIndicatorColor = Color.Transparent,
        disabledContainerColor = Color.Transparent,
        focusedContainerColor = Color.Transparent,
        unfocusedContainerColor = Color.Transparent
    )
) {
    var expanded by remember { mutableStateOf(false) }

    Row(modifier = rowModifier) {
        Column {
            // Label
            Text(
                buildAnnotatedString {
                    append(labelString)
                    if (labelAsteriskRequired) {
                        withStyle(style = SpanStyle(color = Color.Red)) { append("*") }
                    }
                },
                style = MaterialTheme.typography.bodySmall,
                color = labelTextColor ?: customColors.secondPrimary
            )

            Spacer(Modifier.height(dimen_mdpi.x_0_75))

            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { if (enabled) expanded = !expanded }
            ) {
                TextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .menuAnchor()
                        .clip(RoundedCornerShape(dimens.x_4_dp))
                        .background(backgroundColor)
                        .border(
                            width = dimens.x_1_dp,
                            color = borderColor,
                            shape = RoundedCornerShape(dimens.x_4_dp)
                        )
                        .semantics { contentDescription = semanticName }
                        .then(modifier),
                    value = selectedValue,
                    onValueChange = {},
                    readOnly = true,
                    enabled = enabled,
                    trailingIcon = trailingIcon,
                    isError = errorText.isNotEmpty(),
                    textStyle = MaterialTheme.typography.bodySmall,
                    colors = textFieldColors
                )

                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    options.forEach { option ->
                        DropdownMenuItem(
                            text = { Text(option) },
                            onClick = {
                                onValueSelected(option)
                                expanded = false
                            }
                        )
                    }
                }
            }

            if (errorText.isNotEmpty()) {
                Text(
                    text = "! $errorText",
                    modifier = Modifier.padding(top = dimen_mdpi.x_0_75),
                    style = MaterialTheme.typography.bodySmall,
                    color = errorTextColor
                )
            }
        }
    }
}


/*@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommonDatePickerField(
    modifier: Modifier = Modifier,
    rowModifier: Modifier = Modifier,
    enabled: Boolean = true,
    selectedDate: String,
    onDateSelected: (String) -> Unit,
    labelString: String = Constants.EMPTY_STRING,
    labelTextColor: Color? = null,
    backgroundColor: Color = customColors.textFieldBackground,
    borderColor: Color = Color.Black,
    errorText: String = Constants.EMPTY_STRING,
    errorTextColor: Color = MaterialTheme.colorScheme.error,
    semanticName: String,
    labelAsteriskRequired: Boolean = false,
    dateFormatter: (Long) -> String,
    trailingIcon: @Composable (() -> Unit)? = {
        Icon(
            imageVector = Icons.Default.CalendarToday,
            contentDescription = null
        )
    },
    textFieldColors: TextFieldColors = TextFieldDefaults.colors(
        disabledIndicatorColor = Color.Transparent,
        focusedIndicatorColor = Color.Transparent,
        unfocusedIndicatorColor = Color.Transparent,
        disabledContainerColor = Color.Transparent,
        focusedContainerColor = Color.Transparent,
        unfocusedContainerColor = Color.Transparent
    )
) {
    var showDialog by remember { mutableStateOf(false) }
    val datePickerState = rememberDatePickerState()

    Row(modifier = rowModifier) {
        Column {
            // Label
            Text(
                buildAnnotatedString {
                    append(labelString)
                    if (labelAsteriskRequired) {
                        withStyle(style = SpanStyle(color = Color.Red)) { append("*") }
                    }
                },
                style = MaterialTheme.typography.bodySmall,
                color = labelTextColor ?: customColors.secondPrimary
            )

            Spacer(Modifier.height(dimen_mdpi.x_0_75))

            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(dimens.x_4_dp))
                    .background(backgroundColor)
                    .border(
                        width = dimens.x_1_dp,
                        color = borderColor,
                        shape = RoundedCornerShape(dimens.x_4_dp)
                    )
                    .clickable(enabled = enabled) { showDialog = true }
                    .semantics { contentDescription = semanticName }
                    .then(modifier),
                value = selectedDate,
                onValueChange = {},
                readOnly = true,
                enabled = enabled,
                trailingIcon = trailingIcon,
                isError = errorText.isNotEmpty(),
                textStyle = MaterialTheme.typography.bodySmall,
                colors = textFieldColors
            )

            if (errorText.isNotEmpty()) {
                Text(
                    text = "! $errorText",
                    modifier = Modifier.padding(top = dimen_mdpi.x_0_75),
                    style = MaterialTheme.typography.bodySmall,
                    color = errorTextColor
                )
            }
        }
    }

    if (showDialog) {
        DatePickerDialog(
            onDismissRequest = { showDialog = false },
            confirmButton = {
                TextButton(
                    onClick = {
                        datePickerState.selectedDateMillis?.let {
                            onDateSelected(dateFormatter(it))
                        }
                        showDialog = false
                    }
                ) { Text("OK") }
            },
            dismissButton = {
                TextButton(onClick = { showDialog = false }) {
                    Text("Cancel")
                }
            }
        ) {
            DatePicker(state = datePickerState)
        }
    }
}*/

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommonDatePickerField(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    selectedDate: String,
    onDateSelected: (String) -> Unit,
    backgroundColor: Color = customColors.textFieldBackground,
    labelString: String,
    semanticName: String,
    labelAsteriskRequired: Boolean = false,
    errorText: String = Constants.EMPTY_STRING,
    dateFormatter: (Long) -> String,
    trailingIcon: @Composable (() -> Unit)? = {
        Icon(
            imageVector = Icons.Default.CalendarToday,
            contentDescription = null
        )
    },
    textFieldColors: TextFieldColors = TextFieldDefaults.colors(
        disabledTextColor = Color.White,
        disabledIndicatorColor = Color.Transparent,
        focusedIndicatorColor = Color.Transparent,
        unfocusedIndicatorColor = Color.Transparent,
        disabledContainerColor = Color.Transparent,
        focusedContainerColor = Color.Transparent,
        unfocusedContainerColor = Color.Transparent
    )
) {
    var showDialog by remember { mutableStateOf(false) }
    val datePickerState = rememberDatePickerState()

    Column {

        // Label
        Text(
            buildAnnotatedString {
                append(labelString)
                if (labelAsteriskRequired) {
                    withStyle(style = SpanStyle(color = Color.Red)) {
                        append("*")
                    }
                }
            },
            style = MaterialTheme.typography.bodySmall,
            color = customColors.secondPrimary
        )

        Spacer(Modifier.height(dimen_mdpi.x_0_75))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(backgroundColor)
                .clip(RoundedCornerShape(dimens.x_4_dp))
                .border(
                    width = dimens.x_1_dp,
                    color = Color.Black,
                    shape = RoundedCornerShape(dimens.x_4_dp)
                )
                .clickable(enabled = enabled) {
                    showDialog = true
                }
                .semantics { contentDescription = semanticName }
        ) {

            TextField(
                value = selectedDate,
                onValueChange = {},
                readOnly = true,
                enabled = false,
                trailingIcon = trailingIcon,
                textStyle = MaterialTheme.typography.bodySmall,
                colors = textFieldColors,
                modifier = Modifier.fillMaxWidth()
            )
        }

        if (errorText.isNotEmpty()) {
            Text(
                text = "! $errorText",
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(top = dimen_mdpi.x_0_75)
            )
        }
    }

    /** Date Picker Dialog */
    if (showDialog) {
        DatePickerDialog(
            onDismissRequest = { showDialog = false },
            confirmButton = {
                TextButton(onClick = {
                    datePickerState.selectedDateMillis?.let {
                        onDateSelected(dateFormatter(it))
                    }
                    showDialog = false
                }) {
                    Text("OK")
                }
            },
            dismissButton = {
                TextButton(onClick = { showDialog = false }) {
                    Text("Cancel")
                }
            }
        ) {
            DatePicker(state = datePickerState)
        }
    }
}



