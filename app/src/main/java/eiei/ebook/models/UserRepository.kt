package eiei.ebook.models

import android.util.Log
import java.util.*
import kotlin.collections.ArrayList

/**
 * Created by Mond on 30/3/2561.
 */
abstract class UserRepository : Observable() {

    var userList = ArrayList<User>()

    abstract fun loadAllUsers()
    abstract fun getUsers(): ArrayList<User>
}