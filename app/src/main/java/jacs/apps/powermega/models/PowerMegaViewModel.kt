package jacs.apps.powermega.models

import android.app.Application
import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.*
import com.google.gson.Gson
import jacs.apps.powermega.data.MyTicket
import jacs.apps.powermega.utils.Constants
import jacs.apps.powermega.utils.SharedPrefHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import powerball.apps.jacs.powerball.data.mega.MegamillionsData
import powerball.apps.jacs.powerball.data.power.PowerballData
import java.net.URL
import androidx.compose.runtime.getValue

import androidx.compose.runtime.livedata.observeAsState
import jacs.apps.powermega.SimulatorData

class PowerMegaViewModel(application: Application) : AndroidViewModel(application) {

    val powerballData: LiveData<PowerballData> = liveData(context = viewModelScope.coroutineContext + Dispatchers.IO) {

            val json = URL(Constants.PowerURL).readText()
            Log.d("powerball",json)
            val gson = Gson()
            val powerballDataList = gson.fromJson(json, PowerballData::class.java)
            emit(powerballDataList)


    }
    val megamillionsData: LiveData<MegamillionsData> = liveData(context = viewModelScope.coroutineContext + Dispatchers.IO) {
        val json = URL(Constants.MegaURL).readText()
        Log.d("mega",json)
        val gson = Gson()
        val megamillionsDataList = gson.fromJson(json, MegamillionsData::class.java)
        emit(megamillionsDataList)
    }
    var myPowerballTickets = SharedPrefHelper.getMyTickets(getApplication(),Constants.POWER_TICKETS).toList().toMutableStateList()


    /*var myPowerballTickets : MutableLiveData<ArrayList<MyTicket>> = liveData{
        var list = SharedPrefHelper.getMyTickets(application,Constants.POWER_TICKETS)
        emit(list)
    } as MutableLiveData<ArrayList<MyTicket>>
    var myMegamillionsTickets : MutableLiveData<ArrayList<MyTicket>> = liveData {
        var list = SharedPrefHelper.getMyTickets(application,Constants.MEGA_TICKETS)
        emit(list)
    } as MutableLiveData<ArrayList<MyTicket>>*/
    var myMegamillionsTickets = SharedPrefHelper.getMyTickets(getApplication(),Constants.MEGA_TICKETS).toList().toMutableStateList()
    var megaSimData = SharedPrefHelper.getSimData(getApplication(),Constants.MEGA_SIM).toList().toMutableStateList()
    var powerSimData = SharedPrefHelper.getSimData(getApplication(),Constants.POWER_SIM).toList().toMutableStateList()
    fun addTicket(powerball: Boolean, ticket: MyTicket){
        if(powerball){
            myPowerballTickets.add(ticket)
            powerSimData.add(SimulatorData())
            SharedPrefHelper.setMyTickets(getApplication(),myPowerballTickets,Constants.POWER_TICKETS)
            SharedPrefHelper.setSimData(getApplication(),powerSimData,Constants.POWER_SIM)
            //myPowerballTickets.value = myPowerballTickets.value

        }else{
            myMegamillionsTickets.add(ticket)
            megaSimData.add(SimulatorData())
            SharedPrefHelper.setMyTickets(getApplication(),myMegamillionsTickets,Constants.MEGA_TICKETS)
            SharedPrefHelper.setSimData(getApplication(),megaSimData,Constants.MEGA_SIM)
            //myMegamillionsTickets.value = myMegamillionsTickets.value

        }
    }
    fun removeTicket(powerball: Boolean,ticket: MyTicket){
        if(powerball){
            val index = myPowerballTickets.indexOf(ticket)
            myPowerballTickets.remove(ticket)
            powerSimData.removeAt(index)
            SharedPrefHelper.setMyTickets(getApplication(),myPowerballTickets,Constants.POWER_TICKETS)
            SharedPrefHelper.setSimData(getApplication(),powerSimData,Constants.POWER_SIM)
            //myPowerballTickets.value = myPowerballTickets.value

        }else{
            val index = myMegamillionsTickets.indexOf(ticket)
            myMegamillionsTickets.remove(ticket)
            megaSimData.removeAt(index)
            SharedPrefHelper.setMyTickets(getApplication(),myMegamillionsTickets,Constants.MEGA_TICKETS)
            SharedPrefHelper.setSimData(getApplication(),megaSimData,Constants.MEGA_SIM)
            //myMegamillionsTickets.value = myMegamillionsTickets.value

        }
    }

}