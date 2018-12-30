package gustavo.acontece.data.repository

import gustavo.acontece.data.entity.domain.EventPreview
import io.reactivex.Single

interface EventRepository {
    fun fetchCeremonies() : Single<List<EventPreview>>
}