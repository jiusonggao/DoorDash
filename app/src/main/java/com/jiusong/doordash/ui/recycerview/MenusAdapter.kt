package com.jiusong.doordash.ui.recycerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jiusong.doordash.R
import com.jiusong.doordash.data.model.Item
import com.squareup.picasso.Picasso

/**
 * Created by jiusong.gao on 1/16/21.
 */
class MenusAdapter(private val data: List<Item>): RecyclerView.Adapter<MenusAdapter.MenuViewHolder>() {

    class MenuViewHolder(v: View): RecyclerView.ViewHolder(v) {
        private val image: ImageView = v.findViewById(R.id.menu_img)
        private val name: TextView = v.findViewById(R.id.menu_name)
        private val price: TextView = v.findViewById(R.id.menu_price)

        fun bind(item: Item) {
            Picasso.get().load(item.img_url).into(image)
            name.text = item.name
            price.text = getPriceText(item.price)
        }

        private fun getPriceText(price: String): String {
            return "$" + price.subSequence(0, price.length -2) + "." + price.substring(price.length -2)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.menu_item, parent, false)
        return MenuViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }


    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        holder.bind(data[position])
    }
}