package jacs.apps.powermega.Views

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import jacs.apps.powermega.data.WinningTicket
import jacs.apps.powermega.ui.theme.PowerMegaTheme
import jacs.apps.powermega.data.MyTicket


@Composable
fun TicketCard(winningTicket: WinningTicket, myTickets: List<MyTicket>, isPowerball: Boolean){
 Column(){
     Text(winningTicket.date!!)
     TicketView(myTicket = MyTicket(), winingTicket = winningTicket, isActualTicket = true, isPowerball = isPowerball, numTicket = 0)
     var count = 1
     for(myTicket in myTickets){
         TicketView(
             myTicket = myTicket,
             winingTicket = winningTicket,
             isActualTicket = false,
             isPowerball = isPowerball,
             numTicket = count
         )
         count++
     }
 }



}

@Preview(showBackground = true)
@Composable
fun Preview() {
    PowerMegaTheme {
        val ticket1 = MyTicket()
        ticket1.ticket = "1 2 3 4 5 6"
        ticket1.multi = true
        val ticket2 = MyTicket()
        ticket2.ticket = "2 3 4 5 6 7"
        ticket2.multi = true
        var ticks = listOf<MyTicket>()
        val win = WinningTicket()
        win.winningNumber = "1 2 3 4 5 6"
        win.multiplier = "3"
        win.date = "3/12/80"
        TicketCard(winningTicket = win, myTickets = ticks, isPowerball = true)
    }
}