package com.bochet.cyril.projetandroidcloud2020.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bochet.cyril.projetandroidcloud2020.R
import com.bochet.cyril.projetandroidcloud2020.model.ObjectDataSample

class DataAdapter(private val context: Context) :
    RecyclerView.Adapter<DataAdapter.FactViewHolder>() {

    /**
     * Attribute
     */

    // Create list of data we want to display in list as var of class
    private val mData = ArrayList<ObjectDataSample>()

    // Needed to get the item_layout
    private val mInflater: LayoutInflater = LayoutInflater.from(context)

    /**
     * Manage lifecycle
     */

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FactViewHolder {
        // This line perform the matching with our ViewHolder and the item from layout
        return FactViewHolder(mInflater.inflate(R.layout.item_custom_recycler, parent, false))
    }

    override fun getItemCount(): Int = mData.size

    override fun onBindViewHolder(holder: FactViewHolder, position: Int) {
        // onBindViewHolder is called for each item we want to display so we need to get each object
        val currentItem = mData[position];
        val longitude = currentItem.longitude
        val latitude = currentItem.latitude
        val date = currentItem.date

        holder.itemRecyclerViewLocalisation.text = "Localisation : $latitude $longitude"
        holder.itemRecyclerViewDate.text = date

        if (currentItem.id.toInt() % 2 == 0) {
            holder.itemRecyclerViewImage.setImageResource(R.drawable.ic_beenhere);
        } else {
            holder.itemRecyclerViewImage.setImageResource(R.drawable.ic_beenhere_2);
        }

    }

    /**
     * Public method (Called from activity)
     */

    fun rebuild(data: ArrayList<ObjectDataSample>) {
        // This is the simplest way to update the list
        mData.clear()
        mData.addAll(data)
        // Needed to said to recycler view we have new data
        this.notifyDataSetChanged()
    }

    /**
     * Internal class
     */

    inner class FactViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemRecyclerViewLocalisation: TextView =
            itemView.findViewById(R.id.itemRecyclerViewLocalisation)
        val itemRecyclerViewDate: TextView =
            itemView.findViewById(R.id.itemRecyclerViewDate)
        val itemRecyclerViewImage: ImageView = itemView.findViewById(R.id.itemRecyclerViewImage)
    }
}
