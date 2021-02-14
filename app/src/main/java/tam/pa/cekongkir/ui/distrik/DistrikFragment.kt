package tam.pa.cekongkir.ui.distrik

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import tam.pa.cekongkir.R
import tam.pa.cekongkir.databinding.FragmentDistrikBinding
import tam.pa.cekongkir.databinding.FragmentKotaBinding
import tam.pa.cekongkir.ui.kota.KotaViewModel


class DistrikFragment : Fragment() {

    private val viewModel by lazy { ViewModelProvider(requireActivity()).get(KotaViewModel::class.java) }
    private lateinit var binding: FragmentDistrikBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDistrikBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.titleBar.postValue(getString(R.string.select_districk))
    }
}