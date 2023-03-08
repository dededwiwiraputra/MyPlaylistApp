package com.example.myplaylistapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

class DetailFilm: AppCompatActivity() {
    companion object {
        const val EXTRA_PERSON = "extra_person"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_film)

        val imgPhoto: ImageView = findViewById(R.id.img_item_photo)
        val tvTitle: TextView = findViewById(R.id.tv_item_title)
        val tvYear: TextView = findViewById(R.id.tv_item_year)
        val tvDescription: TextView = findViewById(R.id.tv_item_description)

        val film = intent.getParcelableExtra<Film>(EXTRA_PERSON)

        if (film != null) {
            Glide.with(this)
                .load(film.photo) // URL Gambar
                .into(imgPhoto) // imageView mana yang akan diterapkan
            tvTitle.text = film.title
            tvYear.text = film.year
            tvDescription.text = film.description
        }

        //actionbar
        val actionbar = supportActionBar
        //set actionbar title
        actionbar!!.title = "Detail Film"
        //set back button
        actionbar.setDisplayHomeAsUpEnabled(true)
        actionbar.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.action_share, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_share -> {
                val shareIntent = Intent()
                shareIntent.action = Intent.ACTION_SEND
                shareIntent.putExtra(
                    Intent.EXTRA_TEXT,
                    "Hey check out my app at: https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID
                )
                shareIntent.type = "text/plain"
                startActivity(shareIntent)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}