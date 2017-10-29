package lifted.kotlin

import lifted.kotlin.Const.HELLO_STRING
import lifted.kotlin.exceptions.LiftConstructorInitException


fun main(args : Array<String>){
    val lift: Lift?

    if (args.size == 4) {
        println(HELLO_STRING)

        try {
            lift = Lift(argsToLiftConstructor(args))
        } catch (e: LiftConstructorInitException){
            return
        }

       println(lift)
    }

}

fun argsToLiftConstructor(args: Array<String>) : LiftConstructor =
        LiftConstructor(args[0].toInt(), args[1].toFloat(), args[2].toFloat(), args[3].toInt())

