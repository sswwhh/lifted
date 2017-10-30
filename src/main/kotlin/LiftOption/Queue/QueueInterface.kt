package LiftOption.Queue

import LiftOption.LiftCalls.Call


interface QueueInterface {

    fun addToQueue(call: Call)
    fun removeFromQueue(floor: Int)

    fun getNext(current: Int): Call
    fun work()

    fun getSize() : Int
}