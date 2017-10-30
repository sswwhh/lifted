package LiftOption

import Const.FLOOR_HEIGHT_MAX_M
import Const.FLOOR_HEIGHT_MIN_CM
import Const.FLOOR_HEIGHT_MIN_M
import Const.FLOOR_NUMBER_MAX
import Const.FLOOR_NUMBER_MIN
import Const.OPENCLOSETIME_MAX
import Const.OPENCLOSETIME_MIN
import Const.SPEED_MAX
import Const.SPEED_MIN
import Const.Code
import LiftOption.exceptions.LiftConstructorInitException

/**
 * Основные параметры лифта
 */
class LiftConstructor : Throwable {

    internal var floorNumber: Int
    internal var floorHeight: Float
    internal var speed: Float
    internal var openclosetime: Int

    constructor(floorNumber: Int, floorHeight: Float,
                speed: Float, opencloseTime: Int){

        this.floorNumber = floorNumber
        this.floorHeight = floorHeight
        this.speed = speed
        this.openclosetime = opencloseTime

        if (floorNumber < FLOOR_NUMBER_MIN ||
            floorNumber > FLOOR_NUMBER_MAX)
            throw LiftConstructorInitException(Code.FLOOR_NUMBER)

        // Перевести высоту этажа в метры
        if (floorHeight > FLOOR_HEIGHT_MIN_CM)
            this.floorHeight /= 100
        else
            if (floorHeight < FLOOR_HEIGHT_MIN_M ||
                floorHeight > FLOOR_HEIGHT_MAX_M)
                throw LiftConstructorInitException(Code.FLOOR_HEIGHT)

        if (speed <= SPEED_MIN ||
            speed > SPEED_MAX)
            throw LiftConstructorInitException(Code.SPEED)

        if (opencloseTime <= OPENCLOSETIME_MIN ||
            opencloseTime > OPENCLOSETIME_MAX)
            throw LiftConstructorInitException(Code.OPENCLOSETIME)

    }
}