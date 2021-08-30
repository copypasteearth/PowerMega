package jacs.apps.powermega.Views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import jacs.apps.powermega.ui.theme.PowerMegaTheme
import jacs.apps.powermega.R
import powerball.apps.jacs.powerball.MyTicket
import jacs.apps.powermega.data.WinningTicket

class Ticket {

    @Composable
    fun TicketView(myTicket: MyTicket, winingTicket: WinningTicket, isActualTicket: Boolean, isPowerball: Boolean, numTicket: Int) {
        Column(
        ){
            if(isActualTicket) Text("Winning Ticket",fontSize = 25.sp) else Text("Ticket " + numTicket,fontSize = 25.sp,)
            Row(modifier = Modifier.fillMaxWidth().background(color= Color.Black),verticalAlignment = Alignment.CenterVertically) {
                Box(modifier = Modifier.weight(1f).size(75.dp,75.dp)){
                    Image(painter = painterResource(id = if(myTicket.getNumber(0) == winingTicket.getNumber(0) && !isActualTicket) R.drawable.ball1star else R.drawable.ball1),"ball 1",modifier = Modifier.fillMaxSize())
                    Text(
                        if (isActualTicket) winingTicket.getNumber(0).toString() else myTicket.getNumber(0).toString(),
                        fontSize = 25.sp,
                        textAlign = TextAlign.Center,
                        style = TextStyle(textDecoration = TextDecoration.Underline),
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.fillMaxSize().paddingFromBaseline(top = 45.dp))
                }
                Box(modifier = Modifier.weight(1f).size(75.dp,75.dp)){
                    Image(painter = painterResource(id = if(myTicket.getNumber(1) == winingTicket.getNumber(1) && !isActualTicket) R.drawable.ball1star else R.drawable.ball1),"ball 2",modifier = Modifier.fillMaxSize())
                    Text(if (isActualTicket) winingTicket.getNumber(1).toString() else myTicket.getNumber(1).toString(),
                        fontSize = 25.sp,textAlign = TextAlign.Center,
                        style = TextStyle(textDecoration = TextDecoration.Underline),
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.fillMaxSize().paddingFromBaseline(top = 45.dp))
                }
                Box(modifier = Modifier.weight(1f).size(75.dp,75.dp)){
                    Image(painter = painterResource(id = if(myTicket.getNumber(2) == winingTicket.getNumber(2) && !isActualTicket) R.drawable.ball1star else R.drawable.ball1),"ball 3",modifier = Modifier.fillMaxSize())
                    Text(if (isActualTicket) winingTicket.getNumber(2).toString() else myTicket.getNumber(2).toString(),
                        fontSize = 25.sp,textAlign = TextAlign.Center,
                        style = TextStyle(textDecoration = TextDecoration.Underline),
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.fillMaxSize().paddingFromBaseline(top = 45.dp))
                }
                Box(modifier = Modifier.weight(1f).size(75.dp,75.dp)){
                    Image(painter = painterResource(id = if(myTicket.getNumber(3) == winingTicket.getNumber(3) && !isActualTicket) R.drawable.ball1star else R.drawable.ball1),"ball 4",modifier = Modifier.fillMaxSize())
                    Text(if (isActualTicket) winingTicket.getNumber(3).toString() else myTicket.getNumber(3).toString(),
                        fontSize = 25.sp,textAlign = TextAlign.Center,
                        style = TextStyle(textDecoration = TextDecoration.Underline),
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.fillMaxSize().paddingFromBaseline(top = 45.dp))
                }
                Box(modifier = Modifier.weight(1f).size(75.dp,75.dp)){
                    Image(painter = painterResource(id = if(myTicket.getNumber(4) == winingTicket.getNumber(4) && !isActualTicket) R.drawable.ball1star else R.drawable.ball1),"ball 5",modifier = Modifier.fillMaxSize())
                    Text(if (isActualTicket) winingTicket.getNumber(4).toString() else myTicket.getNumber(4).toString(),
                        fontSize = 25.sp,textAlign = TextAlign.Center,
                        style = TextStyle(textDecoration = TextDecoration.Underline),
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.fillMaxSize().paddingFromBaseline(top = 45.dp))
                }
                Box(modifier = Modifier.weight(1f).size(75.dp,75.dp)){
                    var id = R.drawable.power1
                    if(myTicket.getNumber(5) == winingTicket.getNumber(5) && !isActualTicket){
                        if(isPowerball){
                            id = R.drawable.powerstar1
                        }else{
                            id = R.drawable.megastar1
                        }
                    }else{
                        if(isPowerball){
                            id = R.drawable.power1
                        }else{
                            id = R.drawable.mega1
                        }
                    }
                    Image(painter = painterResource(id =  id),"powerball",modifier = Modifier.fillMaxSize())
                    Text(if (isActualTicket) winingTicket.getNumber(5).toString() else myTicket.getNumber(5).toString(),
                        fontSize = 25.sp,textAlign = TextAlign.Center,
                        style = TextStyle(textDecoration = TextDecoration.Underline),
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.fillMaxSize().paddingFromBaseline(top = 45.dp))
                }
                Box(modifier = Modifier.weight(2f).size(75.dp,75.dp)){

                    Text(winingTicket.calculateWin(myTicket.ticket!!, true),
                        color = Color.White,
                        fontSize = 20.sp,textAlign = TextAlign.Center,
                        style = TextStyle(textDecoration = TextDecoration.Underline),
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.fillMaxSize().paddingFromBaseline(top = 12.dp))
                }




            }
        }

    }

    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        PowerMegaTheme {
            var myTicket = MyTicket()
            myTicket.ticket = "1 2 12 4 5 6"
            myTicket.multi = true;
            var winningTicket = WinningTicket()
            winningTicket.winningNumber = "1 2 12 4 5 6"
            winningTicket.multiplier = "2"
            TicketView(myTicket = myTicket, winingTicket = winningTicket,isActualTicket = true,isPowerball = true, numTicket = 1)
        }
    }
}