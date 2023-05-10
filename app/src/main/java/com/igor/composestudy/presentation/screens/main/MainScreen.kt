package com.igor.composestudy.presentation.screens.main

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.igor.composestudy.R
import com.igor.composestudy.presentation.model.CharacterStatus
import com.igor.composestudy.presentation.model.CharacterUiModel
import com.igor.composestudy.presentation.ui.theme.ComposeStudyTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun MainScreen(
    mainViewModel: MainViewModel = koinViewModel()
) {
    ComposeStudyTheme {
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            val characters by mainViewModel.characterList.observeAsState()

            LaunchedEffect(key1 = Unit) {
                mainViewModel.getCharacterList()
            }

            characters?.let { CharacterList(it) }
        }
    }
}

@Composable
fun CharacterList(characters: List<CharacterUiModel>) {
    LazyColumn {
        items(characters) { character ->
            CharacterCard(character = character)
        }
    }
}

@Composable
fun CharacterCard(character: CharacterUiModel) {
    Spacer(modifier = Modifier.height(16.dp))

    Card(
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
            .padding(8.dp),
        shape = RoundedCornerShape(16.dp),
    ) {
        Row {
            Spacer(modifier = Modifier.width(8.dp))

            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(character.imageUrl)
                    .placeholder(R.drawable.rick)
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .clip(RoundedCornerShape(16.dp))
                    .padding(top = 8.dp, bottom = 8.dp),
                alignment = Alignment.Center
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column(
                verticalArrangement = Arrangement.Center
            ) {
                val statusColor = character.status.toColor()
                CharacterInfo("Name", character.name)
                CharacterInfo("Gender", character.gender)
                CharacterInfo("Location", character.location)
                CharacterInfo("Status", character.status.toString(), statusColor)
            }
        }
    }
}

@Composable
fun CharacterInfo(key: String, value: String, color: Color = MaterialTheme.colorScheme.tertiary) {
    Row {
        Text(text = "$key: ", fontSize = 15.sp)
        Text(text = value, fontSize = 15.sp, color = color)
    }
}

@Preview(name = "Light Mode", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(name = "Dark Mode", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewMainScreen() {
    ComposeStudyTheme {
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            CharacterList(
                characters = listOf(
                    CharacterUiModel(
                        "Rick Sanchez",
                        "Male",
                        "Citadel of Ricks",
                        CharacterStatus.ALIVE,
                        "https://rickandmortyapi.com/api/character/avatar/1.jpeg"
                    )
                )
            )
        }
    }

}