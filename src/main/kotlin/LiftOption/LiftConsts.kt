package LiftOption

object LiftConsts {

    object LiftParams {
        val FLOOR_NUMBER_MIN = 5
        val FLOOR_NUMBER_MAX = 20

        val FLOOR_HEIGHT_MIN_CM = 220
        val FLOOR_HEIGHT_MAX_CM = 1000

        val FLOOR_HEIGHT_MIN_M = 2.2
        val FLOOR_HEIGHT_MAX_M = 10

        val SPEED_MIN = 1
        val SPEED_MAX = 4

        val OPENCLOSETIME_MIN = 15
        val OPENCLOSETIME_MAX = 120

        val START_FLOOR = 1
    }

    enum class Code {
        FLOOR_NUMBER, FLOOR_HEIGHT, SPEED, OPENCLOSETIME
    }

    object Error {
        val FLOOR_NUMBER_ERROR = "Количество этажей должно быть от 5 до 20"
        val FLOOR_HEIGHT_ERROR = "Высота этажа должна быть от 2.2м до 10м"
        val SPEED_ERROR = "Скорость лифта должна варьироваться от 1 до 4 м\\с"
        val OPENCLOSETIME_ERROR = "Время между открытием и закрытием двери варьируется от 15с до 120с"

        val INTERRUPTED = "Вы прервали работу лифта, и он застрял"
    }


    enum class LiftDirection {
        UP, DOWN
    }

    fun swapDirection(direction : LiftDirection) : LiftDirection{
        when(direction){
            LiftDirection.UP ->{
                return LiftDirection.DOWN
            }
            LiftDirection.DOWN -> {
                return LiftDirection.DOWN
            }
        }
    }

    object Texts {
        val HELLO_STRING = "Необходимо ввести: \n" +
                "(1) количество этажей (от 5 до 20), \n" +
                "(2) высоту одного этажа (от 2.2 до 10 м), \n" +
                "(3) скорость лифта (от 1 до 4 м\\с), \n" +
                "(4) время между открытием и закрытием дверей лифта (от 15 до 120 сек)\n\n"

        val INIT_ERROR = "Не могу проинициализировать лифт"
    }
}