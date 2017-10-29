package lifted.kotlin

/**
 * Лифт
 */
class Lift(val params : LiftConstructor) {

    override fun toString(): String =
        "\nЛифт создан с параметрами: " +
        "\n- количество этажей: " + params.floorNumber +
        "\n- высота этажа: " + params.floorHeight +
        "\n- скорость лифта: " + params.speed +
        "\n- время между открытием и закрытием двери: " + params.openclosetime

}