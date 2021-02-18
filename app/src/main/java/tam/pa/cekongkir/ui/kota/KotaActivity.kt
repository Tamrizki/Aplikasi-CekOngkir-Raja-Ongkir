package tam.pa.cekongkir.ui.kota

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import tam.pa.cekongkir.R
import tam.pa.cekongkir.localDB.sharedPref.CekOngkirSharedPref
import tam.pa.cekongkir.network.ApiService
import tam.pa.cekongkir.network.RajaOngkirRepo

class KotaActivity : AppCompatActivity(){
    private val api by lazy { ApiService.getClient() }
    private val pref by lazy { CekOngkirSharedPref(this) }
    private val repository by lazy { RajaOngkirRepo(api, pref) }
    private val viewModelFactory by lazy { KotaViewModelFactory(repository) }
    private lateinit var viewModel: KotaViewModel

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
        viewModel = ViewModelProvider(this, viewModelFactory).get(KotaViewModel::class.java)
    }

    private fun setupObserver(){
        viewModel.titleBar.observe(this, Observer {
            supportActionBar?.title = it
        })
    }
}