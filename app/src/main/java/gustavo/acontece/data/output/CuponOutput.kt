package gustavo.acontece.data.output

import com.google.gson.annotations.SerializedName

data class CuponOutput(

    @SerializedName("eventId")
    val eventId: String,

    @SerializedName("discount")
    val discount: Int,

    @SerializedName("id")
    val id: String
)