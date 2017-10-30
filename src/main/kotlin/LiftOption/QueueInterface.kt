package LiftOption

import LiftOption.LiftCalls.Call


interface QueueInterface {

    fun addToQueue(call: Call)
    fun removeFromQueue(call: Call)

    fun getNext(current: Call): Call

    fun getSize() : Int
}