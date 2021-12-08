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

    fun addTicket(powerball: Boolean, ticket: MyTicket){
        if(powerball){
            myPowerballTickets.add(ticket)
            SharedPrefHelper.setMyTickets(getApplication(),myPowerballTickets,Constants.POWER_TICKETS)
            //myPowerballTickets.value = myPowerballTickets.value

        }else{
            myMegamillionsTickets.add(ticket)
            SharedPrefHelper.setMyTickets(getApplication(),myMegamillionsTickets,Constants.MEGA_TICKETS)
            //myMegamillionsTickets.value = myMegamillionsTickets.value

        }
    }
    fun removeTicket(powerball: Boolean,ticket: MyTicket){
        if(powerball){
            myPowerballTickets.remove(ticket)
            SharedPrefHelper.setMyTickets(getApplication(),myPowerballTickets,Constants.POWER_TICKETS)
            //myPowerballTickets.value = myPowerballTickets.value

        }else{
            myMegamillionsTickets.remove(ticket)
            SharedPrefHelper.setMyTickets(getApplication(),myMegamillionsTickets,Constants.MEGA_TICKETS)
            //myMegamillionsTickets.value = myMegamillionsTickets.value

        }
    }

}