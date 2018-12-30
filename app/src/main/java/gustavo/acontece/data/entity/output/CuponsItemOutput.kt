package gustavo.acontece.data.entity.output

import com.google.gson.annotations.SerializedName

data class CuponsItemOutput(

	@SerializedName("eventId")
	val eventId: String,

	@SerializedName("discount")
	val discount: Int,

	@SerializedName("id")
	val id: String
)