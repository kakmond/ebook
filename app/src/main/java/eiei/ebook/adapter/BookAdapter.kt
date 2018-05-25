package eiei.ebook.adapter

import android.app.Activity
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import eiei.ebook.ClickHandler
import eiei.ebook.R
import eiei.ebook.models.Book
import eiei.ebook.models.User

class BookAdapter(val posts: ArrayList<Book>, val user: User, var dealClickHandler: ClickHandler) : RecyclerView.Adapter<BookAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder!!.title.text = posts.get(position).title
        holder!!.publicationYear.text = posts.get(position).publicationYear.toString()
        holder!!.price.text = posts.get(position).price.toString() + " ฿"

    }

    override fun getItemCount(): Int {
        return this.posts.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent!!.context).inflate(R.layout.book_layout, parent, false)
        return ViewHolder(view)
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.title)
        val publicationYear: TextView = itemView.findViewById(R.id.publicationYear)
        val price: TextView = itemView.findViewById(R.id.txtPrice)
        val dealButton: Button = itemView.findViewById(R.id.dealButton)

        init {
            dealButton.setOnClickListener { view -> dealClickHandler!!.onClick(posts.get(adapterPosition)) }
        }

    }
}