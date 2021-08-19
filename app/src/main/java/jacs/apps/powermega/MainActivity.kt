package jacs.apps.powermega

import android.os.Bundle
import android.widget.StackView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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

    Row(modifier = Modifier.fillMaxWidth(),verticalAlignment = Alignment.CenterVertically) {
        Box(modifier = Modifier.weight(1f).size(75.dp,75.dp)){
            Image(painter = painterResource(id = R.drawable.power1),"ball 1",modifier = Modifier.fillMaxSize())
            Text(
                "1",
                fontSize = 25.sp,
                textAlign = TextAlign.Center,
                style = TextStyle(textDecoration = TextDecoration.Underline),
                fontWeight = FontWeight.Bold,
                modifier = Modifier.fillMaxSize().paddingFromBaseline(top = 45.dp))
        }
        Box(modifier = Modifier.weight(1f).size(75.dp,75.dp)){
            Image(painter = painterResource(id = R.drawable.power1),"ball 2",modifier = Modifier.fillMaxSize())
            Text("2",
                fontSize = 25.sp,textAlign = TextAlign.Center,
                style = TextStyle(textDecoration = TextDecoration.Underline),
                fontWeight = FontWeight.Bold,
                modifier = Modifier.fillMaxSize().paddingFromBaseline(top = 45.dp))
        }
        Box(modifier = Modifier.weight(1f).size(75.dp,75.dp)){
            Image(painter = painterResource(id = R.drawable.power1),"ball 3",modifier = Modifier.fillMaxSize())
            Text("3",
                fontSize = 25.sp,textAlign = TextAlign.Center,
                style = TextStyle(textDecoration = TextDecoration.Underline),
                fontWeight = FontWeight.Bold,
                modifier = Modifier.fillMaxSize().paddingFromBaseline(top = 45.dp))
        }
        Box(modifier = Modifier.weight(1f).size(75.dp,75.dp)){
            Image(painter = painterResource(id = R.drawable.power1),"ball 4",modifier = Modifier.fillMaxSize())
            Text("4",
                fontSize = 25.sp,textAlign = TextAlign.Center,
                style = TextStyle(textDecoration = TextDecoration.Underline),
                fontWeight = FontWeight.Bold,
                modifier = Modifier.fillMaxSize().paddingFromBaseline(top = 45.dp))
        }
        Box(modifier = Modifier.weight(1f).size(75.dp,75.dp)){
            Image(painter = painterResource(id = R.drawable.power1),"ball 5",modifier = Modifier.fillMaxSize())
            Text("5",
                fontSize = 25.sp,textAlign = TextAlign.Center,
                style = TextStyle(textDecoration = TextDecoration.Underline),
                fontWeight = FontWeight.Bold,
                modifier = Modifier.fillMaxSize().paddingFromBaseline(top = 45.dp))
        }
        Box(modifier = Modifier.weight(1f).size(75.dp,75.dp)){
            Image(painter = painterResource(id = R.drawable.powerstar1),"powerball",modifier = Modifier.fillMaxSize())
            Text("6",
                fontSize = 25.sp,textAlign = TextAlign.Center,
                style = TextStyle(textDecoration = TextDecoration.Underline),
                fontWeight = FontWeight.Bold,
                modifier = Modifier.fillMaxSize().paddingFromBaseline(top = 45.dp))
        }




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