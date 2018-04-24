package eiei.ebook

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import eiei.ebook.models.MockUserRepository
import eiei.ebook.presenter.UserPresenter
import eiei.ebook.presenter.UserView
import android.support.v7.widget.LinearLayoutManager
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_user.*
import android.view.LayoutInflater
import android.view.ViewGroup
import android.support.annotation.NonNull
import android.widget.TextView

import eiei.ebook.models.User


class UserActivity : AppCompatActivity(), UserView {

    lateinit var presenter: UserPresenter
    lateinit var mock: MockUserRepository

    override fun setUserList(users: ArrayList<User>) {
        userView.adapter = UserAdapter(users)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)
        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL
        userView.layoutManager = layoutManager
        mock = MockUserRepository()
        presenter = UserPresenter(this, mock)
        var test:ArrayList<User> = ArrayList()
        test.add(User("Mond"))
        test.add(User("Mick"))
        this.setUserList(test)
    }

}
