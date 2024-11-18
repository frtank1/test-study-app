package com.example.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.theme.Green
import com.example.theme.TextColor
import com.example.theme.White
import com.example.theme.robotoFamily


@Composable
fun TextNormal(
    modifier: Modifier = Modifier,
    text: String = "",
    color: Color = TextColor,
    fontWeight: FontWeight = FontWeight.Normal,
    textAlign: TextAlign = TextAlign.Center,
    fontSize: TextUnit = TextUnit.Unspecified,
) {
    Text(
        modifier = modifier,
        text = text,
        color = color,
        fontFamily = robotoFamily,
        fontWeight = fontWeight,
        textAlign = textAlign,
        fontSize = fontSize,
    )
}

@Composable
fun TextWithUnderline(
    modifier:Modifier = Modifier
        .padding(top = 10.dp)
        .fillMaxWidth(),
    onClick: (() -> Unit)? = null,
    color: Color = Green,
    textFirst: String? = null,
    textSecond:String
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        textFirst?.let {
            TextNormal(
                text = textFirst,
                color = White,
                fontSize = 12.sp
            )
        }
        Text(
            modifier = Modifier
                .padding(horizontal = 2.dp)
                .clickable { onClick?.invoke() },
            text = textSecond,
            color = color,
            fontSize = 12.sp,
        )
    }
}