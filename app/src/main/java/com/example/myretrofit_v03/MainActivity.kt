package com.example.myretrofit_v03

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myretrofit_v03.ui.theme.MyRetrofit_v03Theme
import com.example.myretrofit_v03.viewmodel.MyViewModel
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyRetrofit_v03Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, myViewModel: MyViewModel = viewModel(), modifier: Modifier = Modifier) {
    val list = myViewModel.listResultFlow.collectAsState(initial = emptyList()).value
    Column() {
        Text(
            text = "Hello $name!",
            modifier = modifier
        )
        Spacer(modifier = Modifier.padding(vertical = 20.dp, horizontal = 10.dp))
        Button(onClick = {
            myViewModel.getQuotes()


        }) {
            Text(text = "Retrofit")
        }

        LazyColumn() {
            items(list) { it ->
              
                CardAuthor(
                    author = it.author,
                    content = it.content,
                    dateAdded = it.dateAdded
                )
            }
        }

    }

}

@Composable
fun CardAuthor(author: String, content: String, dateAdded: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 2.dp,
            horizontal = 5.dp)
            .background(color = Color.LightGray, shape = RoundedCornerShape(8.dp))
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.padding(
                vertical = 5.dp,
                horizontal = 5.dp
            )
        ) {
            Text(text = author)
            Spacer(modifier = Modifier.padding(horizontal = 5.dp))
            Column() {
                Text(text = content)
                Text(text = dateAdded)
            }
        }
    }

}

