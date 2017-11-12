package LiftOption.Queue

import LiftOption.LiftConsts

/**
 * Когда прошли этаж, его нужно удалить в другой очереди
 */
interface QueueLeftListener {
    fun floorLeft(floor : Int, baseDirection : LiftConsts.LiftDirection)
}