package com.qmdeve.location.demo

import android.location.Location
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialExpressiveTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.core.view.WindowCompat
import com.qmdeve.location.FastLocation
import com.qmdeve.location.LocationHandle
import com.qmdeve.location.LocationCallback

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val insetsController = WindowCompat.getInsetsController(this.window, this.window.decorView)
        insetsController.isAppearanceLightStatusBars = true
        actionBar?.hide()
        setContent {
            MaterialExpressiveTheme {
                Content()
            }
        }
    }

    @Composable
    fun Content() {
        var locationHandle: LocationHandle? = null

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = {
                    // Single Location
                    FastLocation.singleLocation(
                        object : LocationCallback {
                            override fun onLocation(location: Location) {
                                // Callback
                                Toast.makeText(this@MainActivity, "${location.latitude}, ${location.longitude}", Toast.LENGTH_SHORT).show()
                            }
                        })
                }
            ) {
                Text(stringResource(R.string.single_location))
            }

            Button(
                onClick = {
                    // Start Location
                    locationHandle = FastLocation.startLocation(
                        object : LocationCallback {
                            override fun onLocation(location: Location) {
                                // Callback
                                Toast.makeText(this@MainActivity, "${location.latitude}, ${location.longitude}", Toast.LENGTH_SHORT).show()
                            }
                        }
                    )
                }
            ) {
                Text(stringResource(R.string.start_location))
            }

            Button(
                onClick = {
                    // Stop Location
                    locationHandle?.stop()
                }
            ) {
                Text(stringResource(R.string.stop_location))
            }
        }
    }
}