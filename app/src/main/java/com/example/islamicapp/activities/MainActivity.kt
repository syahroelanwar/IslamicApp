package com.example.islamicapp.activities

import android.app.Activity
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.islamicapp.R
import com.example.islamicapp.activities.sideactivity.KontakActivity
import com.example.islamicapp.activities.sideactivity.TentangActivity
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout
    lateinit var context : Context
    lateinit var uriString: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        context = this

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                    View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }

        if (Build.VERSION.SDK_INT >= 21) {
            setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false)
            window.statusBarColor = Color.TRANSPARENT
        }

        toolbar.title = ""
        setSupportActionBar(toolbar)
        assert(supportActionBar != null)
        drawerLayout = findViewById(R.id.drawer_layout)
        setUpDrawerLayout()

        btn1.setOnClickListener(){
            startActivity(Intent(this,NiatBacaShalat::class.java))
        }

        btn2.setOnClickListener(){
            startActivity(Intent(this, RukunActivity::class.java))
        }

        btn3.setOnClickListener(){
            startActivity(Intent(this, MalaikatActivity::class.java))
        }

        btn4.setOnClickListener(){
            startActivity(Intent(this, NabiActivity::class.java))
        }

        btn5.setOnClickListener(){
            startActivity(Intent(this, HukumIslamActivity::class.java))
        }

        btn6.setOnClickListener(){
            startActivity(Intent(this, DoaActivity::class.java))
        }

        val navigationView: NavigationView = findViewById(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener { menuItem ->
            menuItem.isChecked = true
            drawerLayout.closeDrawers()

            when(menuItem.itemId){
                R.id.nav2 -> {
                    startActivity(Intent(this, KontakActivity::class.java))
                    drawerLayout.openDrawer(GravityCompat.START)
                }
                R.id.nav3 -> {
                    startActivity(Intent(this, TentangActivity::class.java))
                    drawerLayout.openDrawer(GravityCompat.START)
                }
                R.id.nav4 -> {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com"))
                    startActivity(intent)
                    drawerLayout.openDrawer(GravityCompat.START)
                }
                R.id.nav5 -> {
                    val intent = Intent(Intent.ACTION_SEND)
                    intent.type = "text/plain"
                    uriString = "Islamic App: Belajar dasar-dasar agama islam "+" https://play.google.com"
                    intent.putExtra(Intent.EXTRA_TEXT,uriString)
                    startActivity(Intent.createChooser(intent,"Bagikan Lewat"))
                    drawerLayout.openDrawer(GravityCompat.START)
                }
            }
            true
        }

        drawerLayout.addDrawerListener(
            object : DrawerLayout.DrawerListener {
                override fun onDrawerSlide(drawerView: View, slideOffset: Float) {
                    // Respond when the drawer's position changes
                }

                override fun onDrawerOpened(drawerView: View) {
                    // Respond when the drawer is opened
                }

                override fun onDrawerClosed(drawerView: View) {
                    // Respond when the drawer is closed
                }

                override fun onDrawerStateChanged(newState: Int) {
                    // Respond when the drawer motion state changes
                }
            }
        )

    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                drawerLayout.openDrawer(GravityCompat.START)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setUpDrawerLayout() {
        val toggle = ActionBarDrawerToggle(
            this, drawerLayout,toolbar, R.string.drawerOpen, R.string.drawerClose)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
    }

    private fun ext(){
        val dialog = AlertDialog.Builder(context)
        dialog.setTitle("Peringatan !")
        dialog.setMessage("Anda yakin ingin keluar ?")
        dialog.setPositiveButton("Ya") { _: DialogInterface, _: Int ->
            finish()
            super.onBackPressed()
        }
        dialog.setNegativeButton("Tidak") { _: DialogInterface, _: Int -> }
        dialog.show()
    }

    override fun onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START)
        }else{
            ext()
        }
    }

    companion object {
        fun setWindowFlag(activity: Activity, bits: Int, on: Boolean) {
            val window = activity.window
            val layoutParams = window.attributes
            if (on) {
                layoutParams.flags = layoutParams.flags or bits
            } else {
                layoutParams.flags = layoutParams.flags and bits.inv()
            }
            window.attributes = layoutParams
        }
    }
}