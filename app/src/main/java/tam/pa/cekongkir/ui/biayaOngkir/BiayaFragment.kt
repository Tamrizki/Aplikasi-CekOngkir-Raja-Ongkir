package tam.pa.cekongkir.ui.biayaOngkir

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import tam.pa.cekongkir.databinding.FragmentBiayaBinding
import tam.pa.cekongkir.ui.kota.KotaActivity

class BiayaFragment : Fragment(), View.OnClickListener {
    private lateinit var binding: FragmentBiayaBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBiayaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.editOrigin.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        if (view == binding.editOrigin){
            startActivity(Intent(context, KotaActivity::class.java))
        }
    }
}