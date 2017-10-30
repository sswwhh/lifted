package LiftOption.LiftCalls

import LiftOption.LiftDirection

interface Call {
    val from: Int
    val direction: LiftDirection
}