package gustavo.acontece.utils.extensions

import java.text.NumberFormat

fun Double.toBrCurrency(): String {
    return NumberFormat.getCurrencyInstance().format(this)
}