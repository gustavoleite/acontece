package gustavo.acontece.ui.events

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.airbnb.lottie.LottieDrawable
import gustavo.acontece.MainApplication
import gustavo.acontece.R
import gustavo.acontece.databinding.ActivityEventsBinding
import gustavo.acontece.ui.eventdetail.EventDetailActivity
import gustavo.acontece.utils.EventObserver
import javax.inject.Inject

class EventsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEventsBinding

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

    private fun provideViewModel(): EventsViewModel {
        return ViewModelProviders.of(this, viewModelFactory).get(EventsViewModel::class.java)
    }

    private fun setupBinding(viewModel: EventsViewModel) {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_events)
        binding.viewModel = viewModel
        binding.homeSwipeRefreshLayout.apply {
            setOnRefreshListener { viewModel.loadData() }
            setColorSchemeColors(ContextCompat.getColor(this@EventsActivity, R.color.secondaryColor))
        }
    }

    private fun setupAdapter() {
        listAdapter.setClickListener { it ->
            startActivity(EventDetailActivity.newInstance(Intent(this, EventDetailActivity::class.java), it.id))
        }
        with(binding.homeRecyclerView) {
            layoutManager = LinearLayoutManager(this@EventsActivity)
            adapter = listAdapter
        }
    }

    private fun setupObservers(viewModel: EventsViewModel) {
        with(viewModel) {
            eventPreviewList.observe(this@EventsActivity, Observer {
                it?.let {
                    listAdapter.setEventPreviewList(it)
                }
            })
            loaderVisibility.observe(this@EventsActivity, Observer {
                it?.let {
                    binding.homeSwipeRefreshLayout.isRefreshing = it
                }
            })
            errorMessage.observe(this@EventsActivity, EventObserver {
                binding.homeFaceAnimation.repeatCount = LottieDrawable.INFINITE
                if (it.isBlank()) {
                    hideNetworkingInfo()
                } else if (!binding.homeFaceAnimation.isAnimating) {
                    showNetworkingInfo(it)
                }
            })
        }
    }

    private fun showNetworkingInfo(it: String?) {
        listAdapter.setEventPreviewList(emptyList())
        binding.homeMessageTextView.text = it
        binding.homeFaceAnimation.playAnimation()
        binding.homeFaceAnimation.visibility = View.VISIBLE
    }

    private fun hideNetworkingInfo() {
        binding.homeMessageTextView.text = null
        binding.homeFaceAnimation.pauseAnimation()
        binding.homeFaceAnimation.visibility = View.GONE
    }
}