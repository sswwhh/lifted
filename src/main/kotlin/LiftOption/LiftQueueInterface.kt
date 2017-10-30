package LiftOption

import LiftOption.LiftCalls.Call

class LiftQueueInterface : QueueInterface {

    private val queue : MutableSet<Call> = mutableSetOf()
    private var most : Call? = null

    override fun addToQueue(call: Call) {

        println("adding to queue " + call.from)

        compareMost(call)
        queue.add(call)
    }

    private fun compareMost(call: Call) {
        if (most == null) {
            most = call

            println("most is " + most!!.from)
            return
        }

        when(call.direction){
            LiftDirection.DOWN -> {
                if (most!!.from > call.from)
                    most = call
            }
            LiftDirection.UP -> {
                if (most!!.from < call.from)
                    most = call
            }
        }

        println("most is " + most!!.from)

    }

    override fun removeFromQueue(call: Call) {
        queue.remove(call)
    }


    /**
     * Лифт всегда двигается к самому дальнему этажу - most
     * Но по пути останавливается на всех нужных
     */
    override fun getNext(current: Call): Call {

        var distance : Int
        var retCall : Call? = null

        distance = when(current.direction){
            LiftDirection.DOWN -> {
                current.from - most!!.from
            }
            LiftDirection.UP -> {
                most!!.from - current.from
            }
        }

        queue.forEach { q ->
            when(current.direction){
                LiftDirection.DOWN -> {

                    /**
                     * Если q стоит на пути и он меньше дистанции,
                     * его и вернём
                     */
                    val range = q.from - most!!.from
                    if (range < distance){
                        retCall = q
                        distance = range
                    }
                }
                LiftDirection.UP ->{
                    val range = most!!.from - q.from
                    if (range < distance){
                        retCall = q
                        distance = range
                    }
                }
            }
        }

        return if(retCall == null) most!! else {
            removeFromQueue(retCall!!)
            retCall!!
        }
    }

    override fun getSize(): Int = queue.size

}