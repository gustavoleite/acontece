package gustavo.acontece.ui.checkin

import android.arch.lifecycle.MutableLiveData
import android.databinding.ObservableField
import gustavo.acontece.data.repository.EventRepositoryImpl
import gustavo.acontece.ui.base.BaseViewModel
import gustavo.acontece.utils.Event
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class CheckinViewModel @Inject constructor(val eventRepositoryImpl: EventRepositoryImpl) : BaseViewModel() {

    val navigation = MutableLiveData<Event<CheckinNavigation>>()
    val name = ObservableField<String>()
    val email = ObservableField<String>()
    lateinit var eventId: String

    fun onClosePressed() {
        navigation.value = Event(CheckinNavigation.CHECKIN)
    }

    fun onCheckinPressed() {
        eventRepositoryImpl
            .makeCheckin(eventId, "gustavo", "")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy {

            }
            .addTo(compositeDisposable)
    }
}