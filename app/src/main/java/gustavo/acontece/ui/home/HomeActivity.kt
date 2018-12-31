package gustavo.acontece.ui.home

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import gustavo.acontece.MainApplication
import gustavo.acontece.R
import gustavo.acontece.databinding.ActivityHomeBinding
import javax.inject.Inject

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var listAdapter: EventsListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MainApplication.appComponent.inject(this)
        val viewModel = provideViewModel()
        setupBinding(viewModel)
        setupAdapter()
        setupObservers(viewModel)
        viewModel.loadData()
    }

    private fun provideViewModel(): HomeViewModel {
        return ViewModelProviders.of(this, viewModelFactory).get(HomeViewModel::class.java)
    }

    private fun setupBinding(viewModel: HomeViewModel) {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        binding.viewModel = viewModel
        binding.homeSwipeRefreshLayout.apply {
            setOnRefreshListener { viewModel.loadData() }
            setColorSchemeColors(ContextCompat.getColor(this@HomeActivity, R.color.secondaryColor))
        }
    }

    private fun setupAdapter() {
        with(binding.homeRecyclerView) {
            layoutManager = LinearLayoutManager(this@HomeActivity)
            adapter = listAdapter
        }
    }

    private fun setupObservers(viewModel: HomeViewModel) {
        with(viewModel) {
            eventPreviewList.observe(this@HomeActivity, Observer {
                it?.let {
                    listAdapter.setEventPreviewList(it)
                }
            })
            loaderVisibility.observe(this@HomeActivity, Observer {
                it?.let {
                    binding.homeSwipeRefreshLayout.isRefreshing = it
                }
            })
        }
    }
}