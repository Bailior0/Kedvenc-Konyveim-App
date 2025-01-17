package hu.fishee.favouritebooks.views

import hu.fishee.favouritebooks.data.model.BookItem
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

@Composable
fun Details(
    book: BookItem?,
    onBackClick: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        TopAppBar(
            title = { book?.title?.let { Text(text = it) } },
            navigationIcon = {
                IconButton(
                    content = {
                        Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, null)
                    },
                    onClick = onBackClick
                )
            }
        )

        Row(
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp, 10.dp)
        ) {
            Text("Title:", Modifier.weight(1f), textAlign = TextAlign.Justify, fontSize = 24.sp)
            if(book?.title != null)
                Text(book.title, Modifier.weight(1f) ,textAlign = TextAlign.Justify, fontSize = 24.sp)
        }
        Row(
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp, 10.dp)
        ) {
            Text("Author:", Modifier.weight(1f), textAlign = TextAlign.Justify, fontSize = 24.sp)
            if(book?.author != null)
                Text(book.author, Modifier.weight(1f) ,textAlign = TextAlign.Justify, fontSize = 24.sp)
        }
        Row(
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp, 10.dp)
        ) {
            Text("Description:", Modifier.weight(1f), textAlign = TextAlign.Justify, fontSize = 24.sp)
            if(book?.description != null)
                Text(book.description, Modifier.weight(1f) ,textAlign = TextAlign.Justify, fontSize = 24.sp)
        }
        if(book != null && book.cover != "")
            Box(
                modifier = Modifier
                    .padding(top = 20.dp, bottom = 20.dp)
                    .size(300.dp)
                    .align(Alignment.CenterHorizontally)
            ) {
                AsyncImage(
                    model = book.cover,
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    alignment = Alignment.Center
                )
            }
    }
}

@Preview
@Composable
fun DetailsPreview() {
    Details(
        BookItem(0, "ok", "ok", "ok", "https://www.kreactivity.hu/img/23190/G00176/264x264,r/Kis-tigris-festes-es-gyemantszemes-kreativ-hibrid-kep.webp?time=1715340732")
    )
}