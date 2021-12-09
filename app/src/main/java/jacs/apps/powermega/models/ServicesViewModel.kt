package jacs.apps.powermega.models

import android.app.Application
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Build
import android.os.IBinder
import android.util.Log
import android.view.View
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.AndroidViewModel
import jacs.apps.powermega.SimulatorData
import jacs.apps.powermega.services.MegaMillionsSimulatorService
import jacs.apps.powermega.services.MegamillionsSimulatorListener
import jacs.apps.powermega.services.PowerballSimulatorListener
import jacs.apps.powermega.services.PowerballSimulatorService
import jacs.apps.powermega.utils.Constants
import jacs.apps.powermega.utils.SharedPrefHelper
import java.util.ArrayList

class ServicesViewModel(application: Application) : AndroidViewModel(application) {
    init {

    }

    var binding = false
    var mbinding = false
    var powerPlayService: PowerballSimulatorService? = null
    var megaPlayService: MegaMillionsSimulatorService? = null
    var powerSim = SharedPrefHelper.getSimData(application, Constants.POWER_SIM).toList().toMutableStateList()
    var megaSim = SharedPrefHelper.getSimData(application,Constants.MEGA_SIM).toList().toMutableStateList()
    val powerballSimulatorListener : PowerballSimulatorListener = object : PowerballSimulatorListener{
        override fun onLottoResult(listData: ArrayList<SimulatorData>) {
            powerSim.clear();
            powerSim.addAll(listData)
        }

        override fun stopLottoService() {

        }

    }
    val megaballSimulatorListener : MegamillionsSimulatorListener = object : MegamillionsSimulatorListener{
        override fun onLottoResult(listData: ArrayList<SimulatorData>) {
            megaSim.clear()
            megaSim.addAll(listData)
        }

        override fun stopLottoService() {

        }

    }
    val powerPlayServiceConnection = object : ServiceConnection {
        override fun onServiceConnected(className: ComponentName, service: IBinder) {
            val binder = service as PowerballSimulatorService.PowerballSimulatorServiceBinder
            powerPlayService = binder.service
            powerPlayService!!.setPowerballSimulatorListener(powerballSimulatorListener)
            // mPlayService.setMainActivity(getActivity());
            Log.d("powerservice", "service binded")
        }

        override fun onServiceDisconnected(className: ComponentName) {
            Log.d("powerservice", "service unbinded")
        }
    }
    val megaPlayServiceConnection = object : ServiceConnection {
        override fun onServiceConnected(className: ComponentName, service: IBinder) {
            val binder = service as MegaMillionsSimulatorService.MegaMillionsSimulatorServiceBinder
            megaPlayService = binder.service
            megaPlayService!!.setMegaMillionsSimulatorListener(megaballSimulatorListener)
            // mPlayService.setMainActivity(getActivity());
            Log.d("powerservice", "service binded")
        }

        override fun onServiceDisconnected(className: ComponentName) {
            Log.d("powerservice", "service unbinded")
        }
    }
    var powerClick : () -> Unit = {


    }
    var megaClick : () -> Unit = {

    }
    fun resetPower(){
        val length = powerSim.size
        val tickets = SharedPrefHelper.getMyTickets(getApplication(),Constants.POWER_TICKETS)
        powerSim.clear()
        for(tick in tickets){
            var dat = SimulatorData()
            dat.number = tick.ticket
            powerSim.add(dat)
        }
        SharedPrefHelper.setSimData(getApplication(),powerSim,Constants.POWER_SIM)
        SharedPrefHelper.setLongPowerballCounter(getApplication(),0)
        PowerballSimulatorService.update = true
    }
    fun resetMega(){
        val length = megaSim.size
        val tickets = SharedPrefHelper.getMyTickets(getApplication(),Constants.MEGA_TICKETS)
        megaSim.clear()
        for(tick in tickets){
            var dat = SimulatorData()
            dat.number = tick.ticket
            megaSim.add(dat)
        }
        SharedPrefHelper.setSimData(getApplication(),megaSim,Constants.MEGA_SIM)
        SharedPrefHelper.setLongMegaCounter(getApplication(),0)
        MegaMillionsSimulatorService.update = true
    }

}