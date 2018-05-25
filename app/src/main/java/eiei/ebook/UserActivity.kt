package eiei.ebook

import android.app.Activity
import android.content.DialogInterface
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import eiei.ebook.models.MockUserRepository
import eiei.ebook.presenter.UserPresenter
import eiei.ebook.presenter.UserView
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.widget.ArrayAdapter
import eiei.ebook.adapter.BookAdapter
import eiei.ebook.adapter.CartAdapter
import eiei.ebook.models.Book
import kotlinx.android.synthetic.main.activity_user.*

import eiei.ebook.models.User
import android.content.Intent
import android.view.View
import android.widget.EditText


class UserActivity : AppCompatActivity(), UserView {

    lateinit var presenter: UserPresenter
    lateinit var mock: MockUserRepository

    private var user: User? = null
    private var cart: ArrayList<Book> = ArrayList()

    private lateinit var clickedHandler: ClickHandler


    override fun setUserList(users: ArrayList<User>) {
        userView.adapter = UserAdapter(users)
    }

    fun setCartList(books: ArrayList<Book>) {
        var adapter = CartAdapter(books, clickedHandler)
        cartList.adapter = adapter
    }

    override fun onBackPressed() {
        intent.putExtra("result", cart)
        intent.putExtra("User", user)
        setResult(Activity.RESULT_OK, intent)
        super.onBackPressed()
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        user = intent.getParcelableExtra("User")

        cart = intent.getParcelableArrayListExtra("Cart")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)
        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL
        userView.layoutManager = layoutManager
        clickedHandler = ClickedHandler()

        mock = MockUserRepository()
        presenter = UserPresenter(this, mock)
        presenter.start()

        val cartlayoutManager = LinearLayoutManager(this)
        cartlayoutManager.orientation = LinearLayoutManager.VERTICAL
        cartList.layoutManager = cartlayoutManager


        txtMoney.text = user?.getBalance().toString() + " $"
        txtName.text = user?.name

        this.setCartList(cart!!)

    }

    fun addMoneyClicked(view: View) {
        val dialogBuilder = AlertDialog.Builder(this)
        val inflater = this.layoutInflater
        val dialogView = inflater.inflate(R.layout.moneydialog, null)
        dialogBuilder.setView(dialogView)

        val editText = dialogView.findViewById<View>(R.id.editTextName) as EditText

        dialogBuilder.setTitle("Add money")
        dialogBuilder.setMessage("Enter money below")
        dialogBuilder.setPositiveButton("Add", DialogInterface.OnClickListener { dialog, whichButton ->
            try {
                var amount: Double = editText.text.toString().toDouble()
                user?.addMoney(amount)
                txtMoney.text = user?.getBalance().toString() + " $"
            } catch (e: NumberFormatException) {
                val alertDialog = AlertDialog.Builder(
                        this).create()
                alertDialog.setTitle("Hold on!");
                alertDialog.setMessage("field are blank or wrong");
                alertDialog.show();
            }
        })
        dialogBuilder.setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, whichButton ->
            //pass
        })
        val b = dialogBuilder.create()
        b.show()
    }

    fun checkoutClicked(view: View) {
        var total: Double = 0.0
        for (book in cart)
            total += book.price
        if (total > user!!.getBalance()) {
            val alertDialog = AlertDialog.Builder(
                    this).create()
            alertDialog.setTitle("Hold on!");
            alertDialog.setMessage("Not enough money (require: " + total + " $)");
            alertDialog.show();
        } else {
            user?.checkout(cart!!)
            this.cart?.clear()
            txtMoney.text = user?.getBalance().toString() + " $"
            setCartList(cart!!)
        }
    }

    inner class ClickedHandler : ClickHandler {

        override fun onClick(post: Book) {
            cart?.remove(post)
            setCartList(cart!!)
        }
    }


}
