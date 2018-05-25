package eiei.ebook

import android.app.Activity
import android.app.Dialog
import android.content.DialogInterface
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.support.v7.app.ActionBar
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import eiei.ebook.adapter.BookAdapter
import eiei.ebook.models.Book
import eiei.ebook.models.RealBookRepository
import eiei.ebook.models.User
import eiei.ebook.presenter.BookPresenter
import eiei.ebook.presenter.BookView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.menu_layout.*
import android.widget.TextView
import android.R.attr.data
import android.widget.Toast


class MainActivity : AppCompatActivity(), BookView {

    lateinit var presenter: BookPresenter
    lateinit var real: RealBookRepository

    private var isStarted: Boolean = false

    private lateinit var clickedHandler: ClickHandler

    private val INPUT_REQUEST_CODE = 100

    private var cart: ArrayList<Book> = ArrayList()

    var user: User = User("Mond")
    // lateinit var mock: MockBookRepository;

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == INPUT_REQUEST_CODE)
            if (resultCode == Activity.RESULT_OK)
                if (data != null) {
                    this.cart = data.getParcelableArrayListExtra("Cart")
                    this.user = data.getParcelableExtra("User")
                }
        balance.text = user.getBalance().toString()
        isStarted = false
    }


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getSupportActionBar()?.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar()?.setCustomView(R.layout.menu_layout);
        balance.text = user.getBalance().toString()
        name.text = user.name
        this.initialSpinner()
        // mock = MockBookRepository()
        real = RealBookRepository()
        presenter = BookPresenter(this, real)
        clickedHandler = ClickedHandler()

        name.setOnClickListener(View.OnClickListener {
            userButtonClicked(it)
        })
        balance.setOnClickListener(View.OnClickListener {
            userButtonClicked(it)
        })

        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        book_list.layoutManager = layoutManager
        presenter.start()
    }

    fun initialSpinner() {

        title_search.addTextChangedListener(object : TextWatcher {

            var title: String = ""

            override fun afterTextChanged(p0: Editable?) {
                presenter.search(title, year_search.text.toString())
                sortHandle()
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                // do nothing.
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                title = title_search.text.toString()
            }
        })

        year_search.addTextChangedListener(object : TextWatcher {

            var years: String = ""

            override fun afterTextChanged(p0: Editable?) {
                presenter.search(title_search.text.toString(), years)
                sortHandle()
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                // do nothing.
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                years = year_search.text.toString()
            }
        })

        val items_sortby = arrayOf("Unsorted", "Title", "Years")
        val adapter_sortby = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, items_sortby)
        sort_spinner.adapter = adapter_sortby

        sort_spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (position == 0) {
                    presenter.search(title_search.text.toString(), year_search.text.toString())
                    orderby_spinner.isEnabled = false
                } else {
                    orderby_spinner.isEnabled = true
                    sortHandle()
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                //do nothing
            }
        }

        val items_orderby = arrayOf("Ascending", "Descending")
        val adapter_orderby = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, items_orderby)
        orderby_spinner.adapter = adapter_orderby

        orderby_spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                sortHandle()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                //do nothing
            }
        }

    }

    fun sortHandle() {
        if (sort_spinner.selectedItem.toString().equals("Title")
                && orderby_spinner.selectedItem.toString().equals("Ascending"))
            presenter.sortTitle_asc()
        if (sort_spinner.selectedItem.toString().equals("Title")
                && orderby_spinner.selectedItem.toString().equals("Descending"))
            presenter.sortTitle_des()
        if (sort_spinner.selectedItem.toString().equals("Years")
                && orderby_spinner.selectedItem.toString().equals("Ascending"))
            presenter.sortYear_asc()
        if (sort_spinner.selectedItem.toString().equals("Years")
                && orderby_spinner.selectedItem.toString().equals("Descending"))
            presenter.sortYear_des()
    }

    fun userButtonClicked(view: View) {
        if (isStarted == false) {
            val intent = Intent(this, UserActivity::class.java)
            intent.putExtra("User", user)
            intent.putParcelableArrayListExtra("Cart", cart)
            startActivityForResult(intent, INPUT_REQUEST_CODE)
        }
        isStarted = true
    }


    override fun setBookList(books: ArrayList<Book>) {
        var adapter = BookAdapter(books, user, clickedHandler)
        book_list.adapter = adapter
    }


    inner class ClickedHandler : ClickHandler {

        val alertDialog = AlertDialog.Builder(
                this@MainActivity).create()

        init {
            alertDialog.setTitle("Are you sure?");
        }

        override fun onClick(post: Book) {
            alertDialog.setButton(Dialog.BUTTON_POSITIVE, "ADD", DialogInterface.OnClickListener
            { dialog, which ->
                cart.add(post)
                Toast.makeText(this@MainActivity, "Added to your cart.", Toast.LENGTH_LONG).show()
            })
            alertDialog.setButton(Dialog.BUTTON_NEGATIVE, "CANCEL", DialogInterface.OnClickListener
            { dialog, which -> alertDialog.dismiss() })
            alertDialog.setMessage(post.toString());
            alertDialog.show();
        }
    }

}
