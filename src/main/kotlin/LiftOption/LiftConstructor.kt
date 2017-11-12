package LiftOption

import LiftOption.LiftConsts.Code
import LiftOption.LiftConsts.LiftParams
import LiftOption.exceptions.LiftConstructorInitException

/**
 * Основные параметры лифта
 */
class LiftConstructor : Throwable {

    private var floorNumber: Int
    internal var floorHeight: Float
    internal var speed: Float
    internal var openclosetime: Long

    constructor(floorNumber: Int, floorHeight: Float,
                speed: Float, opencloseTime: Long){

        this.floorNumber = floorNumber
        this.floorHeight = floorHeight
        this.speed = speed
        this.openclosetime = opencloseTime

        if (floorNumber < LiftParams.FLOOR_NUMBER_MIN ||
            floorNumber > LiftParams.FLOOR_NUMBER_MAX)
            throw LiftConstructorInitException(Code.FLOOR_NUMBER)

        // Перевести высоту этажа в метры
        if (floorHeight > LiftParams.FLOOR_HEIGHT_MIN_CM)
            this.floorHeight /= 100
        else
            if (floorHeight < LiftParams.FLOOR_HEIGHT_MIN_M ||
                floorHeight > LiftParams.FLOOR_HEIGHT_MAX_M)
                throw LiftConstructorInitException(Code.FLOOR_HEIGHT)

        if (speed <= LiftParams.SPEED_MIN ||
            speed > LiftParams.SPEED_MAX)
            throw LiftConstructorInitException(Code.SPEED)

        if (opencloseTime <= LiftParams.OPENCLOSETIME_MIN ||
            opencloseTime > LiftParams.OPENCLOSETIME_MAX)
            throw LiftConstructorInitException(Code.OPENCLOSETIME)

    }
}