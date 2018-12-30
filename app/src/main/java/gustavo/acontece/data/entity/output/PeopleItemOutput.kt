package gustavo.acontece.data.entity.output

import com.google.gson.annotations.SerializedName

data class PeopleItemOutput(

	@SerializedName("eventId")
	val eventId: String,

	@SerializedName("name")
	val name: String,

	@SerializedName("id")
	val id: String,

	@SerializedName("picture")
	val picture: String
)