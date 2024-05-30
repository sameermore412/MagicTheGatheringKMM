import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.window.CanvasBasedWindow
import coil3.compose.AsyncImage
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.configureWebResources

@OptIn(ExperimentalComposeUiApi::class, ExperimentalResourceApi::class)
fun main() {
    configureWebResources {
        resourcePathMapping { path -> "./$path" }
    }
    CanvasBasedWindow("ImageViewer") {
        Column {
            Text("Hello")
            Button(onClick = { println("Button clicked") }) {
                Text("Click me")
            }
            Button(onClick = { println("Button clicked") }) {
                Text("Click me")
            }
            Row {
                Button(onClick = { println("Button clicked") }) {
                    Text("Click me")
                }
                Button(onClick = { println("Button clicked") }) {
                    Text("Click me")
                }
            }
            Card(backgroundColor = Color.Blue) {
                Column(modifier = Modifier.background(Color.Red)) {
                    Text("Hello", fontStyle = MaterialTheme.typography.h6.fontStyle)
                    Button(onClick = { println("Button clicked") }) {
                        Text("Click me")
                    }
                    AsyncImage(
                        model = "https://cards.scryfall.io/large/front/2/0/205df311-a72d-40f1-b2ea-9b30274bc9bd.jpg",
                        contentDescription = null,
                    )
                }
            }
        }
    }
}