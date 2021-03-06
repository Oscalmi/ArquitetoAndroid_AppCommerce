package com.example.appcommerce

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appcommerce.Adapter.ProductAdapter
import com.example.appcommerce.Adapter.ProductCategoryAdapter
import com.example.appcommerce.model.Product
import com.example.appcommerce.model.ProductCategory
import com.example.appcommerce.model.ProductColor
import com.example.appcommerce.model.ProductSize
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    lateinit var toolbar: Toolbar
    lateinit var drawerLayout: DrawerLayout
    lateinit var navigationView: NavigationView
    lateinit var textTitle: TextView
    lateinit var textLogin: TextView
    lateinit var recyclerCategory: RecyclerView
    lateinit var recyclerProduct: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayShowTitleEnabled(false)

        textTitle = findViewById(R.id.toolbar_title)
        textTitle.text = getString(R.string.app_name)

        drawerLayout = findViewById(R.id.nav_drawer_layout)

        val toggle: ActionBarDrawerToggle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            toolbar,
            R.string.toggle_open,
            R.string.toggle_close
        )
        drawerLayout.addDrawerListener(toggle)

        toggle.syncState()

        navigationView = findViewById(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)

        textLogin = navigationView.getHeaderView(0).findViewById(R.id.header_profile_name)
        textLogin.setOnClickListener {
            val intent = Intent(this, UserLoginActivity::class.java)
            startActivity(intent)
        }

        recyclerCategory = findViewById(R.id.rv_main_product_category)

        val arrayCategory = arrayListOf<ProductCategory>(
            ProductCategory("1", "Camisetas"),
            ProductCategory("2", "Calças"),
            ProductCategory("3", "Meias"),
            ProductCategory("4", "Sapatos")
        )

        val adapterCategory = ProductCategoryAdapter(arrayCategory, this)

        recyclerCategory.adapter = adapterCategory
        recyclerCategory.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        recyclerProduct = findViewById(R.id.rv_main_product)

        val adapterProduct = ProductAdapter(fillRVProduct(), this)

        recyclerProduct.adapter = adapterProduct
        recyclerProduct.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

    }

    fun fillRVProduct() : List<Product>{
        val product1: Product = Product(
            "1",
            "Camiseta 89",
            ProductCategory("id", "Camisetas"),
            "Camiseta super leve para prática de esportes.",
            19.90,
            arrayListOf(ProductColor("1", "Branco", "#ffffff"), ProductColor("2", "Preta", "#000000")),
            arrayListOf(ProductSize("1", "P"), ProductSize("2", "M"), ProductSize("3", "G")),
            emptyList()
        )

        val product2: Product = Product(
            "2",
            "Calça Jeans",
            ProductCategory("id", "Calças"),
            "Calça impermeável e durável para rotina diária",
            59.90,
            arrayListOf(ProductColor("1", "Branco", "#ffffff"), ProductColor("2", "Preta", "#000000")),
            arrayListOf(ProductSize("1", "M"), ProductSize("2", "G"), ProductSize("3", "XG")),
            emptyList()
        )

        val product3: Product = Product(
            "3",
            "Tênis Nimbus",
            ProductCategory("id", "Calçados"),
            "Tênis com amortecimento em gel, perfeito para corridas e outras práticas esportivas",
            599.90,
            arrayListOf(ProductColor("1", "Branco", "#ffffff"), ProductColor("2", "Azul", "#0000FF")),
            arrayListOf(ProductSize("1", "40"), ProductSize("2", "42"), ProductSize("3", "44")),
            emptyList()
        )

        return arrayListOf(product1, product2, product3)
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START))
            drawerLayout.closeDrawer(GravityCompat.START)
        else
            super.onBackPressed()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.nav_home -> {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }

            R.id.nav_account -> {
                val intent = Intent(this, UserProfileActivity::class.java)
                startActivity(intent)
            }

            R.id.nav_category -> {
                val intent = Intent(this, ProductCategoryActivity::class.java)
                startActivity(intent)
            }
            R.id.nav_orders -> Toast.makeText(this, "Compras", Toast.LENGTH_LONG).show()
            R.id.nav_cart -> Toast.makeText(this, "Carrinho", Toast.LENGTH_LONG).show()
            R.id.nav_logout -> Toast.makeText(this, "Sair", Toast.LENGTH_LONG).show()
        }

        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
}
