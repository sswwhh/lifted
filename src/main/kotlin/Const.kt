package lifted.kotlin

/**
 * Параметрические значения для лифта
 */
object Const {

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

    val HELLO_STRING = "Необходимо ввести: \n" +
            "(1) количество этажей, \n" +
            "(2) высоту одного этажа, \n" +
            "(3) скорость лифта, \n" +
            "(4) время между открытием и закрытием дверей лифта\n\n"

    object Error{
        val FLOOR_NUMBER_ERROR = "Количество этажей должно быть от 5 до 20"
        val FLOOR_HEIGHT_ERROR = "Высота этажа должна быть от 2.2м до 10м"
        val SPEED_ERROR = "Скорость лифта должна вырьироваться от 1 до 4 м\\с"
        val OPENCLOSETIME_ERROR = "Время между открытием и закрытием двери варьируется от 15с до 120с"

    }

    enum class Code{
        FLOOR_NUMBER, FLOOR_HEIGHT, SPEED, OPENCLOSETIME
    }
}