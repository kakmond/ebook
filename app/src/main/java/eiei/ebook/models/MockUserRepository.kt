package eiei.ebook.models

import android.util.Log

/**
 * Created by Mond on 30/3/2561.
 */
class MockUserRepository : UserRepository() {

    override fun getUsers(): ArrayList<User> {
        return userList
    }

    override fun loadAllUsers() {
        userList.add(User("Mond"))
        userList.add(User("Mick"))
        setChanged()
        notifyObservers()
    }


}