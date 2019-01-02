package gustavo.acontece.ui.eventdetail

import android.arch.lifecycle.MutableLiveData
import android.databinding.ObservableField
import gustavo.acontece.R
import gustavo.acontece.data.entity.model.EventDetail
import gustavo.acontece.data.repository.EventRepositoryImpl
import gustavo.acontece.ui.base.BaseViewModel
import gustavo.acontece.utils.resource.ResourceProvider
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class EventDetailViewModel  @Inject constructor(val eventRepository: EventRepositoryImpl, resourceProvider: ResourceProvider): BaseViewModel() {

    val event = MutableLiveData<EventDetail>()
    val image = ObservableField<String>()
    val description = ObservableField<String>()
    val peopleSection = resourceProvider.getString(R.string.events_detail_organizers)
    val locationSection = resourceProvider.getString(R.string.events_detail_location)

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

    private fun setData(it: EventDetail?) {
        event.value = it
        description.set(it?.description)
        image.set(it?.image)
    }
}