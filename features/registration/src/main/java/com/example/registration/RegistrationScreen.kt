package com.example.registration

import android.os.Parcelable
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.example.core.base.CoreBaseScreen
import com.example.theme.ClassmatesFirst
import com.example.theme.ClassmatesSecond
import com.example.theme.Dark
import com.example.theme.Green
import com.example.theme.VkButton
import com.example.theme.White
import com.example.views.ButtonNormal
import com.example.views.ButtonWithIcon
import com.example.views.EditText
import com.example.views.TextNormal
import com.example.views.TextWithUnderline
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.parcelize.Parcelize

@Parcelize
class RegistrationScreen : CoreBaseScreen(), Parcelable {

    @Composable
    override fun Content() {
        val viewModel = getScreenModel<RegistrationViewModel>()
        val navigator = LocalNavigator.currentOrThrow
        val navigationEvent =
            viewModel.navigationEvent.collectAsStateWithLifecycle().value.getValue()
        when (navigationEvent) {
            is NavigationEvent.Default -> {}
            is NavigationEvent.Back -> navigator.pop()
        }
        // SubscribeError(viewModel)
        RegistrationContent(viewModel = viewModel)
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun RegistrationContentPreview() {
    RegistrationContent(RegistrationViewModelPreview())
}

@Composable
fun RegistrationContent(viewModel: IRegistrationViewModel) {
    val state = viewModel.state.collectAsStateWithLifecycle().value
    val text = remember {
        MutableStateFlow("")
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Dark)
            .padding(horizontal = 16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        when (state) {
            is RegistrationState.Default -> {
                TextNormal(
                    modifier = Modifier.padding(top = 140.dp),
                    text = stringResource(R.string.Registration),
                    color = White,
                    fontSize = 28.sp
                )

                TextNormal(
                    modifier = Modifier.padding(top = 28.dp),
                    text = stringResource(R.string.Email),
                    color = White,
                    fontSize = 16.sp
                )

                EditText(
                    modifier = Modifier.padding(top = 8.dp),
                    value = state.email,
                    onValueChange = {
                        viewModel.sendEvent(RegistrationEvent.EnterEmail(it))
                    },
                    placeholder = stringResource(id = R.string.placeholder_email)
                )

                TextNormal(
                    modifier = Modifier.padding(top = 16.dp),
                    text = stringResource(R.string.Password),
                    color = White,
                    fontSize = 16.sp
                )

                EditText(
                    modifier = Modifier.padding(top = 8.dp),
                    value = state.firstPassword,
                    onValueChange = {
                        viewModel.sendEvent(RegistrationEvent.EnterPassword(it))
                    },
                    placeholder = stringResource(id = R.string.enter_password)
                )

                TextNormal(
                    modifier = Modifier.padding(top = 16.dp),
                    text = stringResource(R.string.SecondPassword),
                    color = White,
                    fontSize = 16.sp
                )

                EditText(
                    modifier = Modifier.padding(top = 8.dp),
                    value = state.secondPassword,
                    onValueChange = {
                        viewModel.sendEvent(RegistrationEvent.EnterSecondPassword(it))
                    },
                    placeholder = stringResource(id = R.string.repeat_password)
                )

                ButtonNormal(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 24.dp),
                    text = stringResource(id = R.string.Registration),
                    colors = ButtonDefaults.buttonColors(Green)
                ) {
                    viewModel.sendEvent(RegistrationEvent.Registrate)
                }

                TextWithUnderline(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    textFirst = stringResource(id = R.string.yes_account),
                    textSecond = stringResource(
                        id = R.string.Enter,
                    ),
                )



                HorizontalDivider(modifier = Modifier.padding(top = 32.dp))
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 32.dp)) {
                    ButtonWithIcon(
                        modifier = Modifier
                            .weight(1f)
                            .clip(shape = RoundedCornerShape(24.dp))
                            .background(
                                VkButton
                            ),
                        leadIcon = com.example.theme.R.drawable.vklog,
                        tintIcon = White,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Transparent
                        )
                    ) {

                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    ButtonWithIcon(
                        modifier = Modifier
                            .weight(1f)
                            .clip(shape = RoundedCornerShape(24.dp))
                            .background(
                                Brush.linearGradient(
                                    colors = listOf(ClassmatesFirst, ClassmatesSecond),
                                    start = Offset(0f, 0f),
                                    end = Offset(0f, Float.POSITIVE_INFINITY)
                                ),
                            ),
                        leadIcon = com.example.theme.R.drawable.classmateslog,
                        tintIcon = White,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Transparent
                        )
                    ) {

                    }
                }

            }
        }
    }
}