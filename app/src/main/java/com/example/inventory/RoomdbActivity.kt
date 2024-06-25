package com.example.inventory

import android.app.Activity
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.roomdb.database.InventoryDatabase
import com.example.roomdb.database.Item
import com.example.roomdb.database.ItemDao
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class RoomdbActivity : Activity() {
    lateinit var dao: ItemDao


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // enableEdgeToEdge()
        setContentView(R.layout.activity_roomdb)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        var database = InventoryDatabase.getDatabase(this)
        dao = database.itemDao()

    }

    fun InsertDb(view: View) {
        var myItem = Item(111,"fruits",222.0,333)
        createRowDb(myItem)

    }

    private fun createRowDb(myItem: Item) {
        GlobalScope.launch {
            dao.insert(myItem)
        }
    }
}