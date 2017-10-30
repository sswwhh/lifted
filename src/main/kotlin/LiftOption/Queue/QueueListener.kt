package LiftOption.Queue

import LiftOption.LiftConsts

interface QueueListener {
    fun changeState(direction: LiftConsts.LiftDirection, state : QueueState.State)
}