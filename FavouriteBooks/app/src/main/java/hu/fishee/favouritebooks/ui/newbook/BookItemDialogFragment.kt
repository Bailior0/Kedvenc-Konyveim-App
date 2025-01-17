package hu.fishee.favouritebooks.ui.newbook

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import co.zsmb.rainbowcake.base.RainbowCakeDialogFragment
import co.zsmb.rainbowcake.extensions.exhaustive
import co.zsmb.rainbowcake.hilt.getViewModelFromFactory
import dagger.hilt.android.AndroidEntryPoint
import hu.fishee.favouritebooks.data.model.BookItem
import hu.fishee.favouritebooks.views.BookDialog
import hu.fishee.favouritebooks.views.FullScreenLoading
import hu.fishee.favouritebooks.views.theme.AppUiTheme

@AndroidEntryPoint
class BookItemDialogFragment() : RainbowCakeDialogFragment<BookItemViewState, BookItemViewModel>() {
    override fun provideViewModel() = getViewModelFromFactory()

    interface NewBookItemDialogListener {
        fun onBookItemCreated(newItem: BookItem)
    }

    interface EditBookItemDialogListener {
        fun onBookItemEdited(editedItem: BookItem)
    }

    private lateinit var newListener: NewBookItemDialogListener
    private lateinit var editListener: EditBookItemDialogListener

    private var item: BookItem? = null
    private var type: CreateOrEdit = CreateOrEdit.CREATE

    enum class CreateOrEdit {
        CREATE, EDIT
    }

    constructor(item: BookItem) : this() {
        this.item = item
        this.type = CreateOrEdit.EDIT
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

        viewModel.setNewBook()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val parent = parentFragment
        this.editListener = parent as EditBookItemDialogListener
        this.newListener = parent as NewBookItemDialogListener
    }

    companion object {
        const val TAG = "NewBookItemDialogFragment"
    }

    override fun render(viewState: BookItemViewState) {
        (view as ComposeView).setContent {
            AppUiTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(400.dp),
                    color = MaterialTheme.colorScheme.background
                ) {
                    when (viewState) {
                        is Loading -> FullScreenLoading()
                        is BookItemContent -> when (type) {
                            CreateOrEdit.CREATE -> BookDialog(
                                null,
                                onOkUploadClick = ::onUpload,
                                onOkEditClick = ::onEdit,
                                onCancelClick = ::onCancel
                            )
                            CreateOrEdit.EDIT -> BookDialog(
                                item,
                                onOkUploadClick = ::onUpload,
                                onOkEditClick = ::onEdit,
                                onCancelClick = ::onCancel
                            )
                        }
                    }.exhaustive
                }
            }
        }
    }

    private fun onUpload(newItem: BookItem) {
        newListener.onBookItemCreated(newItem)
        dialog?.dismiss()
    }

    private fun onEdit(editedItem: BookItem) {
        editListener.onBookItemEdited(editedItem)
        dialog?.dismiss()
    }

    private fun onCancel() {
        dialog?.dismiss()
    }
}