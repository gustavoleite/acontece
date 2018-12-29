package gustavo.acontece.data.repository

import gustavo.acontece.data.domain.Ceremony
import io.reactivex.Single

interface CeremonyRepository {
    fun fetchCeremonies() : Single<List<Ceremony>>
}