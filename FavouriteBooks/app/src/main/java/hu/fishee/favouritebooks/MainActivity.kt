package hu.fishee.favouritebooks

import android.os.Bundle
import co.zsmb.rainbowcake.navigation.SimpleNavActivity
import dagger.hilt.android.AndroidEntryPoint
import hu.fishee.favouritebooks.ui.list.BookListFragment

@AndroidEntryPoint
class MainActivity : SimpleNavActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) {
            navigator.add(BookListFragment())
        }
    }
}