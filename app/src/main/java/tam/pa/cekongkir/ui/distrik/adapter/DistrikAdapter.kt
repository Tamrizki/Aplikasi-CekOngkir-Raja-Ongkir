package tam.pa.cekongkir.ui.distrik.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import tam.pa.cekongkir.databinding.CustomListDistrikBinding
import tam.pa.cekongkir.network.response.DistrikResponse

class DistrikAdapter(
        val listDistrik: ArrayList<DistrikResponse.DistrikRajaOngkir.DataResult>,
        val listner: OnDistrikAdapterListener
        ): RecyclerView.Adapter<DistrikAdapter.vHolder>() {

    class vHolder(val binding: CustomListDistrikBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = vHolder(
            CustomListDistrikBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: vHolder, position: Int) {
        val distrik = listDistrik[position]
        holder.binding.textName.text = distrik.subdistrict_name
        holder.binding.container.setOnClickListener {
            listner.onClick( distrik )
        }
    }

    override fun getItemCount() = listDistrik.size

    interface OnDistrikAdapterListener{
        fun onClick(result: DistrikResponse.DistrikRajaOngkir.DataResult)
    }

    fun setData(dataDistrik : List<DistrikResponse.DistrikRajaOngkir.DataResult>){
        listDistrik.clear()
        listDistrik.addAll( dataDistrik )
        notifyDataSetChanged()
    }
}