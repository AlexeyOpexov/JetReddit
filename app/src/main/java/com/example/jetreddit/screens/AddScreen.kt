/*
 * Copyright (c) 2021 Razeware LLC
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * Notwithstanding the foregoing, you may not use, copy, modify, merge, publish,
 * distribute, sublicense, create a derivative work, and/or sell copies of the
 * Software in any work that is designed, intended, or marketed for pedagogical or
 * instructional purposes related to programming, coding, application development,
 * or information technology.  Permission for such use, copying, modification,
 * merger, publication, distribution, sublicensing, creation of derivative works,
 * or sale is expressly withheld.
 *
 * This project and source code may use libraries or frameworks that are
 * released under various Open-Source licenses. Use of those libraries and
 * frameworks are governed by their own individual licenses.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.example.jetreddit.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetreddit.R
import com.example.jetreddit.domain.model.PostModel
import com.example.jetreddit.routing.JetRedditRouter
import com.example.jetreddit.routing.Screen
import com.example.jetreddit.viewmodel.MainViewModel

@Composable
fun AddScreen(viewModel: MainViewModel){
    val selectedCommunity: String by viewModel.selectedCommunity.observeAsState("")
    var post by remember { mutableStateOf(PostModel.EMPTY) }

    Column(
        modifier = Modifier.fillMaxSize(),
        content = {
            CommunityPicker(selectedCommunity)

            TitleTextField(post.title) { newTitle -> post = post.copy(title = newTitle) }

            BodyTextField(post.text) { newContent -> post = post.copy(text = newContent) }

            AddPostButton(selectedCommunity.isNotEmpty() && post.title.isNotEmpty()) {
                viewModel.savePost(post)
                JetRedditRouter.navigateTo(Screen.Home)
            }
        }
    )
}

@Composable
private fun CommunityPicker(selectedCommunity: String) {

    val selectedText =
        if (selectedCommunity.isEmpty()) stringResource(R.string.choose_community)
        else selectedCommunity

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(max = 240.dp)
            .padding(horizontal = 8.dp)
            .padding(top = 16.dp)
            .clickable {
                JetRedditRouter.navigateTo(Screen.ChooseCommunity)
            },
        content = {
            Image(
                bitmap = ImageBitmap.imageResource(id = R.drawable.subreddit_placeholder),
                contentDescription = stringResource(id = R.string.subreddits),
                modifier = Modifier
                    .size(24.dp)
                    .clip(CircleShape)
            )

            Text(
                text = selectedText,
                modifier = Modifier.padding(start = 8.dp)
            )
        }
    )
}

@Composable
private fun TitleTextField(text: String, onTextChange: (String) -> Unit) {
    val activeColor = MaterialTheme.colors.onSurface

    TextField(
        value = text,
        onValueChange = onTextChange,
        label = { Text(stringResource(R.string.title)) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = activeColor,
            focusedLabelColor = activeColor,
            cursorColor = activeColor,
            backgroundColor = MaterialTheme.colors.surface
        )
    )
}

@Composable
private fun BodyTextField(text: String, onTextChange: (String) -> Unit) {
    val activeColor = MaterialTheme.colors.onSurface

    TextField(
        value = text,
        onValueChange = onTextChange,
        label = { Text(stringResource(R.string.body_text)) },
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(max = 240.dp)
            .padding(horizontal = 8.dp)
            .padding(top = 16.dp),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = activeColor,
            focusedLabelColor = activeColor,
            cursorColor = activeColor,
            backgroundColor = MaterialTheme.colors.surface
        )
    )
}

@Composable
private fun AddPostButton(isEnabled: Boolean, onSaveClick: ()->Unit ) {
    Button(
        onClick = onSaveClick,
        enabled = isEnabled,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(max = 240.dp)
            .padding(horizontal = 8.dp)
            .padding(top = 16.dp),
        content = {
            Text(
                text = stringResource(R.string.save_post),
                color = MaterialTheme.colors.onSurface
            )
        }
    )
}


@Preview(showBackground = true)
@Composable
fun CommunityPickerPreview() {
    CommunityPicker(selectedCommunity = "Choose community")
}

@Preview(showBackground = true)
@Composable
fun TitleTextFieldPreview() {
    TitleTextField(text = "", onTextChange = {})
}







