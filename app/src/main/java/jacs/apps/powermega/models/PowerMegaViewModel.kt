package jacs.apps.powermega.models

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import jacs.apps.powermega.utils.Constants
import jacs.apps.powermega.utils.SharedPrefHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import powerball.apps.jacs.powerball.data.mega.MegamillionsData
import powerball.apps.jacs.powerball.data.power.PowerballData
import java.net.URL

class PowerMegaViewModel(application: Application) : AndroidViewModel(application) {

    val powerballData: LiveData<PowerballData> = liveData(context = viewModelScope.coroutineContext + Dispatchers.IO) {

            val json = URL(Constants.PowerURL).readText()
            Log.d("viewmodel",json)
            val gson = Gson()
            val powerballDataList = gson.fromJson(json, PowerballData::class.java)
            emit(powerballDataList)


    }
    val megamillionsData: LiveData<MegamillionsData> = liveData {
        val json = URL(Constants.MegaURL).readText()
        val gson = Gson()
        val megamillionsDataList = gson.fromJson(json, MegamillionsData::class.java)
        emit(megamillionsDataList)
    }
    val myPowerballTickets = SharedPrefHelper.getMyTickets(application,Constants.POWER_TICKETS)
    val myMegamillionsTickets = SharedPrefHelper.getMyTickets(application,Constants.MEGA_TICKETS)

}