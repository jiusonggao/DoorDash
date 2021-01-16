package com.jiusong.doordash.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jiusong.doordash.R
import com.jiusong.doordash.data.model.Status
import com.jiusong.doordash.data.model.Store
import com.squareup.picasso.Picasso

/**
 * Created by jiusong.gao on 1/15/21.
 */
class StoreListAdapter: RecyclerView.Adapter<StoreListAdapter.StoreViewHolder>() {

    private val data = mutableListOf<Store>()

    class StoreViewHolder(v: View): RecyclerView.ViewHolder(v) {
        private val image: ImageView = v.findViewById(R.id.imageView)
        private val name: TextView = v.findViewById(R.id.name)
        private val description : TextView = v.findViewById(R.id.description)
        private val status : TextView = v.findViewById(R.id.status)

        fun bind(store: Store) {
            Picasso.get().load(store.cover_img_url).into(image)
            name.text = store.name
            description.text = store.description
            status.text = getStatus(store.status)
        }

        private fun getStatus(status: Status): String {
            var statusText = ""
            if (!status.unavailable_reason.isNullOrEmpty()) {
                statusText = status.unavailable_reason
            } else if (!status.asap_minutes_range.isNullOrEmpty()){
                statusText = status.asap_minutes_range[0].toString() + " Mins"
            }
            return statusText
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoreViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.store_item, parent, false)
        return StoreViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: StoreViewHolder, position: Int) {
        holder.bind(data[position])
    }

    fun addStores(stores: List<Store>) {
        data.addAll(stores)
        notifyDataSetChanged()
    }
}