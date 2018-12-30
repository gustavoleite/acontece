package gustavo.acontece.data.output

import com.google.gson.annotations.SerializedName

data class PeopleOutput(

    @SerializedName("eventId")
    val eventId: String,

    @SerializedName("name")
    val name: String,

    @SerializedName("id")
    val id: String,

    @SerializedName("picture")
    val picture: String
)