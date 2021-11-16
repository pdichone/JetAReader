package com.bawp.freader

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.bawp.freader.navigation.ReaderNavigation
import com.bawp.freader.ui.theme.FReaderTheme
import dagger.hilt.android.AndroidEntryPoint


@ExperimentalComposeUiApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FReaderTheme {
                ReaderApp()
            }
        }
    }
}

@ExperimentalComposeUiApi
@Composable
fun ReaderApp() {

    Surface(color = MaterialTheme.colors.background,
        modifier = Modifier.fillMaxSize(), content = {
            Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                ReaderNavigation()

            }
        })

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    FReaderTheme {
    }
}