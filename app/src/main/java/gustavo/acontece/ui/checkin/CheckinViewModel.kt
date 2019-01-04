package gustavo.acontece.ui.checkin

import android.arch.lifecycle.MutableLiveData
import android.databinding.ObservableField
import gustavo.acontece.R
import gustavo.acontece.data.repository.EventRepositoryImpl
import gustavo.acontece.ui.base.BaseViewModel
import gustavo.acontece.utils.Event
import gustavo.acontece.utils.resource.ResourceProvider
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class CheckinViewModel @Inject constructor(
    val eventRepository: EventRepositoryImpl,
    resourceProvider: ResourceProvider
) : BaseViewModel() {

    val closeEvent = MutableLiveData<Event<Boolean>>()
    val requestStatus = MutableLiveData<CheckinStatus>()
    val name = ObservableField<String>()
    val email = ObservableField<String>()
    val checkinTitle = resourceProvider.getString(R.string.checkin_name_title)
    val nameHint = resourceProvider.getString(R.string.checkin_name_hint)
    val emailHint = resourceProvider.getString(R.string.checkin_email_hint)
    lateinit var eventId: String

    fun onClosePressed() {
        closeEvent.value = Event(true)
    }

    fun onCheckinPressed() {
        requestStatus.value = CheckinStatus.LOADING
        eventRepository
            .makeCheckin(eventId, name.get().orEmpty(), email.get().orEmpty())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = {
                    requestStatus.value = CheckinStatus.COMPLETE
                },
                onError = {
                    requestStatus.value = CheckinStatus.ERROR
                }
            )
            .addTo(compositeDisposable)
    }
}