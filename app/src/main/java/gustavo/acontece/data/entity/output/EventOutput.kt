package gustavo.acontece.data.entity.output

import com.google.gson.annotations.SerializedName

data class EventOutput(

	@SerializedName("date")
	val date: Long,

	@SerializedName("image")
	val image: String,

	@SerializedName("cupons")
	val cupons: List<CuponsItemOutput>,

	@SerializedName("price")
	val price: Double,

	@SerializedName("latitude")
	val latitude: String,

	@SerializedName("description")
	val description: String,

	@SerializedName("id")
	val id: String,

	@SerializedName("title")
	val title: String,

	@SerializedName("people")
	val people: List<PeopleItemOutput>,

	@SerializedName("longitude")
	val longitude: String
)