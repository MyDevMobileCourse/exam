package com.example.examptp

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.examtp.api.Movie

 public  class ListAdapter(private val context: Context, private var mUsers: MutableList<Movie>, private val mRowLayout: Int) : RecyclerView.Adapter<ListAdapter.UserViewHolder>()  {

     var onItemClick: ((Movie,Int) -> Unit)? = null

     override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(mRowLayout, parent, false)
        return UserViewHolder(view)
    }
    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = mUsers[position]
        holder.nomFilm.text = user.nomfilm
        holder.genreFilm.text = user.genre
        holder.durréeFilm.text = user.duree

        holder.userItem.setOnClickListener {
            onItemClick?.invoke(user,position)
        }
    }

    override fun getItemCount(): Int {
        return mUsers.size
    }

    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nomFilm = itemView.findViewById<TextView>(com.example.examtp.R.id.nomFilm)
        val genreFilm = itemView.findViewById<TextView>(com.example.examtp.R.id.genreFilm)
        val durréeFilm = itemView.findViewById<TextView>(com.example.examtp.R.id.durréeFilm)
        val userItem = itemView.findViewById<CardView>(com.example.examtp.R.id.user_item)

    }

}