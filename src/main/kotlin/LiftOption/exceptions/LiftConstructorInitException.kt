package LiftOption.exceptions

import LiftOption.LiftConsts.Code
import LiftOption.LiftConsts.Error


class LiftConstructorInitException(type : Code) : ExceptionInInitializerError(){

    init {
        when(type){
            Code.FLOOR_NUMBER -> {
                System.err.println(Error.FLOOR_NUMBER_ERROR)
            }
            Code.FLOOR_HEIGHT -> {
                System.err.println(Error.FLOOR_HEIGHT_ERROR)
            }
            Code.SPEED -> {
                System.err.println(Error.SPEED_ERROR)
            }
            Code.OPENCLOSETIME -> {
                System.err.println(Error.OPENCLOSETIME_ERROR)
            }
        }
    }
}