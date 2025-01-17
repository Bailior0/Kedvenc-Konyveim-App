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
import co.zsmb.rainbowcake.navigation.navigator
import hu.fishee.favouritebooks.data.model.BookItem
import hu.fishee.favouritebooks.ui.details.DetailsFragment
import hu.fishee.favouritebooks.ui.newbook.BookItemDialogFragment
import hu.fishee.favouritebooks.ui.newbook.BookItemDialogFragment.EditBookItemDialogListener
import hu.fishee.favouritebooks.ui.newbook.BookItemDialogFragment.NewBookItemDialogListener
import hu.fishee.favouritebooks.views.BookList
import hu.fishee.favouritebooks.views.theme.AppUiTheme
import hu.fishee.favouritebooks.views.FullScreenLoading

@AndroidEntryPoint
class BookListFragment: RainbowCakeFragment<BookListViewState, BookListViewModel>(),
    EditBookItemDialogListener, NewBookItemDialogListener {
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
                        is BoolListContent -> BookList(
                            viewState.books,
                            onItemClicked = ::onBookSelected,
                            onFabClicked = ::onItemAdded,
                            onEditClicked = ::onItemEdited,
                            onDeleteClicked = ::onItemRemoved
                        )
                    }.exhaustive
                }
            }
        }
    }

    private fun onItemEdited(item: BookItem) {
        BookItemDialogFragment(item).show(
            childFragmentManager,
            BookItemDialogFragment.TAG
        )
    }

    private fun onItemAdded() {
        BookItemDialogFragment().show(
            childFragmentManager,
            BookItemDialogFragment.TAG
        )
    }

    private fun onItemRemoved(item: BookItem) {
        viewModel.remove(item)
    }

    override fun onBookItemEdited(editedItem: BookItem) {
        viewModel.edit(editedItem)
    }

    override fun onBookItemCreated(newItem: BookItem) {
        viewModel.add(newItem)
    }

    private fun onBookSelected(bookItem: BookItem) {
        navigator?.add(
            DetailsFragment.newInstance(bookItem),
            enterAnim = 0,
            exitAnim = 0,
            popEnterAnim = 0,
            popExitAnim = 0
        )
    }
}