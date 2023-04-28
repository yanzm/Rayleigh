package net.yanzm.rayleigh

import MainView
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import auth.AuthRepository
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {

    private val authRepository: AuthRepository by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MainView(
                authRepository = authRepository,
                onOpenBrowser = {
                    startActivity(
                        Intent(Intent.ACTION_VIEW, Uri.parse(it))
                    )
                }
            )
        }
    }
}
