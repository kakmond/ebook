package eiei.ebook

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import eiei.ebook.models.Book
import eiei.ebook.presenter.BookView

class UserActivity : AppCompatActivity(), BookView {
    override fun setBookList(books: ArrayList<Book>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)
    }
}
