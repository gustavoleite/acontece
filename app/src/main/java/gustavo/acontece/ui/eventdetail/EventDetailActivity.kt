package gustavo.acontece.ui.eventdetail

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import gustavo.acontece.MainApplication
import gustavo.acontece.R
import gustavo.acontece.data.entity.model.Location
import gustavo.acontece.databinding.ActivityEventDetailBinding
import gustavo.acontece.utils.SupportMapFragmentWrapper
import kotlinx.android.synthetic.main.activity_event_detail.*
import javax.inject.Inject


class EventDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEventDetailBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var listAdapter: PeopleAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MainApplication.appComponent.inject(this)
        val viewModel = provideViewModel()
        setupBinding(viewModel)
        setupAdapter()
        setupObservers(viewModel)
        setupMap()
        viewModel.loadData(intent.extras?.getString(EVENT_DETAIL_ARG) ?: throw Exception())
    }

    private fun provideViewModel(): EventDetailViewModel {
        return ViewModelProviders.of(this, viewModelFactory).get(EventDetailViewModel::class.java)
    }

    private fun setupBinding(viewModel: EventDetailViewModel) {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_event_detail)
        binding.viewModel = viewModel
        event_detail_map.isResumed
    }

    private fun setupAdapter() {
        with(binding.eventDetailRecyclerView) {
            layoutManager = LinearLayoutManager(this@EventDetailActivity, LinearLayoutManager.HORIZONTAL, false)
            adapter = listAdapter
        }
    }

    private fun setupObservers(viewModel: EventDetailViewModel) {
        with(viewModel) {
            event.observe(this@EventDetailActivity, Observer {
                it?.let { it ->
                    binding.toolbar.title = it.title
                    listAdapter.setPeopleList(it.peoples)
                    setMapLocation(it.location)
                }
            })
        }
    }

    private fun setupMap() {
        (event_detail_map as SupportMapFragmentWrapper).setListener(
            object : SupportMapFragmentWrapper.OnTouchListener {
                override fun onTouch() {
                    event_detail_nested_scroll_view.requestDisallowInterceptTouchEvent(true)
                }
            }
        )
    }

    private fun setMapLocation(location: Location) {
        (event_detail_map as SupportMapFragment).getMapAsync {
            val cameraPosition = CameraPosition.Builder()
                .target(LatLng(location.latitude, location.longitude))
                .zoom(14.3f)
                .build()
            it.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))
            it.addMarker(
                MarkerOptions()
                    .position(LatLng(location.latitude, location.longitude))
                    .title(location.title)
            )
        }
    }

    companion object {

        private const val EVENT_DETAIL_ARG = "EVENT_DETAIL_ARG"

        fun newInstance(intent: Intent, eventId: String): Intent {
            return intent.apply {
                putExtra(EVENT_DETAIL_ARG, eventId)
            }
        }
    }
}