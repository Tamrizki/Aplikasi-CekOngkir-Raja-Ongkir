package tam.pa.cekongkir.ui.kota

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import tam.pa.cekongkir.R
import tam.pa.cekongkir.databinding.FragmentKotaBinding
import tam.pa.cekongkir.network.Resource
import tam.pa.cekongkir.network.response.KotaResponse
import tam.pa.cekongkir.ui.kota.adapter.KotaAdapter

class KotaFragment : Fragment(), View.OnClickListener {

    private val viewModel by lazy { ViewModelProvider(requireActivity()).get(KotaViewModel::class.java) }
    private lateinit var binding: FragmentKotaBinding
    private lateinit var kotaadapter: KotaAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentKotaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupObserver()
        viewModel.titleBar.postValue(getString(R.string.select_city))
        binding.editSearch.setOnClickListener(this)
    }

    private fun setupRecyclerView() {
        kotaadapter = KotaAdapter(arrayListOf(), object : KotaAdapter.OnAdapterListener{
            override fun onClick(results: KotaResponse.KotaRajaOngkir.DataResult) {
                Toast.makeText(context, results.city_name, Toast.LENGTH_SHORT).show()
            }
        })
        binding.listCity.adapter = kotaadapter
    }

    override fun onClick(view: View?) {
        if (view == binding.editSearch){
            findNavController().navigate(R.id.action_kotaFragment_to_distrikFragment)
        }
    }
    private fun setupObserver(){
        viewModel.kotaResponse.observe(viewLifecycleOwner, Observer {
            when(it){
                is Resource.Loading ->{
                    Log.d("cityRajaOngkir", "Loading!!!")
                }
                is Resource.Success ->{
                    Log.d("cityRajaOngkir", it.data!!.rajaongkir.toString())
                    kotaadapter.setData( it.data.rajaongkir.results )
                }
                is Resource.Error ->{
                    Log.d("cityRajaOngkir", "EROORRNYA "+it.message.toString())
                }
            }
        })
    }
}