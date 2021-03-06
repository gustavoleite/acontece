package gustavo.acontece.data.entity.output

import com.google.gson.annotations.SerializedName

data class EventOutput(

    @SerializedName("id")
    val id: String,

    @SerializedName("title")
    val title: String,

    @SerializedName("date")
    val date: Long,

    @SerializedName("image")
    val image: String,

    @SerializedName("price")
    val price: Double,

    @SerializedName("latitude")
    val latitude: String,

    @SerializedName("longitude")
    val longitude: String,

    @SerializedName("description")
    val description: String,

    @SerializedName("cupons")
    val cuponList: List<CuponOutput>,

    @SerializedName("people")
    val peopleList: List<PeopleOutput>
)