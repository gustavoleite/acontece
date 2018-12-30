package gustavo.acontece.data.entity.domain

import java.util.*

data class Ceremony (
    val id: String,
    val title: String,
    val image: String,
    val price: Double,
    val date: Calendar
)