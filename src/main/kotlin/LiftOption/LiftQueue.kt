package LiftOption

import LiftOption.LiftCalls.Call
import LiftOption.LiftConsts.LiftDirection
import LiftOption.Queue.QueueInterface
import LiftOption.Queue.QueueListener
import LiftOption.Queue.QueueState

class LiftQueue(val direction : LiftDirection,
                val state: QueueListener) : QueueInterface {

    private val queue : MutableSet<Call> = mutableSetOf()
    private var most : Call? = null

    override fun work() {

        // New Thread with checking state each time


        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun addToQueue(call: Call) {

        println("adding to queue " + call.floor)

        if(queue.none { it.floor == call.floor }) {
            makeMost(call)
            queue.add(call)
        }
    }

    override fun removeFromQueue(floor: Int) {
        println("removing to queue " + floor)

        queue.removeIf{
            it.floor == floor
        }
    }

    override fun removeFromQueue(floors: List<Int>) {
        floors.forEach { f ->
            queue.removeIf{ q->
                q.floor == f
            }
        }
    }

    override fun getAllFloors(): List<Int> =
        queue.map { it.floor }


    /**
     * Лифт всегда двигается к самому дальнему этажу - most
     * Но по пути останавливается на всех нужных
     */
    override fun getNext(currentFloor: Int): Call {

        checkTheEnd(currentFloor)

        var distance : Int
        var retCall : Call? = null

        distance = when(direction){
            LiftDirection.DOWN -> {
                currentFloor - most!!.floor
            }
            LiftDirection.UP -> {
                most!!.floor - currentFloor
            }
        }

        queue.forEach { q ->
            when(direction){
                LiftDirection.DOWN -> {

                    /**
                     * Если q стоит на пути и он меньше дистанции,
                     * его и вернём
                     */
                    val range = q.floor - most!!.floor
                    if (range < distance){
                        retCall = q
                        distance = range
                    }
                }
                LiftDirection.UP ->{
                    val range = most!!.floor - q.floor
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
            most!!
        else
            retCall!!

    }

    override fun getSize(): Int = queue.size


    private fun checkTheEnd(currentFloor: Int) {
        if (currentFloor == most!!.floor) {
            state.changeState(direction, QueueState.State.STOPPED)
        }
    }

    private fun makeMost(call: Call) {
        if (most == null) {
            most = call

            println("most is " + most!!.floor)
            return
        }

        when(direction){
            LiftDirection.DOWN -> {
                if (most!!.floor > call.floor)
                    most = call
            }
            LiftDirection.UP -> {
                if (most!!.floor < call.floor)
                    most = call
            }
        }

        println("most is " + most!!.floor)

    }

}