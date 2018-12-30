package gustavo.acontece.data.api

import gustavo.acontece.data.entity.output.EventOutput
import io.reactivex.Single
import retrofit2.http.GET

interface CerimonyApi {
    @GET("/events")
    fun getAllEvents() : Single<List<EventOutput>>
}