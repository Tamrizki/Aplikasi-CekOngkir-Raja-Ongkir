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
import timber.log.Timber

class CekOngkirApplication: Application(), KodeinAware {

    override val kodein: Kodein = Kodein.lazy {
        import(androidXModule(this@CekOngkirApplication))
        bind() from singleton { CekOngkirSharedPref( instance() ) }
        bind<EndPoint>() with singleton { ApiService.getClient() }
        bind() from singleton { RajaOngkirRepo( instance(), instance() , instance()) }

        bind() from singleton { KotaViewModelFactory( instance() ) }
        bind() from singleton { BiayaViewModelFactory( instance() ) }
    }

    override fun onCreate() {
        super.onCreate()
        Timber.plant( Timber.DebugTree() )
    }

}