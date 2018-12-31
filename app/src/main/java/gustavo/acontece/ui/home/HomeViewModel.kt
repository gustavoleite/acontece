package gustavo.acontece.ui.home

import android.arch.lifecycle.MutableLiveData
import gustavo.acontece.data.entity.model.EventPreview
import gustavo.acontece.data.repository.EventRepositoryImpl
import gustavo.acontece.ui.base.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class HomeViewModel @Inject constructor(val eventRepository: EventRepositoryImpl): BaseViewModel() {

    val eventPreviewList = MutableLiveData<List<EventPreview>>()

    fun loadData() {
        eventRepository
            .fetchEvents()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy (
                onError = {
                    //eventPreviewList.value = it
                },
                onSuccess = {
                    eventPreviewList.value = it
                }
            )
            .addTo(compositeDisposable)
    }
}