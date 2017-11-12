package LiftOption

import LiftOption.LiftCalls.Call
import LiftOption.LiftConsts.LiftDirection
import LiftOption.Queue.QueueInterface
import LiftOption.Queue.QueueListener


class LiftQueue(val direction : LiftDirection, val floorListener: QueueListener) : QueueInterface {

    private val queue : MutableSet<Call> = mutableSetOf()
    private var finalPoint: Call? = null

    override fun addToQueue(call: Call) {
        if(queue.none { it.floor == call.floor }) {
            synchronized(this) {
                makeFinalPoint(call)
                queue.add(call)
            }

            floorListener.workerGo(direction)
        }
    }

    override fun removeFromQueue(floor: Int) {
        synchronized(this){
            queue.removeIf{
                it.floor == floor
            }
        }
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
     *
     * @param currentFloor - текущий этаж
     * @return [Call] - следующий этаж, на который нужно ехать, или null
     */
    override fun getNext(currentFloor: Int): Call? {

        if(isFinal(currentFloor)){
            removeFromQueue(currentFloor)
            recountFinalPoint()
            return null
        }

        var distance : Int
        var retCall : Call? = null

        distance = when(direction){
            LiftDirection.UP -> {
                finalPoint!!.floor - currentFloor
            }
            LiftDirection.DOWN -> {
                currentFloor - finalPoint!!.floor
            }
        }

        queue.forEach { q ->
            /**
             * Если q стоит на пути и он меньше дистанции,
             * его и вернём
             */
            when(direction){
                LiftDirection.UP ->{
                    val range = q.floor - currentFloor
                    if (range < distance){
                        retCall = q
                        distance = range
                    }
                }
                LiftDirection.DOWN -> {
                    val range = currentFloor - q.floor//q.floor - finalPoint!!.floor
                    if (range < distance){
                        retCall = q
                        distance = range
                    }
                }
            }
        }

        removeFromQueue(currentFloor)

        return if(retCall == null)
            finalPoint!! // эта строка должна быть @Deprecated, но надо проверить
        else
            retCall!!

    }

    override fun getSize(): Int = queue.size


    private fun isFinal(currentFloor: Int) : Boolean =
            currentFloor == finalPoint!!.floor


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

    private fun recountFinalPoint(){
        when(direction){
            LiftDirection.DOWN -> {
                finalPoint = queue.minBy { it.floor }
            }
            LiftDirection.UP -> {
                finalPoint = queue.maxBy { it.floor }
            }
        }
    }

}