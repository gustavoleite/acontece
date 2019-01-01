package gustavo.acontece.ui.eventdetail

import android.arch.lifecycle.MutableLiveData
import android.databinding.ObservableField
import gustavo.acontece.R
import gustavo.acontece.data.entity.model.Event
import gustavo.acontece.data.repository.EventRepositoryImpl
import gustavo.acontece.ui.base.BaseViewModel
import gustavo.acontece.utils.resource.ResourceProvider
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class EventDetailViewModel  @Inject constructor(val eventRepository: EventRepositoryImpl, resourceProvider: ResourceProvider): BaseViewModel() {

    val event = MutableLiveData<Event>()
    val image = ObservableField<String>()
    val description = ObservableField<String>()
    val peopleSectionTitle = resourceProvider.getString(R.string.events_detail_organizers)

    fun loadData(eventId: String) {
        eventRepository
            .fetchEventDetail(eventId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy (
                onSuccess = {
                    setData(it)
                }
            )
            .addTo(compositeDisposable)
    }

    private fun setData(it: Event?) {
        event.value = it
        description.set(it?.description)
        image.set(it?.image)
    }
}