package gustavo.acontece.utils.extensions

import java.util.*

fun Long.toCalendar(): Calendar {
    return Calendar.getInstance().apply {
        timeInMillis = this@toCalendar
    }
}