package gustavo.acontece.data.entity.model

import java.util.*

data class EventPreview(
    val id: String,
    val title: String,
    val image: String,
    val price: Double,
    val date: Calendar
)