package tam.pa.cekongkir.helper

import android.app.Activity
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

fun Activity.showToast(message: String){
    Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
}

fun Fragment.showToast(message: String){
    Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
}

fun View.viewShow(){
    visibility = View.VISIBLE
}

fun View.viewHid(){
    visibility = View.GONE
}

fun SwipeRefreshLayout.swipeShow(){
    isRefreshing = true
}

fun SwipeRefreshLayout.swipeHide(){
    isRefreshing = false
}