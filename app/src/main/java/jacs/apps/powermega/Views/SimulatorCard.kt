package jacs.apps.powermega.Views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import jacs.apps.powermega.R
import jacs.apps.powermega.SimulatorData
import jacs.apps.powermega.data.MyTicket
import jacs.apps.powermega.data.WinningTicket
import jacs.apps.powermega.models.PowerMegaViewModel
import jacs.apps.powermega.ui.theme.PowerMegaTheme

@Composable
fun SimulatorCard(powerball: Boolean, myTicket: MyTicket,simulatorData: SimulatorData){
    var ticket = WinningTicket()
    ticket.winningNumber = myTicket.ticket
    val totalPlays = stringResource(id = R.string.totalplays)
    val years = stringResource(id = R.string.years)
    val weeks = stringResource(id = R.string.weeks)
    val hits = stringResource(id = R.string.hits)
    val average = stringResource(id = R.string.average)
    val minimum = stringResource(id = R.string.minimum)
    Column(modifier = Modifier.fillMaxWidth()){
        TicketView(
            myTicket = myTicket,
            winingTicket = ticket,
            isActualTicket = true,
            isPowerball = powerball,
            numTicket = 0,
            display = false,
            onClick = {}
        )
        Text(text = "$totalPlays: ${simulatorData.plays} - ${simulatorData.getDays(simulatorData.plays,years,weeks)}")
        Image(painter = painterResource(id = if(powerball) R.drawable.powerjackpot else R.drawable.megajackpot), contentDescription = "", modifier = Modifier.size(200.dp,32.dp), contentScale = ContentScale.FillBounds)
        Text(text = "$hits: ${simulatorData.jackpotHits}")
        Text(text = "$average: ${simulatorData.jackpotAvg} - ${simulatorData.getDays(simulatorData.jackpotAvg,years,weeks)}")
        Text(text = "$minimum: ${simulatorData.jackpotMin} - ${simulatorData.getDays(simulatorData.jackpotMin,years,weeks)}")
        Divider()

        Image(painter = painterResource(id = R.drawable.ball5hit), contentDescription = "", modifier = Modifier.size(168.dp,32.dp), contentScale = ContentScale.FillBounds)
        Text(text = "$hits: ${simulatorData.white5Hits}")
        Text(text = "$average: ${simulatorData.white5Avg} - ${simulatorData.getDays(simulatorData.white5Avg,years,weeks)}")
        Text(text = "$minimum: ${simulatorData.white5Min} - ${simulatorData.getDays(simulatorData.white5Min,years,weeks)}")
        Divider()

        Image(painter = painterResource(id = if(powerball) R.drawable.ball4powerhit else R.drawable.ball4megahit), contentDescription = "", modifier = Modifier.size(168.dp,32.dp), contentScale = ContentScale.FillBounds)
        Text(text = "$hits: ${simulatorData.white4PowHits}")
        Text(text = "$average: ${simulatorData.white4PowAvg} - ${simulatorData.getDays(simulatorData.white4PowAvg,years,weeks)}")
        Text(text = "$minimum: ${simulatorData.white4PowMin} - ${simulatorData.getDays(simulatorData.white4PowMin,years,weeks)}")
        Divider()

        Image(painter = painterResource(id = R.drawable.ball4hit), contentDescription = "", modifier = Modifier.size(136.dp,32.dp), contentScale = ContentScale.FillBounds)
        Text(text = "$hits: ${simulatorData.white4Hits}")
        Text(text = "$average: ${simulatorData.white4Avg} - ${simulatorData.getDays(simulatorData.white4Avg,years,weeks)}")
        Text(text = "$minimum: ${simulatorData.white4Min} - ${simulatorData.getDays(simulatorData.white4Min,years,weeks)}")
        Divider()

        Image(painter = painterResource(id = if(powerball) R.drawable.ball3powerhit else R.drawable.ball3megahit), contentDescription = "", modifier = Modifier.size(136.dp,32.dp), contentScale = ContentScale.FillBounds)
        Text(text = "$hits: ${simulatorData.white3PowHits}")
        Text(text = "$average: ${simulatorData.white3PowAvg} - ${simulatorData.getDays(simulatorData.white3PowAvg,years,weeks)}")
        Text(text = "$minimum: ${simulatorData.white3PowMin} - ${simulatorData.getDays(simulatorData.white3PowMin,years,weeks)}")
        Divider()

        Image(painter = painterResource(id = R.drawable.ball3hit), contentDescription = "", modifier = Modifier.size(104.dp,32.dp), contentScale = ContentScale.FillBounds)
        Text(text = "$hits: ${simulatorData.white3Hits}")
        Text(text = "$average: ${simulatorData.white3Avg} - ${simulatorData.getDays(simulatorData.white3Avg,years,weeks)}")
        Text(text = "$minimum: ${simulatorData.white3Min} - ${simulatorData.getDays(simulatorData.white3Min,years,weeks)}")
        Divider()

        Image(painter = painterResource(id = if(powerball) R.drawable.ball2powerhit else R.drawable.ball2megahit), contentDescription = "", modifier = Modifier.size(104.dp,32.dp), contentScale = ContentScale.FillBounds)
        Text(text = "$hits: ${simulatorData.white2PowHits}")
        Text(text = "$average: ${simulatorData.white2PowAvg} - ${simulatorData.getDays(simulatorData.white2PowAvg,years,weeks)}")
        Text(text = "$minimum: ${simulatorData.white2PowMin} - ${simulatorData.getDays(simulatorData.white2PowMin,years,weeks)}")
        Divider()

        Image(painter = painterResource(id = if(powerball) R.drawable.ball1powerhit else R.drawable.ball1megahit), contentDescription = "", modifier = Modifier.size(72.dp,32.dp), contentScale = ContentScale.FillBounds)
        Text(text = "$hits: ${simulatorData.white1PowHits}")
        Text(text = "$average: ${simulatorData.white1PowAvg} - ${simulatorData.getDays(simulatorData.white1PowAvg,years,weeks)}")
        Text(text = "$minimum: ${simulatorData.white1PowMin} - ${simulatorData.getDays(simulatorData.white1PowMin,years,weeks)}")
        Divider()

        Image(painter = painterResource(id = if(powerball) R.drawable.noballpowerhit else R.drawable.noballmegahit), contentDescription = "", modifier = Modifier.size(32.dp,32.dp), contentScale = ContentScale.FillBounds)
        Text(text = "$hits: ${simulatorData.nowhitePowHits}")
        Text(text = "$average: ${simulatorData.nowhitePowAvg} - ${simulatorData.getDays(simulatorData.nowhitePowAvg,years,weeks)}")
        Text(text = "$minimum: ${simulatorData.nowhitePowMin} - ${simulatorData.getDays(simulatorData.nowhitePowMin,years,weeks)}")
    }
}

@Preview
@Composable
fun SimulatorCardPreview(){
    val ticket = MyTicket()
    ticket.multi = false
    ticket.ticket = "1 2 3 4 5 6"
    val simulatorData = SimulatorData()
    PowerMegaTheme {
        SimulatorCard(powerball = true, myTicket = ticket, simulatorData = simulatorData)
    }
}