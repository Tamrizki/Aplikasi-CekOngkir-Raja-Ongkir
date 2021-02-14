package tam.pa.cekongkir.ui.kota.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import tam.pa.cekongkir.databinding.CustomListKotaBinding
import tam.pa.cekongkir.network.response.KotaResponse
import timber.log.Timber

class KotaAdapter(
        val listKota: ArrayList<KotaResponse.KotaRajaOngkir.DataResult>,
        val listener: OnAdapterListener
        ): RecyclerView.Adapter<KotaAdapter.vHolder>(), Filterable {

    private var kotafilter = ArrayList<KotaResponse.KotaRajaOngkir.DataResult>()

    init {
        kotafilter = listKota
    }

    class vHolder(val binding: CustomListKotaBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)= vHolder(
            CustomListKotaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: vHolder, position: Int) {
        val kota = kotafilter[position]
        holder.binding.textName.text = kota.city_name
        holder.binding.container.setOnClickListener {
            listener.onClick( kota )
        }
    }

    override fun getItemCount() = kotafilter.size

    interface OnAdapterListener{
        fun onClick(results: KotaResponse.KotaRajaOngkir.DataResult)
    }

    fun setData(dataKota: List<KotaResponse.KotaRajaOngkir.DataResult>){
        listKota.clear()
        listKota.addAll( dataKota )
        notifyDataSetChanged()
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                Timber.d("charSearch: $charSearch")
                if (charSearch.isEmpty()) {
                    kotafilter = listKota
                } else {
                    val citiesFiltered = ArrayList<KotaResponse.KotaRajaOngkir.DataResult>()
                    for (city in listKota) {
                        if (city.city_name.toLowerCase().contains(charSearch.toLowerCase())) {
                            citiesFiltered.add(city)
                        }
                    }
                    kotafilter = citiesFiltered
                }
                val citiesFilteredResult = FilterResults()
                citiesFilteredResult.values = kotafilter
                return citiesFilteredResult
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                kotafilter = results?.values as ArrayList<KotaResponse.KotaRajaOngkir.DataResult>
                notifyDataSetChanged()
            }

        }
    }
}