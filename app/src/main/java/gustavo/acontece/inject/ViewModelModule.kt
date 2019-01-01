package gustavo.acontece.inject

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import gustavo.acontece.ui.ViewModelFactory
import gustavo.acontece.ui.ViewModelKey
import gustavo.acontece.ui.eventdetail.EventDetailViewModel
import gustavo.acontece.ui.home.HomeViewModel

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    internal abstract fun bindHomeViewModel(viewModel: HomeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(EventDetailViewModel::class)
    internal abstract fun bindEventDetailViewModel(viewModel: EventDetailViewModel): ViewModel
}