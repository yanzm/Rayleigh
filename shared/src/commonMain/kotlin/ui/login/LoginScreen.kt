package ui.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

@Composable
fun LoginScreen(
    viewModel: LoginViewModel,
    onOpenBrowser: (String) -> Unit,
) {
    val uiState = viewModel.uiState.collectAsState().value
    LoginContent(
        uiState = uiState,
        onOpenBrowser = onOpenBrowser,
        onClickLogin = { username, appPassword -> viewModel.login(username, appPassword) },
        onInputChanged = viewModel::consumeError,
    )
}

@Composable
private fun LoginContent(
    uiState: LoginViewModel.UiState,
    onOpenBrowser: (String) -> Unit,
    onClickLogin: (String, String) -> Unit,
    onInputChanged: () -> Unit,
) {
    var username by remember { mutableStateOf("") }
    var appPassword by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
    ) {
        Text("Sign into")
        Button(onClick = {}, enabled = false) {
            Text("Bluesky Social")
        }

        Spacer(Modifier.height(16.dp))

        Text("Account")

        Spacer(Modifier.height(8.dp))

        OutlinedTextField(
            value = username,
            onValueChange = {
                username = it
                onInputChanged()
            },
            label = { Text("Username or email address") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            ),
            maxLines = 1,
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(8.dp))

        var showPassword by remember { mutableStateOf(false) }

        OutlinedTextField(
            value = appPassword,
            onValueChange = {
                appPassword = it
                onInputChanged()
            },
            label = { Text("App Password") },
            placeholder = { Text("xxxx-xxxx-xxxx-xxxx") },
            trailingIcon = {
                IconButton(
                    onClick = {
                        showPassword = !showPassword
                    }
                ) {
                    if (showPassword) {
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = "hide password"
                        )
                    } else {
                        Icon(
                            imageVector = Icons.Outlined.Add,
                            contentDescription = "show password"
                        )
                    }
                }
            },
            visualTransformation = if (showPassword) {
                VisualTransformation.None
            } else {
                PasswordVisualTransformation()
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            maxLines = 1,
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(8.dp))

        if (uiState is LoginViewModel.UiState.Error) {
            when (val e = uiState.e) {
                LoginViewModel.LoginError.InvalidAppPassword -> {
                    Text(
                        text = "It seems not App Password",
                        color = MaterialTheme.colors.error,
                    )
                    Spacer(Modifier.height(8.dp))
                }

                is LoginViewModel.LoginError.ApiError -> {
                    Text(
                        text = e.e.message,
                        color = MaterialTheme.colors.error,
                    )
                    Spacer(Modifier.height(8.dp))
                }
            }
        }

        if (uiState is LoginViewModel.UiState.Submitting) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        } else {
            Button(
                onClick = {
                    onClickLogin(username, appPassword)
                },
                enabled = username.isNotEmpty() && appPassword.isNotEmpty(),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Login")
            }
        }

        Spacer(Modifier.height(16.dp))

        TextButton(
            onClick = {
                onOpenBrowser("https://staging.bsky.app/")
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("get App Password from web client")
        }

        Spacer(Modifier.height(16.dp))

        TextButton(
            onClick = {
                onOpenBrowser("https://staging.bsky.app/")
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("create account in web client")
        }
    }
}
