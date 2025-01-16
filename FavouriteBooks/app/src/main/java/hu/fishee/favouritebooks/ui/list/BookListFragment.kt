package hu.fishee.favouritebooks.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import co.zsmb.rainbowcake.base.RainbowCakeFragment
import co.zsmb.rainbowcake.hilt.getViewModelFromFactory
import dagger.hilt.android.AndroidEntryPoint
import co.zsmb.rainbowcake.extensions.exhaustive
import hu.fishee.favouritebooks.views.theme.AppUiTheme
import hu.fishee.favouritebooks.views.FullScreenLoading

@AndroidEntryPoint
class BookListFragment: RainbowCakeFragment<BookListViewState, BookListViewModel>() {
    override fun provideViewModel() = getViewModelFromFactory()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return ComposeView(requireContext()).apply {
            setContent {
                FullScreenLoading()
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.load()
    }

    override fun render(viewState: BookListViewState) {
        (view as ComposeView).setContent {
            AppUiTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    when (viewState) {
                        is Loading -> FullScreenLoading()
                        is BoolListContent -> FullScreenLoading()
                    }.exhaustive
                }
            }
        }
    }
}