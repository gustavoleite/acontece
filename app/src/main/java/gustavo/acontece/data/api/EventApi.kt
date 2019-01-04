package gustavo.acontece.data.api

import gustavo.acontece.data.entity.input.CheckinInput
import gustavo.acontece.data.entity.output.DataOutput
import gustavo.acontece.data.entity.output.EventOutput
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface EventApi {
    @GET("events")
    fun getAllEvents() : Single<List<EventOutput>>

    @GET("events/{id}")
    fun getEvent(@Path("id") id: String) : Single<EventOutput>

    @POST("checkin")
    fun checkin(@Body input: CheckinInput) : Single<DataOutput>
}