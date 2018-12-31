package gustavo.acontece.ui.home

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import gustavo.acontece.MainApplication
import gustavo.acontece.R
import gustavo.acontece.databinding.ActivityHomeBinding
import javax.inject.Inject

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    @Inject
    private lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    private lateinit var listAdapter: EventsListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MainApplication.appComponent.inject(this)
        val viewModel = provideViewModel()
        setupBinding(viewModel)
        setupAdapter()
        setupObservers(viewModel)
        viewModel.loadData()
    }

    private fun provideViewModel() : HomeViewModel {
        return ViewModelProviders.of(this, viewModelFactory).get(HomeViewModel::class.java)
    }

    private fun setupBinding(viewModel: HomeViewModel) {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        binding.viewModel = viewModel
    }

    private fun setupAdapter() {
        with(binding.homeRecyclerView) {
            layoutManager = LinearLayoutManager(this@HomeActivity)
            adapter = listAdapter
        }
    }

    private fun setupObservers(viewModel: HomeViewModel) {
        viewModel.eventPreviewList.observe(this, Observer {
            it?.let {
                listAdapter.setEventPreviewList(it)
            }
        })
    }
}