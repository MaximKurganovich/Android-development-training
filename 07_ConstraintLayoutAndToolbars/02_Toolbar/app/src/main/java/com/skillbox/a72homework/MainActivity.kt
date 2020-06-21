package com.skillbox.a72homework

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val users = listOf(
        "User1",
        "User2",
        "User3",
        "User4",
        "User5",
        "Unknown"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        createToolbar()

    }

    private fun createToolbar() {
        toolbar.setOnMenuItemClickListener { item: MenuItem ->
            when (item.itemId) {
                R.id.action_1_menu -> {
                    toast("This is cake")
                    true
                }
                R.id.action_2_menu -> {
                    toast("This is color lens")
                    true
                }
                R.id.menu_smile -> {
                    toast("Smile =)")
                    true
                }
                else -> false
            }
        }

        toolbar.setNavigationOnClickListener {
            toast("Back")
        }

        val search = toolbar.menu.findItem(R.id.menu_search)
        search.setOnActionExpandListener(object : MenuItem.OnActionExpandListener {
            override fun onMenuItemActionExpand(item: MenuItem?): Boolean {
                expandTextView.text = "Search Expanded"
                return true
            }

            override fun onMenuItemActionCollapse(item: MenuItem?): Boolean {
                expandTextView.text = "Search Collapsed"
                return true
            }
        })

        (search.actionView as androidx.appcompat.widget.SearchView).setOnQueryTextListener(object :
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                users.filter { it.contains(newText ?: "", ignoreCase = true) }
                    .joinToString()
                    .let {
                        searchTextView.text = it
                    }
                return true
            }

        })

    }

    private fun toast(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }
}