package com.skillbox.a72homework

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        createToolbar()

    }

    private fun createToolbar() {
        // Листнер, который обрабатывает нажатие на иконки и меню в тулбаре
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

        //Листнер, который обрабатывает нажатие на навигационную кнопку (кнопка "Назад") в тулбаре
        toolbar.setNavigationOnClickListener {
            toast("Back")
        }

        //Листнер, который обрабатывает раскрытие и закрытие поиска
        val search = toolbar.menu.findItem(R.id.menu_search)
        search.setOnActionExpandListener(object : MenuItem.OnActionExpandListener {
            override fun onMenuItemActionExpand(item: MenuItem?): Boolean {
                expandTextView.text = "Search Expanded"
                return true
            }

            override fun onMenuItemActionCollapse(item: MenuItem?): Boolean {
                expandTextView.text = "Search Collapsed"
                searchTextView.text = ""
                return true
            }
        })

        //Листнер, который обрабатывает ввод текста. Первый метод отвечает за обработку при нажатии на клавишу поиск
        //Второй метод отвечает за обработку при вводе текста в поисковик
        (search.actionView as SearchView).setOnQueryTextListener(object :
            SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null) {
                    if (newText.isNotEmpty()) {
                        var searchText = Regex("""\s(\w*$newText\w*)\s""").findAll(longTextView.text)
                        val result = StringBuilder()
                        for (item in searchText) {
                            result.append(item.value + "")
                        }
                        searchTextView.text = result
                    }
                }
                return true
            }
        })
    }

    //Функция, которая вызывает Тоасты
    private fun toast(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }
}