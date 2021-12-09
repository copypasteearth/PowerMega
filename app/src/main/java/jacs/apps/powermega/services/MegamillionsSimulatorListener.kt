/*
 * Author: John Rowan
 * Description:
 * Anyone may use this file or anything contained in this project for their own personal use.
 */
package jacs.apps.powermega.services

import jacs.apps.powermega.SimulatorData
import java.util.*

interface MegamillionsSimulatorListener {
    fun onLottoResult(listData: ArrayList<SimulatorData>)
    fun stopLottoService()
}