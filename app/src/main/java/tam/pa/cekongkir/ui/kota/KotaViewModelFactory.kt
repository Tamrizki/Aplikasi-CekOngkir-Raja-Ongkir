package tam.pa.cekongkir.ui.kota

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import tam.pa.cekongkir.network.ApiService
import tam.pa.cekongkir.network.EndPoint

class KotaViewModelFactory (
        private val api: EndPoint
        ): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return KotaViewModel(api) as T
    }

}