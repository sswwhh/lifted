package LiftOption.Queue

import LiftOption.LiftCalls.Call


interface QueueInterface {

    fun addToQueue(call: Call)
    fun removeFromQueue(floor: Int)
    fun removeFromQueue(floors: List<Int>)

    fun getAllFloors() : List<Int>
    fun getNext(currentFloor: Int): Call?
    fun getSize() : Int
}