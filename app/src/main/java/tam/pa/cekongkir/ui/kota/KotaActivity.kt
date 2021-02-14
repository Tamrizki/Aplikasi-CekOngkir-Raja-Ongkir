package tam.pa.cekongkir.ui.kota

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import tam.pa.cekongkir.R
import tam.pa.cekongkir.network.ApiService
import tam.pa.cekongkir.network.Resource
import timber.log.Timber

class KotaActivity : AppCompatActivity() {

    private val api by lazy { ApiService.getClient() }
    private lateinit var viewModelFactory: KotaViewModelFactory
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
        viewModelFactory = KotaViewModelFactory(api)
        viewModel = ViewModelProvider(this, viewModelFactory).get(KotaViewModel::class.java)
    }

    private fun setupObserver(){
        viewModel.titleBar.observe(this, Observer {
            supportActionBar?.title = it
        })
    }
}