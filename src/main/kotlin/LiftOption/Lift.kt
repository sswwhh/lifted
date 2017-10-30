package LiftOption

import LiftOption.LiftCalls.OutsideCall
import LiftOption.LiftConsts.LiftDirection
import LiftOption.Queue.QueueListener
import LiftOption.Queue.QueueState

/**
 * Лифт
 */
class Lift(val params : LiftConstructor) : QueueListener {

    val upQueue : LiftQueue = LiftQueue(LiftDirection.UP, this)
    val downQueue : LiftQueue = LiftQueue(LiftDirection.DOWN, this)
    var direction : LiftDirection? = null


    init {
        println(this)

        downQueue.addToQueue(OutsideCall(10, LiftDirection.DOWN))
        downQueue.addToQueue(OutsideCall(8, LiftDirection.DOWN))
        downQueue.addToQueue(OutsideCall(6, LiftDirection.DOWN))
        downQueue.addToQueue(OutsideCall(12, LiftDirection.DOWN))
        downQueue.addToQueue(OutsideCall(14, LiftDirection.DOWN))
        downQueue.addToQueue(OutsideCall(20, LiftDirection.DOWN))

        println(downQueue.getSize())

        downQueue.addToQueue(OutsideCall(12, LiftDirection.DOWN))
        downQueue.addToQueue(OutsideCall(14, LiftDirection.DOWN))
        downQueue.addToQueue(OutsideCall(20, LiftDirection.DOWN))

        println(downQueue.getSize())

        downQueue.addToQueue(OutsideCall(11, LiftDirection.DOWN))
        downQueue.addToQueue(OutsideCall(13, LiftDirection.DOWN))
        downQueue.addToQueue(OutsideCall(19, LiftDirection.DOWN))

        println(downQueue.getSize())


        downQueue.getNext(4)
        downQueue.getNext(5)
        downQueue.getNext(10)

        println(downQueue.getSize())

        downQueue.getNext(1)
        downQueue.getNext(12)
        downQueue.getNext(20)

        println(downQueue.getSize())

    }

    fun callLift(floor : Int, direction: LiftDirection){
        when(direction){
            LiftDirection.DOWN -> {
                downQueue.addToQueue(OutsideCall(floor, direction))
            }
            LiftDirection.UP -> {
                upQueue.addToQueue(OutsideCall(floor, direction))
            }
        }
    }

    fun driveLift(floor: Int){

    }

    private fun moving(){
        while (true){
            return
        }
    }

    override fun changeState(direction: LiftDirection, state : QueueState.State) {
        when(state){
            QueueState.State.STOPPED -> {
                // Достигнут максимальный элемент в очереди -
                // запустим другую очередь
                startQueue(otherwiseDirection(direction))
            }
        }
    }

    private fun otherwiseDirection(direction: LiftDirection) : LiftDirection{
        return if (direction == LiftDirection.DOWN)
                LiftDirection.UP
            else LiftDirection.DOWN
    }

    private fun startQueue(direction: LiftDirection){
        when(direction){
            LiftDirection.DOWN -> {
                if (downQueue.getSize() > 0)
                    downQueue.work()
            }
            LiftDirection.UP -> {
                if (upQueue.getSize() > 0)
                    upQueue.work()
            }
        }
    }

    override fun toString(): String =
        "\nЛифт создан с параметрами: " +
        "\n- количество этажей: " + params.floorNumber +
        "\n- высота этажа: " + params.floorHeight +
        "\n- скорость лифта: " + params.speed +
        "\n- время между открытием и закрытием двери: " + params.openclosetime +
        "\n\n"
}
