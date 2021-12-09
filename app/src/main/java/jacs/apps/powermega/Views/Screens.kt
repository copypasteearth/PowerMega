package jacs.apps.powermega.Views

import android.graphics.Color
import android.util.Log
import android.widget.NumberPicker
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
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import jacs.apps.powermega.AppMainScreen
import jacs.apps.powermega.R
import jacs.apps.powermega.data.MyTicket
import jacs.apps.powermega.ui.theme.PowerMegaTheme
import java.util.*
import kotlin.collections.HashSet
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue


@Composable
fun MyTickets(viewModel: PowerMegaViewModel,openDrawer: () -> Unit) {
    var myMegamillionsTickets = viewModel.myMegamillionsTickets
    var myPowerTickets = viewModel.myPowerballTickets
    var selectedTabIndex by remember { mutableStateOf(0) }
    val scrollState = rememberScrollState()
    Column(modifier = Modifier.fillMaxSize()) {
        TopBar(
            title = "Home",
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
        when(selectedTabIndex){
            0 -> Column(modifier = Modifier.verticalScroll(scrollState)){
                TicketPicker(powerball = true, viewModel = viewModel)
                myPowerTickets?.forEach {
                    TicketView(
                        myTicket = it,
                        winingTicket = WinningTicket(),
                        isActualTicket = false,
                        isPowerball = true,
                        numTicket = 0,
                        display = true,
                        onClick = {viewModel.removeTicket(powerball = true, ticket = it)}
                    )
                }
            }
            1 -> Column(modifier = Modifier.verticalScroll(scrollState)){
                TicketPicker(powerball = false, viewModel = viewModel)
                myMegamillionsTickets?.forEach {
                    TicketView(
                        myTicket = it,
                        winingTicket = WinningTicket(),
                        isActualTicket = false,
                        isPowerball = false,
                        numTicket = 0,
                        display = true,
                        onClick = {
                            Log.d("removing","removing ticket")
                            viewModel.removeTicket(powerball = false, ticket = it)}
                    )
                }
            }
        }
    }
}
@Composable
fun TicketPicker(powerball: Boolean,viewModel: PowerMegaViewModel?){
    val color = if(powerball)  Color.RED else Color.YELLOW
    val checkedState = remember { mutableStateOf(false) }
    var number1 = remember{ mutableStateOf(1)}
    var number2 = remember{ mutableStateOf(1)}
    var number3 = remember{ mutableStateOf(1)}
    var number4 = remember{ mutableStateOf(1)}
    var number5 = remember{ mutableStateOf(1)}
    var powNum = remember{ mutableStateOf(1)}

    val showDialog = remember { mutableStateOf(false) }
    if(showDialog.value){
        ErrorDialog(onDismiss = {showDialog.value = false})
    }
    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally){
       Row(modifier = Modifier.fillMaxWidth()) {
           AndroidView(
               modifier = Modifier.weight(0.16666F),
               factory = { context ->
                   NumberPicker(context).apply {
                       setOnValueChangedListener { numberPicker, i, i2 ->
                           Log.d("numberstickets", "$i  $i2")
                           number1.value = i2}
                       minValue = 1
                       maxValue = if(powerball) 69 else 70
                   }
               }
           )
           AndroidView(
               modifier = Modifier.weight(0.16666F),
               factory = { context ->
                   NumberPicker(context).apply {
                       setOnValueChangedListener { numberPicker, i, i2 ->  number2.value = i2}
                       minValue = 1
                       maxValue = if(powerball) 69 else 70
                   }
               }
           )
           AndroidView(
               modifier = Modifier.weight(0.16666F),
               factory = { context ->
                   NumberPicker(context).apply {
                       setOnValueChangedListener { numberPicker, i, i2 ->  number3.value = i2}
                       minValue = 1
                       maxValue = if(powerball) 69 else 70
                   }
               }
           )
           AndroidView(
               modifier = Modifier.weight(0.16666F),
               factory = { context ->
                   NumberPicker(context).apply {
                       setOnValueChangedListener { numberPicker, i, i2 -> number4.value = i2 }
                       minValue = 1
                       maxValue = if(powerball) 69 else 70
                   }
               }
           )
           AndroidView(
               modifier = Modifier.weight(0.16666F),
               factory = { context ->
                   NumberPicker(context).apply {
                       setOnValueChangedListener { numberPicker, i, i2 ->  number5.value = i2}
                       minValue = 1
                       maxValue = if(powerball) 69 else 70
                   }
               }
           )
           AndroidView(
               modifier = Modifier.weight(0.16666F),
               factory = { context ->
                   NumberPicker(context).apply {
                       setOnValueChangedListener { numberPicker, i, i2 ->  powNum.value = i2}
                       minValue = 1
                       maxValue = if(powerball) 26 else 25
                       setBackgroundColor(color)
                   }
               }
           )
       }
        Row(horizontalArrangement = Arrangement.Center){
            Button(onClick = {
                val nums = arrayOf(number1.value,number2.value,number3.value,number4.value,number5.value)
                Arrays.sort(nums)
                Log.d("numberstickets",Arrays.toString(nums))
                val set: Set<Int> = HashSet(Arrays.asList(*nums))
                if (set.size == nums.size) {
                    val ticket = nums[0].toString() + " " + nums[1] + " " + nums[2] +
                            " " + nums[3] + " " + nums[4] + " " + powNum.value

                    var ticketMultiplier = checkedState.value

                    val ticket1 = MyTicket()
                    ticket1.ticket = ticket
                    ticket1.multi = ticketMultiplier
                    viewModel!!.addTicket(powerball = powerball, ticket = ticket1)

                } else {
                    showDialog.value = true
                }

            } ) {
                Text(text = stringResource(id = R.string.addticket))
            }
            Spacer(modifier = Modifier.size(50.dp,50.dp))
            Text(text= stringResource(id = R.string.multiplier))
            Checkbox(checked = checkedState.value, onCheckedChange = {checkedState.value = it})
        }

    }

}
@Preview(showBackground = true)
@Composable
fun TicketPreview() {
    PowerMegaTheme {
        TicketPicker(powerball = false, viewModel = null)
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
fun SimulatorScreen(viewModel: PowerMegaViewModel?,openDrawer: () -> Unit) {
    var selectedTabIndex by remember { mutableStateOf(0) }
    var powerballTickets = viewModel!!.myPowerballTickets
    var megamillionsTickets = viewModel.myMegamillionsTickets
    var megaSim = viewModel.megaSimData
    var powerSim = viewModel.powerSimData

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
            listOf(
                stringResource(id = R.string.powerball),
                stringResource(id = R.string.megamillions)
            ).forEachIndexed { i, text ->
                Tab(
                    selected = selectedTabIndex == i,
                    onClick = { selectedTabIndex = i },
                    modifier = Modifier.height(50.dp), // Specify the Tab's height instead
                    text = { Text(text) }
                )
            }
        }

        when(selectedTabIndex){
            0 -> Column(modifier = Modifier.fillMaxSize()){
                Row(modifier = Modifier.fillMaxWidth()){
                    Button(onClick = { /*TODO*/ },modifier = Modifier.weight(0.5f)) {
                        Text(stringResource(id = R.string.reset))
                    }
                    Button(onClick = { /*TODO*/ },modifier = Modifier.weight(0.5f)) {
                        Text(stringResource(id = R.string.start))
                    }
                }
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally){
                    itemsIndexed(powerballTickets) { index, myTicket ->
                        var simData = powerSim.get(index)
                        SimulatorCard(powerball = true, myTicket = myTicket, simulatorData = simData)
                    }
                }


            }
            1 -> Column(modifier = Modifier.fillMaxSize()){
                Row(modifier = Modifier.fillMaxWidth()){
                    Button(onClick = { /*TODO*/ }, modifier = Modifier.weight(0.5f)) {
                        Text(stringResource(id = R.string.reset))
                    }
                    Button(onClick = { /*TODO*/ },modifier = Modifier.weight(0.5f)) {
                        Text(stringResource(id = R.string.start))
                    }
                }

                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally){
                    itemsIndexed(megamillionsTickets) { index, myTicket ->
                        var simData = megaSim.get(index)
                        SimulatorCard(powerball = false, myTicket = myTicket, simulatorData = simData)
                    }
                }
            }
        }

    }
}