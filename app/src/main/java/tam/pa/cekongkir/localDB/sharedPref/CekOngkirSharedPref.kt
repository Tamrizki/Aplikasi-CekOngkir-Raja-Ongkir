package tam.pa.cekongkir.localDB.sharedPref

import android.content.Context
import android.content.SharedPreferences

private const val PREF_NAME = "cekOngkir.pref"

class CekOngkirSharedPref(context: Context) {

    private var sharedPref: SharedPreferences =
        context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    private val editor: SharedPreferences.Editor

    init {
        editor = sharedPref.edit()
    }

    fun setData(key: String, value: String){
        editor.putString(key, value)
            .apply()
    }

    fun getString(key: String): String?{
        return sharedPref.getString(key, null)
    }

}