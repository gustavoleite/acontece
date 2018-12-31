package gustavo.acontece.utils.extensions

import java.text.SimpleDateFormat
import java.util.*

fun Long.toCalendar(): Calendar {
    return Calendar.getInstance().apply {
        timeInMillis = this@toCalendar
    }
}

fun Calendar.toDayMonthYear(): String {
    val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    return sdf.format(this.time)
}