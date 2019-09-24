package com.pagetyler.nasadaillypict

import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import android.view.Menu
import android.view.View
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import androidx.recyclerview.widget.RecyclerView
import com.jakewharton.threetenabp.AndroidThreeTen
import com.pagetyler.nasadaillypict.adapter.getPreviousDay
import com.pagetyler.nasadaillypict.adapter.getShortDate
import com.pagetyler.nasadaillypict.adapter.getToday
import com.pagetyler.nasadaillypict.adapter.getYesterday
import com.pagetyler.nasadaillypict.ui.home.HomeFragmentDirections
import com.pagetyler.nasadaillypict.ui.home.HomeViewModel

import org.threeten.bp.Instant
import org.threeten.bp.ZoneId
import org.threeten.bp.ZonedDateTime


var baseURL = "" // Global access to current URL setting
var siteURL = "" // Global access to current URL setting
var keyURL = "" // Global access to current URL setting
var selectDate = "" // Global access to current selection date
var initialized = false
var today : ZonedDateTime? = null

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //
        baseURL = getString(R.string.BASE_URL1)
        siteURL = getString(R.string.Site_URL1)
        keyURL = getString(R.string.Key_URL1)
        //
        AndroidThreeTen.init(this)
        if(!initialized) { today  =  getToday(); initialized = true }
        var yesterday = getYesterday()
        var todayDateString :String  = getShortDate(today!!)
        selectDate = todayDateString
        //
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)

        val fab: FloatingActionButton = findViewById(R.id.fab)
        fab.setOnClickListener { view ->

            var newDate = getPreviousDay(today!!)
            selectDate = getShortDate(newDate)
            today = newDate
            var imgView : View = findViewById(R.id.photos_grid)

            navController.navigate(
                HomeFragmentDirections.actionShowNextPicture())

                    }

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow,
                R.id.nav_tools, R.id.nav_share, R.id.nav_send
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    fun now(): Instant {
        return Instant.now()
    }

    fun hereAndNow(): ZonedDateTime {
        return ZonedDateTime.ofInstant(now(), ZoneId.systemDefault())
    }
}
fun getBaseURL1() : String{

    return baseURL
}
fun getSiteURL1() : String{

    return siteURL
}

fun getSiteKey1() : String{

    return keyURL
}
fun getCurrentDate () : String {
    return selectDate
}
fun setCurentDate (dateIn : String){
    selectDate = dateIn
}