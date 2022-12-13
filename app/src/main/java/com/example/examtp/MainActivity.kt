package com.example.examtp

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.examptp.ListAdapter
import com.example.examtp.api.Movie
import com.example.examtp.api.RestApiService

class MainActivity : AppCompatActivity() {
    lateinit var reView: RecyclerView
    lateinit var fav : Button
    lateinit var showFav : Button
    lateinit var mAdapter: ListAdapter
    lateinit var empty : CardView
    var mUsers: MutableList<Movie> = ArrayList<Movie>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init();
        fetchMovies();
    }

    fun init(){
        reView = findViewById(R.id.recyclerview)
        fav = findViewById(R.id.fav)
        showFav = findViewById(R.id.showFav)
        empty = findViewById(R.id.empty)
        initRecyclerView()
    }

    fun initRecyclerView() {
        reView = findViewById(R.id.recyclerview)
        reView.layoutManager = LinearLayoutManager(this)
        mAdapter = ListAdapter(this, mUsers, R.layout.item)

        mAdapter.onItemClick = { movie, position ->
            println("clicked")
            println(movie.nomfilm)
            // get all other nomfilm and set color to blac
            for (i in 0 until mUsers.size) {
                if (i != position) {
                    val viewHolder = reView.findViewHolderForAdapterPosition(i) as ListAdapter.UserViewHolder
                    viewHolder.nomFilm.setTextColor(Color.BLACK)
                } else{
                    val viewHolder = reView.findViewHolderForAdapterPosition(i) as ListAdapter.UserViewHolder
                    viewHolder.nomFilm.setTextColor(Color.RED)
                }
            }
        }
        reView.adapter = mAdapter
        reView.visibility = View.INVISIBLE
        empty.visibility = View.INVISIBLE


    }



    fun fetchMovies(){
        val apiService = RestApiService()
        val call = apiService.getMovies { res ->
            run {
                if (res?.isEmpty() == true) {
                    mUsers = ArrayList()
                    reView.visibility = View.INVISIBLE
                    empty.visibility = View.VISIBLE
                    mAdapter.notifyDataSetChanged()
                } else {
                    mUsers.addAll(res!!)
                    reView.visibility = View.VISIBLE
                    empty.visibility = View.INVISIBLE
                    mAdapter.notifyDataSetChanged()
                }
            }
        }
    }
}