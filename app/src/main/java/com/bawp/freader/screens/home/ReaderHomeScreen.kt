package com.bawp.freader.screens.home

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.bawp.freader.components.*
import com.bawp.freader.model.MBook
import com.bawp.freader.navigation.ReaderScreens
import com.google.firebase.auth.FirebaseAuth

@Composable
fun Home(navController: NavController,
         viewModel: HomeScreenViewModel = hiltViewModel()  //viewModel
        ) {
    Scaffold(topBar = {
                      ReaderAppBar(title = "A.Reader", navController = navController )
                      

    },
        floatingActionButton = {
            FABContent{
                navController.navigate(ReaderScreens.SearchScreen.name)
            }

        }) {
        //content
        Surface(modifier = Modifier.fillMaxSize()) {
            //home content
            HomeContent(navController, viewModel)

        }

    }


}

@Composable
fun HomeContent(navController: NavController, viewModel: HomeScreenViewModel) {
    var listOfBooks = emptyList<MBook>()
    val currentUser = FirebaseAuth.getInstance().currentUser

    if (!viewModel.data.value.data.isNullOrEmpty()) {
         listOfBooks = viewModel.data.value.data!!.toList().filter { mBook ->
              mBook.userId == currentUser?.uid.toString()
         }

        Log.d("Books", "HomeContent: ${listOfBooks.toString()}")
    }

//    val listOfBooks = listOf(
//          MBook(id = "dadfa", title = "Hello Again", authors = "All of us", notes = null),
//        MBook(id = "dadfa", title = " Again", authors = "All of us", notes = null),
//        MBook(id = "dadfa", title = "Hello ", authors = "The world us", notes = null),
//        MBook(id = "dadfa", title = "Hello Again", authors = "All of us", notes = null),
//        MBook(id = "dadfa", title = "Hello Again", authors = "All of us", notes = null)
//                            )
    //me @gmail.com
    val email = FirebaseAuth.getInstance().currentUser?.email
    val currentUserName = if (!email.isNullOrEmpty())
         FirebaseAuth.getInstance().currentUser?.email?.split("@")
        ?.get(0)else
            "N/A"
    Column(Modifier.padding(2.dp),
          verticalArrangement = Arrangement.Top) {
          Row(modifier = Modifier.align(alignment = Alignment.Start)) {
               TitleSection(label = "Your reading \n " + " activity right now...")
                Spacer(modifier = Modifier.fillMaxWidth(0.7f))
              Column {
                        Icon(
                            imageVector = Icons.Filled.AccountCircle,
                            contentDescription = "Profile",
                            modifier = Modifier
                                .clickable {
                                    navController.navigate(ReaderScreens.ReaderStatsScreen.name)
                                }
                                .size(45.dp),
                            tint = MaterialTheme.colors.secondaryVariant)
                  Text(text = currentUserName!!,
                      modifier = Modifier.padding(2.dp),
                      style = MaterialTheme.typography.overline,
                      color = Color.Red,
                      fontSize = 15.sp,
                      maxLines = 1,
                      overflow = TextOverflow.Clip)
                  Divider()
              }

              
          }

        ReadingRightNowArea(listOfBooks = listOfBooks,
            navController =navController )
        TitleSection(label = "Reading List")
        BookListArea(listOfBooks = listOfBooks,
            navController = navController)


        
    }

}

@Composable
fun BookListArea(listOfBooks: List<MBook>,
                 navController: NavController) {
    val addedBooks = listOfBooks.filter { mBook ->
          mBook.startedReading == null && mBook.finishedReading == null
    }



    HorizontalScrollableComponent(addedBooks){
         navController.navigate(ReaderScreens.UpdateScreen.name +"/$it")

    }
    
    

}

@Composable
fun HorizontalScrollableComponent(listOfBooks: List<MBook>,
                                  viewModel: HomeScreenViewModel = hiltViewModel(),
                                  onCardPressed: (String) -> Unit) {
    val scrollState = rememberScrollState()
    
    Row(modifier = Modifier
        .fillMaxWidth()
        .heightIn(280.dp)
        .horizontalScroll(scrollState)) {
        if (viewModel.data.value.loading == true) {
            LinearProgressIndicator()

        }else {
            if (listOfBooks.isNullOrEmpty()){
                 Surface(modifier = Modifier.padding(23.dp)) {
                      Text(text = "No books found. Add a Book",
                          style = TextStyle(
                              color = Color.Red.copy(alpha = 0.4f),
                              fontWeight = FontWeight.Bold,
                              fontSize = 14.sp
                                           )
                          )

                 }
            }else {
                for (book in listOfBooks) {
                    ListCard(book) {
                        onCardPressed(book.googleBookId.toString())

                    }
                }
            }

        }



    }
    

}


@Composable
fun ReadingRightNowArea(listOfBooks: List<MBook>,
                        navController: NavController) {
    //Filter books by reading now
    val readingNowList = listOfBooks.filter { mBook ->
        mBook.startedReading != null && mBook.finishedReading == null
    }

    HorizontalScrollableComponent(readingNowList){
        Log.d("TAG", "BoolListArea: $it")
         navController.navigate(ReaderScreens.UpdateScreen.name + "/$it")
    }



}

