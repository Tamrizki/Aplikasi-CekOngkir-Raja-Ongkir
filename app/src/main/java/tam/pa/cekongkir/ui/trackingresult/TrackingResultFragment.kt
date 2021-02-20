package tam.pa.cekongkir.ui.trackingresult

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import tam.pa.cekongkir.databinding.FragmentTrackingResultBinding
import tam.pa.cekongkir.localDB.sharedPref._error
import tam.pa.cekongkir.network.Resource
import tam.pa.cekongkir.network.response.TrackingResponse
import tam.pa.cekongkir.ui.tracking.TrackingViewModel
import tam.pa.cekongkir.ui.trackingresult.adapter.TrackingResultAdapter

class TrackingResultFragment : Fragment() {
    private lateinit var binding: FragmentTrackingResultBinding
    private val viewModel by lazy { ViewModelProvider(requireActivity()).get(TrackingViewModel::class.java) }
    private lateinit var trackingAdapter: TrackingResultAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =  FragmentTrackingResultBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservable()
    }

    private fun setupRecyclerview(dataList: List<TrackingResponse.dataRajaOngkir.dataresult.datamanifest>) {
        trackingAdapter = TrackingResultAdapter( dataList )
        binding.listManifest.adapter = trackingAdapter
    }

    private fun setupView(dataTrack: TrackingResponse.dataRajaOngkir.dataresult) {
        binding.textStatus.setText( dataTrack.delivery_status.status )
        binding.textReceiver.setText( dataTrack.delivery_status.pod_receiver )
        binding.textDate.setText( dataTrack.delivery_status.pod_date+" "+dataTrack.delivery_status.pod_time )
    }

    private fun setupObservable() {
        viewModel.trackingResponse.observe(viewLifecycleOwner, Observer {
            when(it){
                is Resource.Loading ->{
                    loading(true)
                    Log.d( "_trackingresponse", "Loading!!" )
                }
                is Resource.Success ->{
                    loading(false)
                    Log.d( "_trackingresponse", it.data.toString() )
                    setupRecyclerview(it.data!!.rajaongkir.result.manifest)
                    setupView( it.data!!.rajaongkir.result )
                }
                is Resource.Error ->{
                    loading(false)
                    Log.d( _error, it.message.toString() )
                }
            }
        })
    }

    private fun loading(load: Boolean){
        if (load) binding.refreshWaybill.isRefreshing = true
        else binding.refreshWaybill.isRefreshing = false
    }
}