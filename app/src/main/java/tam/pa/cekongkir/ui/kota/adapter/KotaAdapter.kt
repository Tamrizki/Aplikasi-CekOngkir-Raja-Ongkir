package tam.pa.cekongkir.ui.kota.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import tam.pa.cekongkir.databinding.CustomListKotaBinding
import tam.pa.cekongkir.network.response.KotaResponse

class KotaAdapter(
        val listKota: ArrayList<KotaResponse.KotaRajaOngkir.DataResult>,
        val listener: OnAdapterListener
        ): RecyclerView.Adapter<KotaAdapter.vHolder>() {

    class vHolder(val binding: CustomListKotaBinding): RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)= vHolder(
            CustomListKotaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: vHolder, position: Int) {
        val kota = listKota[position]
        holder.binding.textName.text = kota.city_name
        holder.binding.container.setOnClickListener {
            listener.onClick( kota )
        }
    }

    override fun getItemCount() = listKota.size

    interface OnAdapterListener{
        fun onClick(results: KotaResponse.KotaRajaOngkir.DataResult)
    }

    fun setData(dataKota: List<KotaResponse.KotaRajaOngkir.DataResult>){
        listKota.clear()
        listKota.addAll( dataKota )
        notifyDataSetChanged()
    }
}