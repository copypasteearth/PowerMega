package jacs.apps.powermega

import android.os.Bundle
import android.widget.StackView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import jacs.apps.powermega.ui.theme.PowerMegaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PowerMegaTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {

    Row(modifier = Modifier.fillMaxWidth()) {
        Image(painter = painterResource(id = R.drawable.power1),"ball 1",modifier = Modifier.weight(1f).size(50.dp,50.dp))
        Image(painter = painterResource(id = R.drawable.power1),"ball 2",modifier = Modifier.weight(1f).size(50.dp,50.dp))
        Image(painter = painterResource(id = R.drawable.power1),"ball 3",modifier = Modifier.weight(1f).size(50.dp,50.dp))
        Image(painter = painterResource(id = R.drawable.power1),"ball 4",modifier = Modifier.weight(1f).size(50.dp,50.dp))
        Image(painter = painterResource(id = R.drawable.power1),"ball 5",modifier = Modifier.weight(1f).size(50.dp,50.dp))
        Image(painter = painterResource(id = R.drawable.powerstar1),"powerball",modifier = Modifier.weight(1f).size(50.dp,50.dp))



    }
    //Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    PowerMegaTheme {
        Greeting("Android")
    }
}