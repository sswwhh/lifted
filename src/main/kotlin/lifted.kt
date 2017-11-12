import LiftOption.LiftConsts.Texts
import LiftOption.Lift
import LiftOption.LiftConstructor
import LiftOption.LiftConsts
import LiftOption.LiftConsts.Texts.HELLO_STRING
import LiftOption.exceptions.LiftConstructorInitException


fun main(args : Array<String>){

    val theLift = checkLift(args)

    if (theLift != null){
        // start lift in another thread
        theLift.start()

        // Вечно слушаем команды от пользователя
        while(true){
            val command = readLine()!!
            val floor = command.subSequence(1, command.length).toString().toInt()

            when(command[0]){
                'u' -> {
                    // вызвать лифт вверх
                    theLift.callLift(floor, LiftConsts.LiftDirection.UP)
                }
                'd' -> {
                    // вызвать лифт вниз
                    theLift.callLift(floor, LiftConsts.LiftDirection.DOWN)
                }
                'l' -> {
                    // добавить команду в лифт
                    theLift.driveLift(floor)
                }
            }
        }

    }
    else {
        println(HELLO_STRING)
    }




    checkLift(args)

}

private fun checkLift(args: Array<String>) : Lift? {
    var lift: Lift? = null

    if (args.size == 4) {
        try {
            lift = Lift(argsToLiftConstructor(args))

        } catch (e: LiftConstructorInitException) {
            System.err.println(Texts.INIT_ERROR)
        } finally {
            return lift
        }

    }

    return null
}

fun argsToLiftConstructor(args: Array<String>) : LiftConstructor =
        LiftConstructor(args[0].toInt(), args[1].toFloat(), args[2].toFloat(), args[3].toLong())

