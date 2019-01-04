package gustavo.acontece.data.entity.input

import com.google.gson.annotations.SerializedName

data class CheckinInput(

	@SerializedName("eventId")
	val eventId: String,

	@SerializedName("name")
	val name: String,

	@SerializedName("email")
	val email: String
)