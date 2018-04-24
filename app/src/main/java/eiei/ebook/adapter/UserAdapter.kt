package eiei.ebook

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import eiei.ebook.models.User
import android.widget.TextView
import eiei.ebook.R
import android.view.LayoutInflater
import android.widget.ImageView


class UserAdapter(val posts: ArrayList<User>) : RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder!!.name.text = posts.get(position).name
        holder!!.pic.setImageResource(R.drawable.user_dark)

    }

    override fun getItemCount(): Int {
        return this.posts.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent!!.context).inflate(R.layout.item_view, parent, false)
        return ViewHolder(view)
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.textview_title_item)
        val pic: ImageView = itemView.findViewById(R.id.imageview_item)
    }

}