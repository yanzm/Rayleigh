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
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.ExperimentalResourceApi
import ui.theme.RayleighTheme

@OptIn(ExperimentalResourceApi::class)
@Composable
fun App(
    onOpenBrowser: (String) -> Unit = {}
) {
    RayleighTheme {
        var greetingText by remember { mutableStateOf("Hello, World!") }
        var showImage by remember { mutableStateOf(false) }

        var handleOrEmail by remember { mutableStateOf("") }
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
                value = handleOrEmail,
                onValueChange = {
                    handleOrEmail = it
//                    valid = viewModel.isLoginParamValid(serviceProvider, handleOrEmail, password)
                },
                label = { Text("Username or email address") },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Ascii,
                    imeAction = ImeAction.Next
                ),
                maxLines = 1,
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(8.dp))

            OutlinedTextField(
                value = appPassword,
                onValueChange = {
                    appPassword = it
//                    valid = viewModel.isLoginParamValid(serviceProvider, handleOrEmail, password)
                },
                label = { Text("App Password") },
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                ),
                maxLines = 1,
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(8.dp))

            Button(
                onClick = {},
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Login")
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

//            AnimatedVisibility(showImage) {
//                Image(
//                    painterResource("compose-multiplatform.xml"),
//                    null
//                )
//            }
        }
    }
}

expect fun getPlatformName(): String
