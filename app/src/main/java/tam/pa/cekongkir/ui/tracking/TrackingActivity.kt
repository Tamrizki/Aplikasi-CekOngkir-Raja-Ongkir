package tam.pa.cekongkir.ui.tracking

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein
import org.kodein.di.generic.instance
import tam.pa.cekongkir.databinding.ActivityTrackingBinding
import tam.pa.cekongkir.localDB.roomDb.CekOngkirDb
import tam.pa.cekongkir.localDB.sharedPref.CekOngkirSharedPref
import tam.pa.cekongkir.network.ApiService
import tam.pa.cekongkir.network.RajaOngkirRepo

class TrackingActivity : AppCompatActivity(), KodeinAware {
    override val kodein by closestKodein()
    private val binding by lazy { ActivityTrackingBinding.inflate(layoutInflater) }

    private val cekresofactory: TrackingViewModelFactory by instance()
    private lateinit var viewmodel: TrackingViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupViewModel()
    }

    private fun setupViewModel() {
        viewmodel = ViewModelProvider( this, cekresofactory).get(TrackingViewModel::class.java)
    }

    override fun onBackPressed() {
            finish()
    }
}