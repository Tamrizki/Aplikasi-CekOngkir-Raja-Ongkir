package tam.pa.cekongkir.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.tabs.TabLayoutMediator
import tam.pa.cekongkir.databinding.ActivityHomeBinding
import tam.pa.cekongkir.localDB.roomDb.CekOngkirDb
import tam.pa.cekongkir.localDB.sharedPref.CekOngkirSharedPref
import tam.pa.cekongkir.network.ApiService
import tam.pa.cekongkir.network.RajaOngkirRepo
import tam.pa.cekongkir.ui.biayaOngkir.BiayaViewModel
import tam.pa.cekongkir.ui.biayaOngkir.BiayaViewModelFactory
import tam.pa.cekongkir.ui.tracking.TrackingViewModel
import tam.pa.cekongkir.ui.tracking.TrackingViewModelFactory

class HomeActivity : AppCompatActivity(){
    private val api by lazy { ApiService.getClient() }
    private val pref by lazy { CekOngkirSharedPref(applicationContext) }
    private val db by lazy { CekOngkirDb(applicationContext) }
    private val repo by lazy {  RajaOngkirRepo(api, pref, db) }
    private val biayaFactory by lazy { BiayaViewModelFactory(repo) }
    private val trackingFactory by lazy { TrackingViewModelFactory(repo) }
    private lateinit var biayaViewModel: BiayaViewModel

    private val binding by lazy { ActivityHomeBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupTab()
        setupViewModel()
    }

    private fun setupViewModel() {
        biayaViewModel = ViewModelProvider(this, biayaFactory).get(BiayaViewModel::class.java)
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