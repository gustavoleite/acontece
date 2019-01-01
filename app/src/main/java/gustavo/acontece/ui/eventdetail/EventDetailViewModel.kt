package gustavo.acontece.ui.eventdetail

import android.arch.lifecycle.MutableLiveData
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

class EventDetailViewModel  @Inject constructor(val eventRepository: EventRepositoryImpl, val resourceProvider: ResourceProvider): BaseViewModel() {

    val event = MutableLiveData<Event>()
    val loaderVisibility = MutableLiveData<Boolean>()
    val errorMessage = MutableLiveData<String>()

    fun loadData(eventId: String) {
        loaderVisibility.value = true
        eventRepository
            .fetchEventDetail(eventId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy (
                onError = {
                    loaderVisibility.value = false
                    errorMessage.value = resourceProvider.getString(R.string.events_list_error)
                },
                onSuccess = {
                    loaderVisibility.value = false
                    event.value = it
                    errorMessage.value = null
                }
            )
            .addTo(compositeDisposable)
    }
}