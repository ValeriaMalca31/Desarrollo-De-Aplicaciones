package com.example.trujigo

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationView
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView
    private lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        drawerLayout=findViewById(R.id.drawerLayout)
        navigationView=findViewById(R.id.navigationView)
        toggle= ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        navigationView.setNavigationItemSelectedListener { item ->
           when(item.itemId){
               R.id.nav_perfil -> reemplazarFragmento(PerfilFragment())
               R.id.nav_mapa -> reemplazarFragmento(MapaFragment())
               R.id.nav_planificar -> reemplazarFragmento(PlanificarFragment())
               R.id.nav_enVivo -> reemplazarFragmento(EnVivoFragment())
               R.id.nav_config -> reemplazarFragmento(ConfigFragment())
           }
           true
        }

    }
    private fun reemplazarFragmento(fragmento: Fragment){
        supportFragmentManager.beginTransaction().replace(R.id.contenedor_fragmentos,fragmento).commit()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if(toggle.onOptionsItemSelected(item)) true
        else super.onOptionsItemSelected(item)
    }
}