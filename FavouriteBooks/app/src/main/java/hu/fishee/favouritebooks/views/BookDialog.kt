package hu.fishee.favouritebooks.views

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import hu.fishee.favouritebooks.data.model.BookItem

@Composable
fun BookDialog(
    item: BookItem?,
    onOkUploadClick: (BookItem) -> Unit,
    onOkEditClick: (BookItem) -> Unit,
    onCancelClick: () -> Unit
) {
    var titleInput by remember { mutableStateOf("") }
    var authorInput by remember { mutableStateOf("") }
    var descriptionInput by remember { mutableStateOf("") }
    var coverInput by remember { mutableStateOf("") }

    if(item != null) {
        titleInput = item.title
        authorInput = item.author
        descriptionInput = item.description
        coverInput = item.cover
    }

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(10.dp)
    ) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            val (
                dialogTitle,
                title,
                author,
                description,
                cover,
                okButton,
                cancelButton
            ) = createRefs()

            Text(
                text = when(item == null) {
                    true -> "New Book"
                    false -> "Edit Book"
                },
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(5.dp, 0.dp)
                    .constrainAs(dialogTitle) {
                        top.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                    }
            )

            OutlinedTextField(
                value = titleInput,
                onValueChange = { titleInput = it },
                singleLine = true,
                label = {
                    Text(
                        text = "Title",
                        color = Color.Gray
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp, 2.dp, 0.dp, 0.dp)
                    .constrainAs(title) {
                        top.linkTo(dialogTitle.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
            )

            OutlinedTextField(
                value = authorInput,
                onValueChange = { authorInput = it },
                singleLine = true,
                label = {
                    Text(
                        text = "Author",
                        color = Color.Gray
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp, 2.dp, 0.dp, 0.dp)
                    .constrainAs(author) {
                        top.linkTo(title.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
            )

            OutlinedTextField(
                value = descriptionInput,
                onValueChange = { descriptionInput = it },
                singleLine = true,
                label = {
                    Text(
                        text = "Description",
                        color = Color.Gray
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp, 2.dp, 0.dp, 0.dp)
                    .constrainAs(description) {
                        top.linkTo(author.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
            )

            OutlinedTextField(
                value = coverInput,
                onValueChange = { coverInput = it },
                singleLine = true,
                label = {
                    Text(
                        text = "Cover URL",
                        color = Color.Gray
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp, 2.dp, 0.dp, 0.dp)
                    .constrainAs(cover) {
                        top.linkTo(description.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
            )

            Button(
                content = {
                    Text(
                        text = "Cancel",
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .padding(20.dp, 0.dp),
                        color = Color.White
                    )
                },
                onClick = {
                    onCancelClick()
                },
                modifier = Modifier
                    .padding(5.dp)
                    .constrainAs(cancelButton) {
                        top.linkTo(cover.bottom)
                        end.linkTo(parent.end)
                    }
            )

            Button(
                content = {
                    Text(
                        text = "OK",
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .padding(20.dp, 0.dp),
                        color = Color.White
                    )
                },
                onClick = {
                    if(item == null) {
                        if(titleInput.isEmpty())
                            Toast.makeText(context, "Place field must be filled", Toast.LENGTH_SHORT).show()
                        else
                            onOkUploadClick(
                                BookItem(
                                    title = titleInput,
                                    author = authorInput,
                                    description = descriptionInput,
                                    cover = coverInput
                                )
                            )
                    } else {
                        if(titleInput.isEmpty())
                            Toast.makeText(context, "Place field must be filled", Toast.LENGTH_SHORT).show()
                        else {
                            item.title = titleInput
                            item.author = authorInput
                            item.description = descriptionInput
                            item.cover = coverInput
                            onOkEditClick(item)
                        }
                    }
                },
                modifier = Modifier
                    .padding(5.dp)
                    .constrainAs(okButton) {
                        top.linkTo(cover.bottom)
                        end.linkTo(cancelButton.start)
                    }
            )
        }
    }
}

@Preview
@Composable
fun BookDialogPreview() {
    BookDialog(
        null,
        onOkUploadClick = {},
        onOkEditClick = {},
        onCancelClick = {}
    )
}
