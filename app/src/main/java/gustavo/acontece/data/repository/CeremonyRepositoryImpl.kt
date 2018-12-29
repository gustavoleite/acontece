package gustavo.acontece.data.repository

import gustavo.acontece.data.domain.Ceremony
import io.reactivex.Single

class CeremonyRepositoryImpl : CeremonyRepository {
    override fun fetchCeremonies(): Single<List<Ceremony>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}