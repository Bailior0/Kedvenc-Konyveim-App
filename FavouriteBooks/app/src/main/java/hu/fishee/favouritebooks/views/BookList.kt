package hu.fishee.favouritebooks.views

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import androidx.compose.ui.tooling.preview.Preview
import hu.fishee.favouritebooks.data.model.BookItem
import hu.fishee.favouritebooks.data.network.ConnectivityChecker.isConnected

@Composable
fun BookList(
    books: List<BookItem>,
    onItemClicked: (BookItem) -> Unit,
    onFabClicked: () -> Unit,
    onEditClicked: (BookItem) -> Unit,
    onDeleteClicked: (BookItem) -> Unit
) {
    val data  = mutableListOf<BookItem>()
    data.addAll(books)
    val context = LocalContext.current

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { onFabClicked() },
                modifier = Modifier
                    .padding(0.dp, 0.dp, 0.dp, 70.dp)
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add")
            }
        }
    ) {
            innerPadding ->

        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            TopAppBar( title = { Text(
                text = "Favourite books", style = TextStyle(color = Color.White, fontWeight = FontWeight.Bold, fontSize = 20.sp)
            ) } )

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(5.dp, 5.dp, 5.dp, 5.dp)
            ) {
                itemsIndexed(data) { _, item ->
                    TripItem(
                        item = item,
                        onItemClicked = onItemClicked,
                        onEditClicked = onEditClicked,
                        onDeleteClicked = onDeleteClicked,
                        context = context
                    )
                }
            }
        }
    }
}

@Composable
fun TripItem(
    item: BookItem,
    onItemClicked: (BookItem) -> Unit,
    onEditClicked: (BookItem) -> Unit,
    onDeleteClicked: (BookItem) -> Unit,
    context: Context
) {
    val textStyle = if (item.title.split(" ").any { isPalindrome(it) }) {
        TextStyle(color = Color.Red, fontWeight = FontWeight.Bold, fontSize = 24.sp)
    } else {
        TextStyle(color = Color.Black, fontSize = 24.sp)
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(1.dp)
            .clickable(onClick = {
                onItemClicked(item)
            }),
        shape = RoundedCornerShape(20),
        elevation = 5.dp
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(vertical = 5.dp)
                .fillMaxWidth()
        ) {
            if (item.cover != "" && isConnected(context))
                Box(
                    modifier = Modifier
                        .padding(all = 10.dp)
                        .size(100.dp)
                ) {
                    AsyncImage(
                        model = item.cover,
                        contentDescription = null
                    )
                }
            Row(
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = item.title, style = textStyle, maxLines = 1, modifier = Modifier.padding(all = 5.dp)
                )
            }

            Row(
                horizontalArrangement = Arrangement.End,
            ) {
                IconButton(
                    onClick = {onEditClicked(item)},
                    modifier = Modifier.offset(x = 10.dp)
                            .padding(all = 5.dp)
                            .size(50.dp)
                ) {
                    Icon(imageVector  = Icons.Filled.Edit, "")
                }
                IconButton(
                    onClick = {onDeleteClicked(item)},
                    modifier = Modifier
                        .padding(all = 5.dp)
                        .size(50.dp)
                ) {
                    Icon(imageVector  = Icons.Filled.Delete, "")
                }
            }
        }
    }
}

@Preview
@Composable
fun BookListPreview() {
    BookList(
        mutableListOf(BookItem(0, "ok", "ok", "ok", "")),
        onItemClicked = {},
        onFabClicked = {},
        onEditClicked = {},
        onDeleteClicked = {}
    )
}


fun isPalindrome(word: String): Boolean {
    val cleanWord = word.lowercase().filter { it.isLetterOrDigit() }
    return cleanWord == cleanWord.reversed()
}
