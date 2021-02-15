package tam.pa.cekongkir.ui.kota

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import tam.pa.cekongkir.R
import tam.pa.cekongkir.localDB.sharedPref.CekOngkirSharedPref
import tam.pa.cekongkir.network.ApiService
import tam.pa.cekongkir.network.RajaOngkirRepo
import tam.pa.cekongkir.network.Resource
import timber.log.Timber

class KotaActivity : AppCompatActivity() {

    private val api by lazy { ApiService.getClient() }
    private val pref by lazy { CekOngkirSharedPref(this) }
    private lateinit var viewModelFactory: KotaViewModelFactory
    private lateinit var viewModel: KotaViewModel
    private lateinit var repository: RajaOngkirRepo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kota)
        setupViewModel()
        setupObserver()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    private fun setupViewModel(){
        repository = RajaOngkirRepo( api, pref )
        viewModelFactory = KotaViewModelFactory( repository )
        viewModel = ViewModelProvider(this, viewModelFactory).get(KotaViewModel::class.java)
    }

    private fun setupObserver(){
        viewModel.titleBar.observe(this, Observer {
            supportActionBar?.title = it
        })
    }
}