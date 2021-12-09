package jacs.apps.powermega

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.StackView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key.Companion.Home
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import jacs.apps.powermega.Views.*
import jacs.apps.powermega.models.PowerMegaViewModel
import jacs.apps.powermega.models.ServicesViewModel
import jacs.apps.powermega.services.MegaMillionsSimulatorService
import jacs.apps.powermega.services.PowerballSimulatorService
import jacs.apps.powermega.ui.theme.PowerMegaTheme
import kotlinx.coroutines.launch


class MainActivity : ComponentActivity() {
    lateinit var serviceModelcop: ServicesViewModel
    override fun onDestroy() {
        super.onDestroy()
        if(serviceModelcop.binding){
            this.unbindService(serviceModelcop.powerPlayServiceConnection)
        }
        if(serviceModelcop.mbinding){
            this.unbindService(serviceModelcop.megaPlayServiceConnection)
        }

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel : PowerMegaViewModel by viewModels()
        val serviceModel : ServicesViewModel by viewModels()
        serviceModelcop = serviceModel
        if (PowerballSimulatorService.running) {
            val intent = Intent(this, PowerballSimulatorService::class.java)
            this.bindService(intent, serviceModel.powerPlayServiceConnection, Context.BIND_AUTO_CREATE)
            serviceModel.binding = true
        }
        if (MegaMillionsSimulatorService.running) {
            val intent = Intent(this, MegaMillionsSimulatorService::class.java)
            this.bindService(intent, serviceModel.megaPlayServiceConnection, Context.BIND_AUTO_CREATE)
            serviceModel.mbinding = true
        }
        serviceModel.powerClick = {
            if (PowerballSimulatorService.running) {
                Log.d("service","Service should be stopping")
                val intent = Intent(this, PowerballSimulatorService::class.java)
                this.unbindService(serviceModel.powerPlayServiceConnection)
                serviceModel.binding = false
                this.stopService(intent)

            } else {
                Log.d("service","Service should be starting")
                val intent = Intent(this, PowerballSimulatorService::class.java)
                if (Build.VERSION.SDK_INT > 25) {
                    this.startForegroundService(intent)
                } else {
                    this.startService(intent)
                }
                this.bindService(intent, serviceModel.powerPlayServiceConnection, Context.BIND_AUTO_CREATE)
                serviceModel.binding = true

            }
        }
        serviceModel.megaClick = {
            Log.d("service","Service should be starting")
            if (MegaMillionsSimulatorService.running) {
                val intent = Intent(this, MegaMillionsSimulatorService::class.java)
                this.unbindService(serviceModel.megaPlayServiceConnection)
                serviceModel.mbinding = false
                this.stopService(intent)

            } else {
                val intent = Intent(this, MegaMillionsSimulatorService::class.java)
                if (Build.VERSION.SDK_INT > 25) {
                    this.startForegroundService(intent)
                } else {
                    this.startService(intent)
                }
                this.bindService(intent, serviceModel.megaPlayServiceConnection, Context.BIND_AUTO_CREATE)
                serviceModel.mbinding = true

            }
        }
        setContent {
            PowerMegaTheme {
                AppMainScreen(viewModel = viewModel, serviceModel = serviceModel, preview = false)
            }
        }
    }
}

@Composable
fun AppMainScreen(viewModel: PowerMegaViewModel?,serviceModel: ServicesViewModel?, preview: Boolean) {
    val navController = rememberNavController()
    Surface(color = MaterialTheme.colors.background) {
        val drawerState = rememberDrawerState(DrawerValue.Closed)
        val scope = rememberCoroutineScope()
        val openDrawer = {
            scope.launch {
                drawerState.open()
            }
        }
        ModalDrawer(
            drawerState = drawerState,
            gesturesEnabled = drawerState.isOpen,
            drawerContent = {
                Drawer(
                    onDestinationClicked = { route ->
                        scope.launch {
                            drawerState.close()
                        }
                        navController.navigate(route) {
                            //popUpTo = navController.graph.startDestination
                            launchSingleTop = true
                        }
                    }
                )
            }
        ) {
            if(!preview){
                NavHost(
                    navController = navController,
                    startDestination = DrawerScreens.PastResults.route
                ) {

                    composable(DrawerScreens.MyTickets.route) {
                        MyTickets(
                            viewModel = viewModel!!,
                            openDrawer = {
                                openDrawer()
                            }
                        )
                    }
                    composable(DrawerScreens.PastResults.route) {
                        PastTicketResults(
                            viewModel = viewModel!!,
                            openDrawer = {
                                openDrawer()
                            }
                        )
                    }
                    composable(DrawerScreens.Simulator.route) {
                        SimulatorScreen(
                            viewModel = viewModel!!,
                            serviceModel = serviceModel!!,
                            openDrawer = {
                                openDrawer()
                            }

                        )
                    }
                }
            }else{
                Text(text = "hello")
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    PowerMegaTheme {
        AppMainScreen(viewModel = null, serviceModel = null, preview = true)
    }
}