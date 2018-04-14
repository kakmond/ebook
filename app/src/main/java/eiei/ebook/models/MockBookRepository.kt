package eiei.ebook.models

import android.util.Log

/**
 * Created by Mond on 30/3/2561.
 */
class MockBookRepository : BookRepository() {

    val bookList = ArrayList<Book>()

    override fun getBooks(): ArrayList<Book> {
        return bookList
    }

    override fun loadAllBooks() {
        bookList.clear()
        bookList.add(Book(1,"How to be a programmer",555.0))
        bookList.add(Book(1,"How to be a Varit",123.0))
        bookList.add(Book(1,"How to be a Assavavisitchai",999.0))
        Log.d("FUCK","ADDED")
        setChanged()
        notifyObservers()
    }

}