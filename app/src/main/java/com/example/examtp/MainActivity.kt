package com.example.examtp

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.examptp.ListAdapter
import com.example.examtp.api.Movie
import com.example.examtp.api.RestApiService
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class MainActivity : AppCompatActivity() {
    lateinit var reView: RecyclerView
    lateinit var fav : Button
    lateinit var showFav : Button
    lateinit var mAdapter: ListAdapter
    lateinit var empty : CardView
    var mUsers: MutableList<Movie> = ArrayList<Movie>()
    lateinit var sharedPref:SharedPref
    var myFavMovie : HashMap<String,String> = HashMap<String,String>()

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
        sharedPref = SharedPref(this)
        initRecyclerView()
        initButtons()
    }

    fun initButtons(){
        fav.isEnabled = false
        fav.isClickable = false

        fav.setOnClickListener {
            // loop through myFavMovie
            sharedPref.clearSharedPreference()
            for ((k,v) in myFavMovie){
                sharedPref.save(k,v)
            }
            Toast.makeText(this,"Favoris sauvegardés",Toast.LENGTH_SHORT).show()
        }

        if (sharedPref.sharedPref.contains("nomfilm")){
            showFav.isEnabled = true
            showFav.isClickable = true
        }
            showFav.setOnClickListener {
                println("showFav")
                println(sharedPref.getValueString("nomfilm"))
                println(sharedPref.getValueString("genre"))
                println(sharedPref.getValueString("duree"))
                println(sharedPref.getValueString("date"))
            }
    }

    fun initRecyclerView() {
        reView = findViewById(R.id.recyclerview)
        reView.layoutManager = LinearLayoutManager(this)
        mAdapter = ListAdapter(this, mUsers, R.layout.item)
        mAdapter.onItemClick = { movie, position ->
            println("clicked")
            println(movie.nomfilm)
            myFavMovie.put("nomfilm",movie.nomfilm)
            myFavMovie.put("genre",movie.genre)
            myFavMovie.put("duree",movie.duree)
            myFavMovie.put("date",Date().toString())
            fav.isEnabled = true
            fav.isClickable = true
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