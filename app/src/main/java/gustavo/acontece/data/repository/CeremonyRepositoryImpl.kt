package gustavo.acontece.data.repository

import gustavo.acontece.data.api.CerimonyApi
import gustavo.acontece.data.entity.domain.Ceremony
import gustavo.acontece.data.entity.output.EventOutput
import io.reactivex.Single
import java.util.*
import javax.inject.Inject

class CeremonyRepositoryImpl @Inject constructor(private val cerimonyApi: CerimonyApi) : CeremonyRepository {

    override fun fetchCeremonies(): Single<List<Ceremony>> {
        return cerimonyApi
            .getAllEvents()
            .map { toCeremonyList(it) }
    }

    private fun toCeremonyList(output: List<EventOutput>) : List<Ceremony> {
        return output.map {
            with(it) {
                Ceremony (id,title,image,price, Calendar.getInstance())
            }
        }.toList()
    }
}