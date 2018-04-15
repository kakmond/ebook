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
        bookList.clear()
        bookList.add(Book(1,"How to be a Programmer",555.0))
        bookList.add(Book(1,"How to be a Varit",123.0))
        bookList.add(Book(1,"How to be a Assavavisitchai",999.0))
        setChanged()
        notifyObservers()
    }

}