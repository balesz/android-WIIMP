package net.solutinno.wiimp

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import android.view.View
import kotlinx.android.synthetic.main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.main)

        setSupportActionBar(main_toolbar)

        val toggle = ActionBarDrawerToggle(this, main_drawer, main_toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        main_drawer.setDrawerListener(toggle)
        toggle.syncState()

        main_fab.setOnClickListener(onFabClick)

        main_navigation.setNavigationItemSelectedListener(onNavigationItemSelected)
    }

    override fun onBackPressed() {
        if (main_drawer.isDrawerOpen(Gravity.START))
            main_drawer.closeDrawer(Gravity.START)
        else super.onBackPressed()
    }

    val onNavigationItemSelected = NavigationView.OnNavigationItemSelectedListener {
        if (it.title == "Settings") {
            fragmentManager.beginTransaction()
                    .replace(main_content.id, SettingsFragment(), "Settings")
                    .addToBackStack(null)
                    .commit()
        }
        main_drawer.closeDrawer(Gravity.START)
        true
    }

    val onFabClick = View.OnClickListener {
        main_drawer.openDrawer(Gravity.END)
    }
}
