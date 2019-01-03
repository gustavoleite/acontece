package gustavo.acontece.inject

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import gustavo.acontece.ui.ViewModelFactory
import gustavo.acontece.ui.ViewModelKey
import gustavo.acontece.ui.checkin.CheckinViewModel
import gustavo.acontece.ui.eventdetail.EventDetailViewModel
import gustavo.acontece.ui.events.EventsViewModel

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(EventsViewModel::class)
    internal abstract fun bindHomeViewModel(viewModel: EventsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(EventDetailViewModel::class)
    internal abstract fun bindEventDetailViewModel(viewModel: EventDetailViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CheckinViewModel::class)
    internal abstract fun bindCheckinViewModel(viewModel: CheckinViewModel): ViewModel
}