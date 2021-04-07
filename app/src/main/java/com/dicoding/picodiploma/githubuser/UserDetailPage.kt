package com.dicoding.picodiploma.githubuser

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.dicoding.picodiploma.githubuser.db.User
import de.hdodenhof.circleimageview.CircleImageView

class UserDetailPage : AppCompatActivity() {
    companion object {
        const val EXTRA_USER = "extra_user"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_detail_page)

        val user = intent.getParcelableExtra<User>(EXTRA_USER) as User

        val imgDetailPhoto : CircleImageView = findViewById(R.id.img_detail_photo)
        imgDetailPhoto.setImageResource(user.avatar!!)

        val tvName: TextView = findViewById(R.id.tv_detail_name)
        tvName.text = user.name

        val tvUsername: TextView = findViewById(R.id.tv_detail_username)
        tvUsername.text = user.username

        val tvFollower: TextView = findViewById(R.id.tv_detail_follower)
        tvFollower.text = user.follower.toString()

        val tvFollowing: TextView = findViewById(R.id.tv_detail_following)
        tvFollowing.text = user.following.toString()

        val tvRepository: TextView = findViewById(R.id.tv_detail_repository)
        tvRepository.text = user.repository.toString()

        val tvCompany: TextView = findViewById(R.id.tv_detail_company)
        tvCompany.text = user.company

        val tvLocation: TextView = findViewById(R.id.tv_detail_location)
        tvLocation.text = user.location

        supportActionBar?.title = user.name
    }
}