package tam.pa.cekongkir.ui.kota

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import tam.pa.cekongkir.network.ApiService
import tam.pa.cekongkir.network.EndPoint
import tam.pa.cekongkir.network.RajaOngkirRepo

class KotaViewModelFactory (
        private val repo: RajaOngkirRepo
        ): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return KotaViewModel(repo) as T
    }
}