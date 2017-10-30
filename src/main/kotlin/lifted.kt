import Const.INIT_ERROR
import LiftOption.Lift
import LiftOption.LiftConstructor
import LiftOption.exceptions.LiftConstructorInitException


fun main(args : Array<String>){
    var lift: Lift? = null

    // println(HELLO_STRING)

    // while(true) // вечный ввод вызовов

    // while(true) // функционирование лифта

    if (args.size == 4) {

        try {
            lift = Lift(argsToLiftConstructor(args))

        } catch (e: LiftConstructorInitException){
            System.err.println(INIT_ERROR)
        } finally {
            if (lift == null)
                return
        }

    }

}

fun argsToLiftConstructor(args: Array<String>) : LiftConstructor =
        LiftConstructor(args[0].toInt(), args[1].toFloat(), args[2].toFloat(), args[3].toInt())

