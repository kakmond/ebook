package eiei.ebook.models

import android.util.Log

/**
 * Created by Mond on 30/3/2561.
 */
class MockBookRepository : BookRepository() {

    override fun getBooks(): ArrayList<Book> {
        return bookList
    }

    override fun loadAllBooks() {
        allbooks.clear()
        allbooks.add(Book(1, "How to be a Programmer", 555.0))
        allbooks.add(Book(1, "How to be a Varit", 123.0))
        allbooks.add(Book(1, "How to be a Assavavisitchai", 999.0))
        bookList = allbooks
        setChanged()
        notifyObservers()

    }

}