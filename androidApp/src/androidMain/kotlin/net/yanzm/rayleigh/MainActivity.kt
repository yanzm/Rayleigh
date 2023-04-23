package net.yanzm.rayleigh

import MainView
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MainView(
                onOpenBrowser = {
                    startActivity(
                        Intent(Intent.ACTION_VIEW, Uri.parse(it))
                    )
                }
            )
        }
    }
}
