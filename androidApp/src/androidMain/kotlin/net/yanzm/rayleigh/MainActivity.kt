package net.yanzm.rayleigh

import MainView
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import di.AppComponent

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MainView(
                appComponent = AppComponent(),
                onOpenBrowser = {
                    startActivity(
                        Intent(Intent.ACTION_VIEW, Uri.parse(it))
                    )
                }
            )
        }
    }
}
