package com.bochet.cyril.projetandroidcloud2020.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bochet.cyril.projetandroidcloud2020.R
import com.bochet.cyril.projetandroidcloud2020.model.Quote
import com.bochet.cyril.projetandroidcloud2020.view.adapter.QuoteAdapter
import com.bochet.cyril.projetandroidcloud2020.viewmodel.QuoteViewModel
import kotlinx.android.synthetic.main.activity_retrofit.*
import kotlin.collections.ArrayList

class RetrofitActivity : AppCompatActivity() {

    // We need variable of our adapter
    private lateinit var mAdapter: QuoteAdapter

    private lateinit var mViewModel: QuoteViewModel
    
    private var mObserverQuote = Observer<List<Quote>> {
        updateRecyclerView(ArrayList(it))

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_retrofit)
        mViewModel = ViewModelProvider(this)[QuoteViewModel::class.java]
        // Create the instance of adapter
        mAdapter = QuoteAdapter(this)
        // We define the style
        recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        // We set the adapter to recycler view
        recyclerView.adapter = mAdapter

        buttonAddItem.setOnClickListener { addRandomQuote() }
        buttonDeleteItem.setOnClickListener { deleteRandomQuote() }
    }

    override fun onStart() {
        super.onStart()
        mViewModel.mQuoteLiveData.observe(this, mObserverQuote)
    }

    override fun onStop() {
        mViewModel.mQuoteLiveData.removeObserver(mObserverQuote)
        super.onStop()
    }

    private fun updateRecyclerView(newList: ArrayList<Quote>) {
        mAdapter.rebuild(newList)
    }

    private fun addRandomQuote() {
        mViewModel.fetchNewQuote()
    }

    private fun deleteRandomQuote() {
        mViewModel.deleteAllQuotes()
    }
}
