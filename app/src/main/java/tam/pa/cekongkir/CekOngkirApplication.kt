package tam.pa.cekongkir

import android.app.Application
import android.util.Log
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton
import tam.pa.cekongkir.localDB.sharedPref.CekOngkirSharedPref
import tam.pa.cekongkir.network.ApiService
import tam.pa.cekongkir.network.EndPoint
import tam.pa.cekongkir.network.RajaOngkirRepo
import tam.pa.cekongkir.ui.biayaOngkir.BiayaViewModelFactory
import tam.pa.cekongkir.ui.kota.KotaViewModelFactory

class CekOngkirApplication: Application(), KodeinAware {

    override val kodein: Kodein = Kodein.lazy {
        import(androidXModule(this@CekOngkirApplication))
//        init sharedpref, instance() sebagai Context
        bind() from singleton { CekOngkirSharedPref( instance() ) }
//        Contoh init untuk interface
        bind<EndPoint>() with singleton { ApiService.getClient() }

        // param RajaOngkirRepo sharedPref dan Api sudah di init diatas
        // sehingga bisa langsung dengan instance()
        bind() from singleton { RajaOngkirRepo( instance(), instance() ) }

        bind() from singleton { KotaViewModelFactory( instance() ) }
        bind() from singleton { BiayaViewModelFactory( instance() ) }
    }

    override fun onCreate() {
        super.onCreate()
    }

}