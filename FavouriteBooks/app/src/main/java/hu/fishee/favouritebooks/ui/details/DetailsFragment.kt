package hu.fishee.favouritebooks.ui.details

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
import co.zsmb.rainbowcake.navigation.extensions.applyArgs
import co.zsmb.rainbowcake.navigation.navigator
import hu.fishee.favouritebooks.data.model.BookItem
import hu.fishee.favouritebooks.views.Details
import hu.fishee.favouritebooks.views.theme.AppUiTheme
import hu.fishee.favouritebooks.views.FullScreenLoading

@AndroidEntryPoint
class DetailsFragment: RainbowCakeFragment<DetailsViewState, DetailsViewModel>() {
    override fun provideViewModel() = getViewModelFromFactory()

    private lateinit var book: BookItem

    companion object {
        private const val EXTRA_BOOK = "BOOK"


        fun newInstance(bookItem: BookItem): DetailsFragment {
            return DetailsFragment().applyArgs {
                putParcelable(EXTRA_BOOK, bookItem)
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return ComposeView(requireContext()).apply {
            setContent {
                FullScreenLoading()
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        book = arguments?.getParcelable(EXTRA_BOOK)!!
        viewModel.setBook(book)
    }

    override fun render(viewState: DetailsViewState) {
        (view as ComposeView).setContent {
            AppUiTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    when (viewState) {
                        is Loading -> FullScreenLoading()
                        is DetailsContent -> Details(book, onBackClick = { navigator?.pop() })
                    }.exhaustive
                }
            }
        }
    }
}