package tam.pa.cekongkir.ui.cekresi

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import tam.pa.cekongkir.databinding.FragmentCekResiBinding
import tam.pa.cekongkir.localDB.roomDb.CekResiEntity
import tam.pa.cekongkir.localDB.sharedPref.CekOngkirSharedPref
import tam.pa.cekongkir.localDB.sharedPref._error
import tam.pa.cekongkir.network.ApiService
import tam.pa.cekongkir.network.RajaOngkirRepo
import tam.pa.cekongkir.network.Resource
import tam.pa.cekongkir.ui.cekresi.adapter.CekResiAdapter
import tam.pa.cekongkir.ui.tracking.TrackingActivity
import tam.pa.cekongkir.ui.tracking.TrackingViewModel
import tam.pa.cekongkir.ui.tracking.TrackingViewModelFactory

class CekResiFragment : Fragment() {
    private lateinit var binding: FragmentCekResiBinding
    private val preferences by lazy { CekOngkirSharedPref( requireActivity() ) }
    private val viewModelTracking by lazy { ViewModelProvider(requireActivity()).get(TrackingViewModel::class.java) }
    lateinit var cekResiAdapter: CekResiAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =  FragmentCekResiBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListener()
        setupRecyclerview()
        setupObserver()
     }

    private fun setupRecyclerview() {
        cekResiAdapter = CekResiAdapter(arrayListOf(), object : CekResiAdapter.OnAdapterListener{
            override fun OnClick(result: CekResiEntity) {

            }
        })
        binding.listWaybill.adapter = cekResiAdapter
    }

    private fun setupObserver() {
        viewModelTracking.cekResi.observe( viewLifecycleOwner, Observer {
            Log.d("_cekresientity", it.toString() )
            cekResiAdapter.setData( it )
        })
    }

    private fun setupListener() {
        binding.editWaybill.setOnClickListener {
            startActivity(Intent( requireActivity(), TrackingActivity::class.java) )
        }
    }

}
