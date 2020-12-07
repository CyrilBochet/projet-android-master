package com.bochet.cyril.projetandroidcloud2020.view.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bochet.cyril.projetandroidcloud2020.R
import com.bochet.cyril.projetandroidcloud2020.model.Quote
import com.bochet.cyril.projetandroidcloud2020.view.FullQuoteActivity

class QuoteAdapter(private val context: Context) :
    RecyclerView.Adapter<QuoteAdapter.FactViewHolder>() {

    /**
     * Attribute
     */

    // Create list of data we want to display in list as var of class
    private val mQuote = ArrayList<Quote>()

    // Needed to get the item_layout
    private val mInflater: LayoutInflater = LayoutInflater.from(context)

    /**
     * Manage lifecycle
     */

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FactViewHolder {
        // This line perform the matching with our ViewHolder and the item from layout
        return FactViewHolder(mInflater.inflate(R.layout.item_custom_retrofit, parent, false))
    }

    override fun getItemCount(): Int = mQuote.size

    override fun onBindViewHolder(holder: FactViewHolder, position: Int) {
        // onBindViewHolder is called for each item we want to display so we need to get each object
        val currentItem = mQuote[position];
        val create_at = currentItem.appeared_at.substring(0, 10);

        var quote = currentItem.quote
        if (quote.length > 150) {
            quote = quote.substring(0, 150) + "...";
        }

        if (currentItem.id.toInt() % 2 == 0) {
            holder.itemRecyclerViewImage.setImageResource(R.drawable.ic_tronald_dump);
        }else{
            holder.itemRecyclerViewImage.setImageResource(R.drawable.ic_tronald_dump_reverse);
        }
        holder.itemRecyclerViewQuote.text = quote
        holder.itemRecyclerViewCreateDate.text = create_at;
        holder.itemView.setOnClickListener{this.clickAction(currentItem)}

    }

    /**
     * Public method (Called from activity)
     */

    fun rebuild(quote: ArrayList<Quote>) {
        // This is the simplest way to update the list
        mQuote.clear()
        mQuote.addAll(quote)
        // Needed to said to recycler view we have new data
        this.notifyDataSetChanged()
    }

    private fun clickAction(quote: Quote){
        val fullQuoteIntent = Intent(context, FullQuoteActivity::class.java)
        fullQuoteIntent.putExtra("full_quote", quote.quote)
        context.startActivity(fullQuoteIntent);
    }

    /**
     * Internal class
     */

    inner class FactViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemRecyclerViewQuote: TextView = itemView.findViewById(R.id.itemRecyclerViewLocalisation)
        val itemRecyclerViewCreateDate: TextView =
            itemView.findViewById(R.id.itemRecyclerViewDate)
        val itemRecyclerViewImage: ImageView = itemView.findViewById(R.id.itemRecyclerViewImage)
    }
}
