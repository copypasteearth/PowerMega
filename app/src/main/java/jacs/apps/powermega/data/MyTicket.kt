/*
 * Author: John Rowan
 * Description: class representing a powerball or a megamillions ticket
 * Anyone may use this file or anything contained in this project for their own personal use.
 */
package jacs.apps.powermega.data

class MyTicket {
    @JvmField
    var ticket: String? = null
    @JvmField
    var multi = false

    fun getNumber(x:Int) : Int?{
        return ticket?.split(" ")?.get(x)?.toInt();
    }
}