package LiftOption.LiftCalls

import LiftOption.LiftConsts.LiftDirection

interface Call {
    val floor: Int
    val direction: LiftDirection
}