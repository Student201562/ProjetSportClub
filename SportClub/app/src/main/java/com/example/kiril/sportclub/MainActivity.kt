package com.example.kiril.sportclub

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import okhttp3.OkHttpClient
import okhttp3.Request

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private val okHttpClient = OkHttpClient()
    private val trainers = mutableListOf<String>()

    private val trainersAdapter = TrainerListAdapter(trainers)

    private lateinit var linearLayoutManager: LinearLayoutManager;


    private fun showData(repos: List<String>) {
        this.trainers.run {
            clear()
            addAll(repos)
        }
        trainersAdapter.notifyDataSetChanged()
    }

    private fun loadData(onComplete: (List<String>) -> Unit) {
        Thread {
            val request = Request.Builder()
                    .url("https://api.github.com/users/square/repos")
                    .build()
            val response = okHttpClient.newCall(request).execute()
            val respBody = response.body()!!.string()
            val trainers = Gson().fromJson(respBody, TrainerInfo.List::class.java)
            val names = trainers.map { it.lastNameTrainer; it.nameTrainer; it.secondNameTrainer }
            runOnUiThread {
                onComplete(names)

            }
        }.start()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_trainers -> {

            }
            R.id.nav_discounts -> {

            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}
