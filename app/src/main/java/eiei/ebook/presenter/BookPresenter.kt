package eiei.ebook.presenter

import android.util.Log
import eiei.ebook.models.BookRepository
import java.util.*
import kotlin.collections.ArrayList

/**
 * Created by Mond on 30/3/2561.
 */
class BookPresenter(val view: BookView,
                    val repository: BookRepository) : Observer {

    override fun update(p0: Observable?, p1: Any?) {
        if (p0 == repository) {
//            val bookList_string = ArrayList<String>()
//            for (book in repository.getBooks())
//                bookList_string.add(book.toString())
//            Log.d("TEST",bookList_string.toString());
//            view.setBookList(bookList_string)
            view.setBookList(repository.getBooks())
        }
    }

    fun start() {
        repository.addObserver(this)
        repository.loadAllBooks()
    }
}