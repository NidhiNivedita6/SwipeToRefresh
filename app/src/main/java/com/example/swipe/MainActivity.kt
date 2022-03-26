package com.example.swipe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener

class MainActivity : AppCompatActivity() {

    var num = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView : RecyclerView = findViewById(R.id.recyclerView)

        // set layout of out Recycler view as linear
        recyclerView.layoutManager = LinearLayoutManager(this)

        val items = fetchData()
        //set adoptor
        val adaptor = RVAdaptor(items)
        recyclerView.adapter = adaptor

        // swipe to refresh
        val swipe : SwipeRefreshLayout = findViewById(R.id.swipeRefreshLayout)
        swipe.setOnRefreshListener {
            // updating data
            items.addAll(updateData())

            // notify adaptor about data set change
            adaptor.notifyDataSetChanged()

            // stop refreshing when task is completed
            swipe.isRefreshing = false
        }
    }

    // we will fetch data using fetch data
    fun fetchData() : ArrayList<String>{
        val list = ArrayList<String>()
        for(i in 0 until 20){
            list.add("Item $i")
        }
        return list
    }
    // we will update data till 30

    fun updateData() : ArrayList<String>{
        val item = ArrayList<String>()
        for(i in 20 until 30){
            item.add("Item $i")
            val toast = Toast.makeText(applicationContext, "Scroll down", Toast.LENGTH_SHORT)
            toast.show()
        }
        return item
    }
}