package com.example.pertemuan5

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

@Composable
fun FormMahasiswa(modifier: Modifier = Modifier) {
    var namaDepan by remember { mutableStateOf("") }
    var namaBelakang by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var tglLahir by remember { mutableStateOf("") }
    var experience by remember { mutableStateOf("") }
    var showMenu by remember { mutableStateOf(false) }
    var skillAndroid by remember { mutableStateOf(false) }
    var skillUiux by remember { mutableStateOf(false) }
    var skillWeb by remember { mutableStateOf(false) }

    val listExperience = listOf(
        stringResource(R.string.exp_beginner),
        stringResource(R.string.exp_intermediate),
        stringResource(R.string.exp_expert)
    )
    val fieldShape = RoundedCornerShape(dimensionResource(R.dimen.field_corner))

    val formValid = namaDepan.isNotBlank() && namaBelakang.isNotBlank() &&
            email.isNotBlank() && tglLahir.isNotBlank() && experience.isNotBlank() &&
            (skillAndroid || skillUiux || skillWeb)

    Box(modifier = modifier.fillMaxSize()) {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            Image(
                painter = painterResource(id = R.drawable.photo),
                contentDescription = stringResource(R.string.cd_background),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(dimensionResource(R.dimen.image_height))
                    .padding(innerPadding)
            )
        }
        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .background(
                    color = colorResource(R.color.white),
                    shape = RoundedCornerShape(
                        topStart = dimensionResource(R.dimen.card_corner),
                        topEnd = dimensionResource(R.dimen.card_corner)
                    )
                )
                .padding(dimensionResource(R.dimen.card_padding))
        ) {
            Text(
                text = stringResource(R.string.form_title),
                color = colorResource(R.color.purple_title),
                fontSize = dimensionResource(R.dimen.text_title).value.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
            Text(
                    text = stringResource(R.string.form_subtitle),
            color = colorResource(R.color.gray_text),
            fontSize = dimensionResource(R.dimen.text_subtitle).value.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = dimensionResource(R.dimen.space_small))
            )
            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.space_medium)))

            Row(horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.space_small))) {
                OutlinedTextField(
                    value = namaDepan,
                    onValueChange = { namaDepan = it },
                    label = { Text(stringResource(R.string.label_first_name)) },
                    singleLine = true,
                    shape = fieldShape,
                    modifier = Modifier.weight(1f)
                )
                OutlinedTextField(
                    value = namaBelakang,
                    onValueChange = { namaBelakang = it },
                    label = { Text(stringResource(R.string.label_last_name)) },
                    singleLine = true,
                    shape = fieldShape,
                    modifier = Modifier.weight(1f)
                )
            }
            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.space_medium)))

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text(stringResource(R.string.label_email)) },
                singleLine = true,
                shape = fieldShape,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.space_medium)))

            Row(horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.space_small))) {
                OutlinedTextField(
                    value = tglLahir,
                    onValueChange = { tglLahir = it },
                    label = { Text(stringResource(R.string.label_birth_date)) },
                    singleLine = true,
                    trailingIcon = {
                        Icon(
                            imageVector = Icons.Default.DateRange,
                            contentDescription = stringResource(R.string.cd_calendar)
                        )
                    },
                    shape = fieldShape,
                    modifier = Modifier.weight(1f)
                )
                Box(modifier = Modifier.weight(1f)) {
                    OutlinedTextField(
                        value = experience,
                        onValueChange = {},
                        readOnly = true,
                        label = { Text(stringResource(R.string.label_experience)) },
                        trailingIcon = {
                            IconButton(onClick = { showMenu = true }) {
                                Icon(
                                    imageVector = Icons.Default.ArrowDropDown,
                                    contentDescription = stringResource(R.string.cd_dropdown)
                                )
                            }
                        },
                        shape = fieldShape,
                        modifier = Modifier.fillMaxWidth()
                    )
                    DropdownMenu(
                        expanded = showMenu,
                        onDismissRequest = { showMenu = false }
                    ) {
                        listExperience.forEach { item ->
                            DropdownMenuItem(
                                text = { Text(item) },
                                onClick = {
                                    experience = item
                                    showMenu = false
                                }
                            )
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.space_medium)))

            Text(
                text = stringResource(R.string.label_skill),
                fontSize = dimensionResource(R.dimen.text_label).value.sp,
                fontWeight = FontWeight.Bold
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            )