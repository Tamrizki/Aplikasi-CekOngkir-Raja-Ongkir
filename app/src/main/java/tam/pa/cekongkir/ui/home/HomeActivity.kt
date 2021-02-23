package tam.pa.cekongkir.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.tabs.TabLayoutMediator
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein
import org.kodein.di.generic.instance
import tam.pa.cekongkir.databinding.ActivityHomeBinding
import tam.pa.cekongkir.localDB.roomDb.CekOngkirDb
import tam.pa.cekongkir.localDB.sharedPref.CekOngkirSharedPref
import tam.pa.cekongkir.network.ApiService
import tam.pa.cekongkir.network.RajaOngkirRepo
import tam.pa.cekongkir.ui.biayaOngkir.BiayaViewModel
import tam.pa.cekongkir.ui.biayaOngkir.BiayaViewModelFactory
import tam.pa.cekongkir.ui.tracking.TrackingViewModel
import tam.pa.cekongkir.ui.tracking.TrackingViewModelFactory

class HomeActivity : AppCompatActivity(), KodeinAware{
    override val kodein by closestKodein()

    private val binding by lazy { ActivityHomeBinding.inflate(layoutInflater) }
    private val biayaFactory: BiayaViewModelFactory by instance()
    private val trackingFactory: TrackingViewModelFactory by instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupTab()
        setupViewModel()
    }

    private fun setupViewModel() {
        ViewModelProvider(this, biayaFactory).get(BiayaViewModel::class.java)
        ViewModelProvider(this, trackingFactory).get(TrackingViewModel::class.java)
    }

    private fun setupTab(){
        val tabTitle = arrayOf("Cek Ongkir", "Cek Resi")
        val tabAdapter = HomeTabAdapter(supportFragmentManager, lifecycle)
        binding.viewPager.adapter = tabAdapter

        TabLayoutMediator(binding.tabLayout, binding.viewPager){tab, position ->
            tab.text = tabTitle[position]
        }.attach()
    }

}