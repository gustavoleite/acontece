package gustavo.acontece.ui.checkin

import android.databinding.ObservableField
import gustavo.acontece.data.repository.EventRepository
import gustavo.acontece.ui.base.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class CheckinViewModel @Inject constructor(val eventRepository: EventRepository) : BaseViewModel() {

    val name = ObservableField<String>()
    val email = ObservableField<String>()
    lateinit var eventId: String

    fun onCheckinPressed() {
        eventRepository
            .makeCheckin(eventId, "gustavo", "")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy {

            }
            .addTo(compositeDisposable)
    }
}