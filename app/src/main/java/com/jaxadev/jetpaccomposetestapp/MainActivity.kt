package com.jaxadev.jetpaccomposetestapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jaxadev.jetpaccomposetestapp.ui.theme.JetpacComposeTestAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp {
                Greeting(name = "Jaxaxdev")
            }
        }
    }
}


@Composable
fun MyApp(content: @Composable () -> Unit) {
    JetpacComposeTestAppTheme {
        Surface(color = Color.Yellow) {
            MyScreenContent()
        }
    }
}

@Composable
fun Greeting(name: String) {
    var isSelected by remember { mutableStateOf(false) }
    val backgroundColor by animateColorAsState(if (isSelected) Color.Red else Color.Transparent)

    Text(
        text = "Hello $name!",
        modifier = Modifier
            .padding(24.dp)
            .background(color = backgroundColor)
            .clickable(onClick = { isSelected = !isSelected }),
        style = MaterialTheme.typography.h1
    )
}

@Composable
fun MyScreenContent(
    names: List<String> = List(1000) { "Hello Android #$it" }
) {
    val counterState = remember { mutableStateOf(0) }

    Column(modifier = Modifier.fillMaxHeight()) {
        NameList(names, Modifier.weight(1f))
        Counter(
            count = counterState.value,
            updateCount = { newCount ->
                counterState.value = newCount
            }
        )
    }
}

@Composable
fun Counter(count: Int, updateCount: (Int) -> Unit) {

    Button(
        onClick = { updateCount(count + 1) },
        colors = ButtonDefaults.buttonColors(backgroundColor = if (count > 5) Color.Green else Color.White)
    ) {
        Text(text = "Jaxadev Tugmani bostirib bosayabdi $count")
    }

}

@Composable
fun NameList(names: List<String>, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier) {
        items(items = names) { name ->
            Greeting(name = name)
            Divider(color = Color.Black)
        }
    }
}


@Preview
@Composable
fun DefaultPreview() {
    MyApp {
        MyScreenContent()
    }
}