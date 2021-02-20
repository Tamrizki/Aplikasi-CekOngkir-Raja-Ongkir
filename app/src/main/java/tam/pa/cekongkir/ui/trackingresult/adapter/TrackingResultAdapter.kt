package tam.pa.cekongkir.ui.trackingresult.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import tam.pa.cekongkir.databinding.CustomListTrackingBinding
import tam.pa.cekongkir.network.response.TrackingResponse

class TrackingResultAdapter(
        val listTrack: List<TrackingResponse.dataRajaOngkir.dataresult.datamanifest>
        ): RecyclerView.Adapter<TrackingResultAdapter.vHolder>() {

    class vHolder(val binding: CustomListTrackingBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = vHolder(
            CustomListTrackingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: vHolder, position: Int) {
        val tracking = listTrack[position]
        holder.binding.textDate.setText( tracking.manifest_date+" "+tracking.manifest_time )
        holder.binding.textDescription.setText( tracking.manifest_description )
    }

    override fun getItemCount() = listTrack.size
}