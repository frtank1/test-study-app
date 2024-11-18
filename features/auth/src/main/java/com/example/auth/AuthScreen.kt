package kz.secret_santa_jusan.presentation.Auth

import android.app.Activity
import android.os.Parcelable
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.example.auth.R
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
import kotlinx.parcelize.Parcelize

@Parcelize
class AuthScreen : CoreBaseScreen(), Parcelable {

    @Composable
    override fun Content() {
        val viewModel = getScreenModel<AuthViewModel>()
        val navigator = LocalNavigator.currentOrThrow
        val navigationEvent =
            viewModel.navigationEvent.collectAsStateWithLifecycle().value.getValue()
        when (navigationEvent) {
            is NavigationEvent.Default -> {}
            is NavigationEvent.Back -> navigator.pop()
        }
        // SubscribeError(viewModel)
        AuthContent(viewModel = viewModel)
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AuthContentPreview() {
    AuthContent(AuthViewModelPreview())
}

@Composable
fun AuthContent(viewModel: IAuthViewModel) {
    val state = viewModel.state.collectAsStateWithLifecycle().value
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Dark)
            .padding(horizontal = 16.dp)

        //.verticalScroll(rememberScrollState())
    ) {
        when (state) {
            is AuthState.Default -> {
                TextNormal(
                    modifier = Modifier.padding(top = 140.dp),
                    text = stringResource(R.string.login),
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
                    value = "",
                    onValueChange = {},
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
                    value = "",
                    onValueChange = {},
                    placeholder = stringResource(id = R.string.enter_password)
                )

                ButtonNormal(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 24.dp),
                    text = stringResource(id = R.string.login),
                    colors = ButtonDefaults.buttonColors(Green)
                ) {

                }

                TextWithUnderline(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    textFirst = stringResource(id = R.string.no_account),
                    textSecond = stringResource(
                        id = R.string.registration,
                    ),
                )

                TextWithUnderline(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp),
                    textSecond = stringResource(
                        id = R.string.forgot_password,
                    ),
                )
                HorizontalDivider(modifier = Modifier.padding(top = 32.dp))
                Row(modifier = Modifier.fillMaxWidth().padding(top = 32.dp)) {
                    ButtonWithIcon(
                        modifier = Modifier.weight(1f)
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
                        modifier = Modifier.weight(1f)
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