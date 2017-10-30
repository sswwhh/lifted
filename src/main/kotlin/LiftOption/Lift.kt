package LiftOption

import LiftOption.LiftCalls.OutsideCall

/**
 * Лифт
 */
class Lift(val params : LiftConstructor) {

    val upQueue : LiftQueue = LiftQueue(LiftDirection.UP)
    val downQueue : LiftQueue = LiftQueue(LiftDirection.DOWN)
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



    }

    /**
     * Пока едем, можем переоптимизировать очередь
     */
    private fun moving(){

    }


    override fun toString(): String =
        "\nЛифт создан с параметрами: " +
        "\n- количество этажей: " + params.floorNumber +
        "\n- высота этажа: " + params.floorHeight +
        "\n- скорость лифта: " + params.speed +
        "\n- время между открытием и закрытием двери: " + params.openclosetime +
        "\n\n"
}
