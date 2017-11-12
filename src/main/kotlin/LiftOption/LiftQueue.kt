package LiftOption

import LiftOption.LiftCalls.Call
import LiftOption.LiftConsts.LiftDirection
import LiftOption.Queue.QueueInterface
import LiftOption.Queue.QueueLeftListener

class LiftQueue(val direction : LiftDirection, val floorLeftListener: QueueLeftListener) : QueueInterface {

    private val queue : MutableSet<Call> = mutableSetOf()
    private var finalPoint: Call? = null

    override fun addToQueue(call: Call) {
        if(queue.none { it.floor == call.floor }) {
            synchronized(this) {
                makeFinalPoint(call)
                queue.add(call)
            }
        }
    }

    override fun removeFromQueue(floor: Int) {
        synchronized(this){
            queue.removeIf{
                it.floor == floor
            }
        }

        floorLeftListener.floorLeft(floor, direction)
    }

    override fun removeFromQueue(floors: List<Int>) {
        floors.forEach { f ->
            synchronized(this) {
                queue.removeIf { q ->
                    q.floor == f
                }
            }
        }
    }

    override fun getAllFloors(): List<Int> =
        queue.map { it.floor }


    /**
     * Лифт всегда двигается к самому дальнему этажу - finalPoint
     * Но по пути останавливается на всех нужных
     */
    override fun getNext(currentFloor: Int): Call? {

        if(isFinal(currentFloor)) return null

        var distance : Int
        var retCall : Call? = null

        distance = when(direction){
            LiftDirection.DOWN -> {
                currentFloor - finalPoint!!.floor
            }
            LiftDirection.UP -> {
                finalPoint!!.floor - currentFloor
            }
        }

        queue.forEach { q ->
            /**
             * Если q стоит на пути и он меньше дистанции,
             * его и вернём
             */
            when(direction){
                LiftDirection.DOWN -> {
                    val range = q.floor - finalPoint!!.floor
                    if (range < distance){
                        retCall = q
                        distance = range
                    }
                }
                LiftDirection.UP ->{
                    val range = finalPoint!!.floor - q.floor
                    if (range < distance){
                        retCall = q
                        distance = range
                    }
                }
            }
        }

        removeFromQueue(currentFloor)

        println("next is " + currentFloor)

        return if(retCall == null)
            finalPoint!!
        else
            retCall!!

    }

    override fun getSize(): Int = queue.size


    private fun isFinal(currentFloor: Int) : Boolean {
        if (currentFloor == finalPoint!!.floor) {
            return true
        }
        return false
    }

    private fun makeFinalPoint(call: Call) {
        if (finalPoint == null) {
            finalPoint = call

            println("finalPoint is " + finalPoint!!.floor)
            return
        }

        when(direction){
            LiftDirection.DOWN -> {
                if (finalPoint!!.floor > call.floor)
                    finalPoint = call
            }
            LiftDirection.UP -> {
                if (finalPoint!!.floor < call.floor)
                    finalPoint = call
            }
        }

        println("finalPoint is " + finalPoint!!.floor)

    }

}