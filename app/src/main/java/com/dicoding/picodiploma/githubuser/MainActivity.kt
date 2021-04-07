package com.dicoding.picodiploma.githubuser

import android.content.Intent
import android.content.res.TypedArray
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.picodiploma.githubuser.adapters.UserAdapter
import com.dicoding.picodiploma.githubuser.db.User

class MainActivity : AppCompatActivity(), TextWatcher {
    private lateinit var edtSearchUser: EditText

    private lateinit var dataUsernames: Array<String>
    private lateinit var dataNames: Array<String>
    private lateinit var dataLocations: Array<String>
    private lateinit var dataRepositories: Array<String>
    private lateinit var dataCompanies: Array<String>
    private lateinit var dataFollowers: Array<String>
    private lateinit var dataFollowings: Array<String>
    private lateinit var dataAvatars: TypedArray

    private lateinit var userAdapter: UserAdapter

    private var listUsers: ArrayList<User> = arrayListOf()

    private lateinit var rvUsers: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvUsers = findViewById(R.id.rv_users)
        rvUsers.setHasFixedSize(true)

        prepare()
        addItem()

        showRecyclerUsers()

        edtSearchUser = findViewById(R.id.edt_search_user)
        edtSearchUser.addTextChangedListener(this)
    }

    private fun prepare() {
        dataUsernames = resources.getStringArray(R.array.username)
        dataNames = resources.getStringArray(R.array.name)
        dataLocations = resources.getStringArray(R.array.location)
        dataRepositories = resources.getStringArray(R.array.repository)
        dataCompanies = resources.getStringArray(R.array.company)
        dataFollowers = resources.getStringArray(R.array.follower)
        dataFollowings = resources.getStringArray(R.array.following)
        dataAvatars = resources.obtainTypedArray(R.array.avatar)
    }

    private fun addItem() {
        for (position in dataUsernames.indices) {
            val user = User(
                dataUsernames[position],
                dataNames[position],
                dataLocations[position],
                dataRepositories[position],
                dataCompanies[position],
                dataFollowers[position].toInt(),
                dataFollowings[position].toInt(),
                dataAvatars.getResourceId(position, -1)
            )

            listUsers.add(user)
        }
    }

    private fun showRecyclerUsers() {
        rvUsers.layoutManager = LinearLayoutManager(this)

        userAdapter = UserAdapter(listUsers)
        rvUsers.adapter = userAdapter

        userAdapter.setOnItemClickCallback(object : UserAdapter.OnItemClickCallback {
            override fun setItemClicked(user: User) {
                showDetailUser(user)
            }
        })
    }

    private fun showDetailUser(user: User) {
        val moveIntent = Intent(this@MainActivity, UserDetailPage::class.java)
        moveIntent.putExtra(UserDetailPage.EXTRA_USER, user)
        startActivity(moveIntent)
    }

    override fun afterTextChanged(s: Editable?) {
        filter(s.toString())
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
    }

    private fun filter(keyword: String) {
        val filteredList: ArrayList<User> = arrayListOf()

        for (item in listUsers) {
            if (item.name.toString().toLowerCase().contains(keyword.toLowerCase())) {
                filteredList.add(item)
            }
        }

        userAdapter.filterList(filteredList)
    }
}