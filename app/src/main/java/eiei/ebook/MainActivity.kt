package eiei.ebook

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import eiei.ebook.models.Book
import eiei.ebook.models.MockBookRepository
import eiei.ebook.presenter.BookPresenter
import eiei.ebook.presenter.BookView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), BookView {

    lateinit var presenter: BookPresenter
    lateinit var mock: MockBookRepository;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mock = MockBookRepository()
        presenter = BookPresenter(this, mock)
        presenter.start()
    }

    override fun setBookList(books: ArrayList<Book>) {
//        var adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, books);
//        book_list.setAdapter(adapter);
        var adapter = ArrayAdapter<Book>(this, android.R.layout.simple_list_item_1, books);
        book_list.setAdapter(adapter);
    }

}
