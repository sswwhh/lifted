package LiftOption.Queue

import LiftOption.LiftConsts

/**
 * Слушатель для очереди:
 * - Когда прошли этаж, его нужно удалить в другой очереди
 * - когда чего-то добавили, нужно стартовать воркер, если он еще не стартовал
 */
interface QueueListener {
    fun floorLeft(floor : Int, baseDirection : LiftConsts.LiftDirection)
    fun workerGo(direction : LiftConsts.LiftDirection)
}