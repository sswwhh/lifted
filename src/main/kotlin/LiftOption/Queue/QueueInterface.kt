package LiftOption.Queue

import LiftOption.LiftCalls.Call
import LiftOption.LiftConsts


interface QueueInterface {

    fun addToQueue(call: Call)
    fun removeFromQueue(floor: Int)
    fun removeFromQueue(floor: Int, dir: LiftConsts.LiftDirection)
    fun removeFromQueue(floors: List<Int>)

    fun getAllFloors() : List<Int>
    fun getNext(currentFloor: Int): Call?
    fun getSize() : Int
}