package com.example.views


import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.theme.LightBlack
import com.example.theme.System_Error
import com.example.theme.White
import org.w3c.dom.Text


@Composable
fun  EditText(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    placeholder: String? = null,
    maxLines: Int = 1,
    minLines: Int = 1,
    isError: Boolean = false,
    readOnly: Boolean = false,
    singleLine: Boolean = false,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    containerColor: Color = LightBlack,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
) {

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = { TextNormal(text = placeholder ?: "",fontSize = 16.sp,  modifier = Modifier.alpha(0.6f))},
        modifier = modifier,
        isError = isError,
        keyboardOptions = keyboardOptions,
        enabled = enabled,
        maxLines = maxLines,
        minLines = minLines,
        readOnly = readOnly,
        singleLine = singleLine,
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        visualTransformation = visualTransformation,
        shape = RoundedCornerShape(8.dp),
        colors = TextFieldDefaults.colors(
            focusedTextColor = White,
            unfocusedTextColor = White,
            cursorColor = White,
             errorCursorColor = White,
            focusedContainerColor = containerColor,
            unfocusedContainerColor = containerColor,
            focusedIndicatorColor = LightBlack,
            unfocusedIndicatorColor = LightBlack,
            errorIndicatorColor = System_Error,
            errorLabelColor = System_Error,
            errorTrailingIconColor = System_Error,
        )
    )
}