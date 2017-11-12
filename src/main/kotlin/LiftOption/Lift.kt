package LiftOption

import LiftOption.LiftCalls.InsideCall
import LiftOption.LiftCalls.OutsideCall
import LiftOption.LiftConsts.LiftDirection
import LiftOption.Queue.QueueListener

/**
 * Лифт
 *  Работает следующим образом. Едет на тот этаж, куда сказали.
 *  Если ему по дороге - остановится и подберёт.
 *  Если не по дороге, то сначала доедет до той точки, куда надо,
 *  а на обратном пути остановится.
 */
class Lift(private val params : LiftConstructor) : Thread(), QueueListener {


    private val upQueue : LiftQueue = LiftQueue(LiftDirection.UP, this)
    private val downQueue : LiftQueue = LiftQueue(LiftDirection.DOWN, this)

    private val oneFloorTime : Long = (params.floorHeight * 1000 / params.speed).toLong()
    private val waitDoorsTime : Long = params.openclosetime * 1000

    private var directionQueue: LiftDirection? = null
    private var currentFloor = LiftConsts.LiftParams.START_FLOOR

    init {
        println(this)
    }

    /**
     * Вызов лифта снаружи
     */
    fun callLift(floor : Int, direction: LiftDirection){

        when(direction){
            LiftDirection.UP -> {
                // чтобы лифт приехал,
                // даже если мы вызываем его с нижнего этажа наверх,
                // когда он находится выше
                if (floor < currentFloor){
                    getQueue(swapLiftDirection(direction)).addToQueue(OutsideCall(floor, swapLiftDirection(direction)))
                    return
                }
            }
            LiftDirection.DOWN -> {
                if (floor > currentFloor){
                    getQueue(swapLiftDirection(direction)).addToQueue(OutsideCall(floor, swapLiftDirection(direction)))
                    return
                }
            }
        }

        getQueue(direction).addToQueue(OutsideCall(floor, direction))
    }

    /**
     * Нажать на кнопку внутри лифта
     */
    fun driveLift(floor: Int){

        if (currentFloor > floor){
            downQueue.addToQueue(InsideCall(floor, LiftDirection.DOWN))
        } else {
            upQueue.addToQueue(InsideCall(floor, LiftDirection.UP))
        }

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
        val direction = swapLiftDirection(baseDirection)
        getQueue(direction).removeFromQueue(floor, direction)
    }

    override fun workerGo(direction: LiftDirection) {
        synchronized(this){
            directionQueue = direction
        }
    }

    override fun run() {
        while(!Thread.currentThread().isInterrupted){
            try {

                while(directionQueue != null){

                    val first = getQueue(directionQueue!!)
                    val second = getQueue(swapLiftDirection(directionQueue!!))

                    if (first.getSize() > 0)
                        crossQueue(first)

                    sleep(1000)

                    if (second.getSize() > 0)
                        crossQueue(second)

                    synchronized(this){
                        if (first.getSize() == 0 &&
                                second.getSize() == 0){
                            directionQueue = null
                        }
                    }
                }

            } catch (e : InterruptedException){
                println(LiftConsts.Error.INTERRUPTED)
                return
            }

        }
    }

    private fun crossQueue(queue: LiftQueue) {
        while (true) {
            val next = queue.getNext(currentFloor)

            if (next != null) { // Приехали?
                // - Нет

                if (next.floor == currentFloor) {
                    printPosition(currentFloor)
                    openCloseDoors()
                } else {
                    goneFloor(next.direction)
                    printPosition(currentFloor)
                }
            } else {
                // - Да

                arrived()
                return

            }
        }
    }

    private fun arrived() {
        openCloseDoors()
    }

    /**
     * Проехали этаж
     */
    private fun goneFloor(direction: LiftDirection) {
        sleep(oneFloorTime)
        when(direction){
            LiftDirection.UP -> {
                currentFloor += 1
            }
            LiftDirection.DOWN -> {
                currentFloor -= 1
            }
        }
    }

    /**
     * Открыли дверь
     */
    private fun openCloseDoors() {
        printOpenedDoors(currentFloor)
        sleep(waitDoorsTime / 5)
        printClosedDoors(currentFloor)
    }

    private fun printPosition(currentFloor: Int) {
        System.out.println("Лифт приехал на $currentFloor этаж")
    }

    private fun printOpenedDoors(currentFloor: Int) {
        System.out.println("Лифт открывает двери на $currentFloor этаже")
    }

    private fun printClosedDoors(currentFloor: Int) {
        System.out.println("Лифт закрывает двери на $currentFloor этаже")
    }


    override fun toString(): String =
        "\nЛифт создан с параметрами: " +
        "\n- количество этажей: $params.floorNumber" +
        "\n- высота этажа: $params.floorHeight" +
        "\n- скорость лифта: $params.speed" +
        "\n- время между открытием и закрытием двери: $params.openclosetime" +
        "\n\n"
}
