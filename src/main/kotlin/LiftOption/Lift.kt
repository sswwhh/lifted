package LiftOption

import LiftOption.LiftCalls.InsideCall
import LiftOption.LiftCalls.OutsideCall
import LiftOption.LiftConsts.LiftDirection
import LiftOption.Queue.QueueLeftListener

/**
 * Лифт
 *  Работает следующим образом. Едет на тот этаж, куда сказали.
 *  Если ему по дороге - остановится и подберёт.
 *  Если не по дороге, то сначала доедет до той точки, куда надо,
 *  а на обратном пути остановится.
 */
class Lift(private val params : LiftConstructor) : QueueLeftListener {


    private val upQueue : LiftQueue = LiftQueue(LiftDirection.UP, this)
    private val downQueue : LiftQueue = LiftQueue(LiftDirection.DOWN, this)

    private val oneFloorTime = params.floorHeight / params.speed
    private val waitDoorsTime = params.openclosetime

    init {
        println(this)
    }

    fun callLift(floor : Int, direction: LiftDirection){

        getQueue(direction).addToQueue(OutsideCall(floor, direction))
    }

    fun driveLift(floor: Int){

        downQueue.addToQueue(InsideCall(floor, LiftDirection.DOWN))
        upQueue.addToQueue(InsideCall(floor, LiftDirection.UP))
    }

    private fun swapLiftDirection(direction: LiftDirection) : LiftDirection =
        if (direction == LiftDirection.DOWN)
                LiftDirection.UP
        else LiftDirection.DOWN

    private fun getQueue(direction: LiftDirection) =
        when(direction){
            LiftDirection.DOWN -> {
                downQueue
            }
            LiftDirection.UP -> {
                upQueue
            }
        }

    override fun floorLeft(floor: Int, baseDirection: LiftDirection) {
        getQueue(swapLiftDirection(baseDirection)).removeFromQueue(floor)
    }




    override fun toString(): String =
        "\nЛифт создан с параметрами: " +
        "\n- количество этажей: $params.floorNumber" +
        "\n- высота этажа: $params.floorHeight" +
        "\n- скорость лифта: $params.speed" +
        "\n- время между открытием и закрытием двери: $params.openclosetime" +
        "\n\n"
}
