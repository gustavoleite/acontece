package gustavo.acontece.utils.extensions

import java.text.NumberFormat
import java.util.*

fun Double.toBrCurrency(): String {
    val ptBr = Locale("pt", "BR")
    return NumberFormat.getCurrencyInstance(ptBr).format(this)
}