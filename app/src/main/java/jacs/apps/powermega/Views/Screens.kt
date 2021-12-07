package jacs.apps.powermega.Views

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import jacs.apps.powermega.data.WinningTicket
import jacs.apps.powermega.models.PowerMegaViewModel
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import jacs.apps.powermega.R

@Composable
fun Home(openDrawer: () -> Unit) {
    Column(modifier = Modifier.fillMaxSize()) {
        TopBar(
            title = "Home",
            buttonIcon = Icons.Filled.Menu,
            onButtonClicked = { openDrawer() }
        )
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "Home Page content here.")
        }
    }
}

@Composable
fun PastTicketResults(viewModel: PowerMegaViewModel,openDrawer: () -> Unit) {
    val powerballData = viewModel.powerballData.observeAsState()
    val megamillionsData = viewModel.megamillionsData.observeAsState()
    val myMegamillionsTickets = viewModel.myMegamillionsTickets
    val myPowerTickets = viewModel.myPowerballTickets
    var selectedTabIndex by remember { mutableStateOf(0) }


    Column(modifier = Modifier.fillMaxSize()) {
        TopBar(
            title = stringResource(id = R.string.past_results),
            buttonIcon = Icons.Filled.Menu,
            onButtonClicked = { openDrawer() }
        )
        TabRow(
            selectedTabIndex = selectedTabIndex,
            modifier = Modifier.fillMaxWidth(), // Don't specify the TabRow's height!
            backgroundColor = MaterialTheme.colors.primary
        ) {
            listOf(stringResource(id = R.string.powerball), stringResource(id = R.string.megamillions)).forEachIndexed { i, text ->
                Tab(
                    selected = selectedTabIndex == i,
                    onClick = { selectedTabIndex = i },
                    modifier = Modifier.height(50.dp), // Specify the Tab's height instead
                    text = { Text(text) }
                )
            }
        }


        when (selectedTabIndex) { // 6.
            0 -> LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally) {
                if(powerballData.value != null){
                    items(powerballData.value!!){ tick ->
                        val winner = WinningTicket()
                        winner.winningNumber = tick.winning_numbers
                        winner.date = tick.draw_date
                        winner.multiplier = tick.multiplier
                        TicketCard(winningTicket = winner, myTickets = myPowerTickets, isPowerball = true)
                    }
                }


            }
            1 -> LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally) {
                if(megamillionsData.value != null){
                    items(megamillionsData.value!!){ tick ->
                        val winner = WinningTicket()
                        winner.winningNumber = tick.winning_numbers + " " + tick.mega_ball
                        winner.date = tick.draw_date
                        winner.multiplier = tick.multiplier
                        TicketCard(winningTicket = winner, myTickets = myMegamillionsTickets, isPowerball = false)
                    }
                }


            }
            2 -> Text("World content")
        }
        /**/
    }
}

@Composable
fun Help(openDrawer: () -> Unit) {
    Column(modifier = Modifier.fillMaxSize()) {
        TopBar(
            title = "Help",
            buttonIcon = Icons.Filled.Menu,
            onButtonClicked = { openDrawer() }
        )
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "Help.", style = MaterialTheme.typography.h4)
        }
    }
}