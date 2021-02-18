package tam.pa.cekongkir.ui.biayaOngkir

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import tam.pa.cekongkir.databinding.FragmentBiayaBinding
import tam.pa.cekongkir.localDB.sharedPref._error
import tam.pa.cekongkir.localDB.sharedPref.destination
import tam.pa.cekongkir.localDB.sharedPref.origin
import tam.pa.cekongkir.network.Resource
import tam.pa.cekongkir.ui.biayaOngkir.adapter.BiayaAdapter
import tam.pa.cekongkir.ui.kota.KotaActivity

class BiayaFragment : Fragment() {
    private val viewModel by lazy { ViewModelProvider(requireActivity()).get(BiayaViewModel::class.java) }
    private lateinit var binding: FragmentBiayaBinding
    private var originId: String? = ""
    private var destinationId: String? = ""
    private lateinit var biayaAdapter: BiayaAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBiayaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setLoading(false)
        setupListener()
        setupRecyclerview()
        setupObserver()
    }

    private fun setupRecyclerview() {
        biayaAdapter = BiayaAdapter(arrayListOf())
        binding.listCost.adapter = biayaAdapter

    }

    private fun setupListener() {
        binding.editOrigin.setOnClickListener {
            startActivity(Intent(context, KotaActivity::class.java).putExtra("type", origin))
        }
        binding.editDestination.setOnClickListener {
            startActivity(Intent(context, KotaActivity::class.java).putExtra("type", destination))
        }
        binding.buttonCost.setOnClickListener {
            if (originId.isNullOrEmpty() || destinationId.isNullOrEmpty()){
                Toast.makeText(requireContext(), "Form tidak boleh kosong!", Toast.LENGTH_SHORT)
            }else{
                viewModel.fetchCost( origin = originId!!,
                        originType = "subdistrict",
                        destination = destinationId!!,
                        destinationType = "subdistrict",
                        weight = "1000",
                        courier = "sicepat:jnt:pos")
            }
        }
    }

    private fun setupObserver() {
        viewModel.preferences.observe(viewLifecycleOwner, Observer { listPreferences->
            listPreferences.forEach {
                when(it.type){
                    origin -> {
                        binding.editOrigin.setText( it.name )
                        originId = it.id
                    }
                    destination -> {
                        binding.editDestination.setText( it.name )
                        destinationId = it.id
                    }
                }
            }
        })

        viewModel.biayaResponse.observe(viewLifecycleOwner, Observer {
            when(it){
                is Resource.Loading -> setLoading(true)
                is Resource.Success -> {
                    setLoading(false)
                    Log.d("_biayaresponse", it.data!!.rajaongkir.results.toString() )
                    biayaAdapter.setData( it.data!!.rajaongkir.results )
                }
                is Resource.Error -> {
                    setLoading(false)
                    Log.d( _error, it.message.toString() )
                }
            }
        })
    }

    private fun setLoading(load: Boolean){
        if (load) binding.progressCost.visibility = View.VISIBLE
        else binding.progressCost.visibility = View.GONE
    }

    override fun onStart() {
        super.onStart()
        viewModel.getPreferences()
    }

}