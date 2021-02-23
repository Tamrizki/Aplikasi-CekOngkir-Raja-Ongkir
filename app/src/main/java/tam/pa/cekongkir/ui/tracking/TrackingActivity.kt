package tam.pa.cekongkir.ui.tracking

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import tam.pa.cekongkir.databinding.ActivityTrackingBinding
import tam.pa.cekongkir.localDB.roomDb.CekOngkirDb
import tam.pa.cekongkir.localDB.sharedPref.CekOngkirSharedPref
import tam.pa.cekongkir.network.ApiService
import tam.pa.cekongkir.network.RajaOngkirRepo

class TrackingActivity : AppCompatActivity() {
    private val binding by lazy { ActivityTrackingBinding.inflate(layoutInflater) }

    private val api by lazy { ApiService.getClient() }
    private val preferences by lazy { CekOngkirSharedPref( applicationContext ) }
    private val db by lazy { CekOngkirDb(applicationContext) }
    private val repository by lazy {  RajaOngkirRepo(api, preferences, db) }
    private val cekresofactory by lazy { TrackingViewModelFactory( repository ) }
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
//        if (this.intent.getBooleanExtra("is_tracking", false))
            finish()
//        else super.onBackPressed()
    }

}