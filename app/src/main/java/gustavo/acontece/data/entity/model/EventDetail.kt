package gustavo.acontece.data.entity.model

import java.util.*

data class EventDetail(
    val id: String,
    val title: String,
    val image: String,
    val price: Double,
    val date: Calendar,
    val description: String,
    val location: Location,
    val people: List<People>,
    val cupons: List<Cupon>
)