package tam.pa.cekongkir.ui.kota

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.core.widget.doAfterTextChanged
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
        setupView()
        setupListener()
        setupRecyclerView()
        setupObserver()
        binding.editSearch.setOnClickListener(this)
    }

    private fun setupListener() {
        binding.editSearch.doAfterTextChanged {
            kotaadapter.filter.filter( it.toString() )
        }
        binding.refreshCity.setOnRefreshListener {
            viewModel.fetchKota()
        }
    }

    private fun setupView() {
        viewModel.titleBar.postValue(getString(R.string.select_city))
    }

    private fun setupRecyclerView() {
        kotaadapter = KotaAdapter(arrayListOf(), object : KotaAdapter.OnAdapterListener{
            override fun onClick(results: KotaResponse.KotaRajaOngkir.DataResult) {
                viewModel.fetchDistrik( results.city_id )
                findNavController().navigate(
                        R.id.action_kotaFragment_to_distrikFragment,
                        bundleOf("city_id" to results.city_id, "city_name" to results.city_name)
                )
            }
        })
        binding.listCity.adapter = kotaadapter
    }

    override fun onClick(view: View?) {
        if (view == binding.editSearch){
        }
    }
    private fun setupObserver(){
        viewModel.kotaResponse.observe(viewLifecycleOwner, Observer {
            when(it){
                is Resource.Loading ->{
                    binding.refreshCity.isRefreshing = true
                    Log.d("cityRajaOngkir", "Loading!!!")
                }
                is Resource.Success ->{
                    binding.refreshCity.isRefreshing = false
                    Log.d("cityRajaOngkir", it.data!!.rajaongkir.toString())
                    kotaadapter.setData( it.data.rajaongkir.results )
                }
                is Resource.Error ->{
                    binding.refreshCity.isRefreshing = false
                    Log.d("cityRajaOngkir", "EROORRNYA "+it.message.toString())
                }
            }
        })
    }
}