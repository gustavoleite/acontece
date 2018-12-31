package gustavo.acontece.ui.home

import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField
import gustavo.acontece.data.repository.EventRepositoryImpl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class HomeViewModel @Inject constructor(val eventRepository: EventRepositoryImpl): ViewModel() {

    val title = ObservableField<String>("Ola mundo")
    var compositeDisposable = CompositeDisposable()

    fun loadData() {
        eventRepository
            .fetchEvents()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy (
                onError = {
                    title.set("Ocorreu um erro na request")
                },
                onSuccess = { title.set(it[0].title)}
            )
            .addTo(compositeDisposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}