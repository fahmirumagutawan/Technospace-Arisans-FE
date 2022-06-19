package com.fahgutawan.arisans.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fahgutawan.arisans.R
import com.fahgutawan.arisans.myViewModel
import com.fahgutawan.arisans.ui.theme.Typography
import com.fahgutawan.arisans.util.MyTopBar
import com.tahutelor.arisans.ui.theme.*

@Composable
fun AddArisanPage() {
    AddContent()
    AddTopBar()
}

@Composable
fun AddContent() {
    val height = LocalConfiguration.current.screenHeightDp
    val scaledHeight = height / 10
    var scrollState = rememberScrollState()

    Surface(modifier = Modifier.fillMaxSize(), color = White) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .scrollable(state = scrollState, orientation = Orientation.Vertical)
                .padding(start = 16.dp, end = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            //To prevent some compose rendered behind our top bar
            Spacer(modifier = Modifier.height((scaledHeight + 16).dp))

            //Field stuff
            //Nama kelompok arisan
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp),
                textAlign = TextAlign.Start,
                text = "Nama Kelompok Arisan",
                color = Color.Black,
                style = Typography.body2
            )
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = myViewModel.addNama.value,
                onValueChange = {
                    myViewModel.addNama.value = it
                },
                shape = RoundedCornerShape(CornerSize(14.dp)),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = OrangeDark,
                    unfocusedBorderColor = GrayMid,
                    textColor = Color.Black,
                    disabledTextColor = Color.Black,
                    backgroundColor = GrayLight,
                    placeholderColor = GrayMid
                ),
                placeholder = {
                    Text(
                        text = "Masukkan nama kelompok arisan",
                        style = Typography.subtitle2
                    )
                }
            )

            //Jumlah anggota
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp),
                textAlign = TextAlign.Start,
                text = "Jumlah Anggota",
                color = Color.Black,
                style = Typography.body2
            )

            //USED FOR DROPDOWN
            val expanded = remember { mutableStateOf(false) }
            var expandIcon = R.drawable.ic_expand_open
            if (expanded.value) expandIcon = R.drawable.ic_expand_close else expandIcon =
                R.drawable.ic_expand_open
            val jumlah = listOf(2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15)
            val width = LocalConfiguration.current.screenWidthDp
            Column(modifier = Modifier.fillMaxWidth()) {
                OutlinedTextField(
                    readOnly = true,
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    value = myViewModel.addJumlah.value.toString(),
                    onValueChange = { myViewModel.addJumlah.value.toString() },
                    shape = RoundedCornerShape(CornerSize(14.dp)),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = OrangeDark,
                        unfocusedBorderColor = GrayMid,
                        textColor = Color.Black,
                        disabledTextColor = Color.Black,
                        backgroundColor = GrayLight,
                        placeholderColor = GrayMid
                    ),
                    placeholder = {
                        Text(
                            text = "Masukkan jumlah anggota",
                            style = Typography.subtitle2
                        )
                    },
                    trailingIcon = {
                        Icon(
                            painter = painterResource(id = expandIcon), "contentDescription",
                            modifier = Modifier.clickable { expanded.value = !expanded.value },
                            tint = Color.Black
                        )
                    }
                )
                //Dropdown
                DropdownMenu(
                    modifier = Modifier
                        .width((width - 32).dp)
                        .height(256.dp)
                        .background(color = GrayLight),
                    expanded = expanded.value,
                    onDismissRequest = { expanded.value = false }
                ) {
                    jumlah.forEach { item ->
                        DropdownMenuItem(onClick = {
                            myViewModel.addJumlah.value = item
                            expanded.value = false
                        }) {
                            Text(text = item.toString(), color = GrayDark)
                        }
                    }
                }
            }

            //NOMINAL
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp),
                textAlign = TextAlign.Start,
                text = "Nominal Biaya Arisan",
                color = Color.Black,
                style = Typography.body2
            )
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                value = myViewModel.addNominal.value,
                onValueChange = {
                    myViewModel.addNominal.value = it
                },
                shape = RoundedCornerShape(CornerSize(14.dp)),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = OrangeDark,
                    unfocusedBorderColor = GrayMid,
                    textColor = Color.Black,
                    disabledTextColor = Color.Black,
                    backgroundColor = GrayLight,
                    placeholderColor = GrayMid
                ),
                leadingIcon = {
                    Text(text = "Rp", style = Typography.h2, fontSize = 16.sp, color = Color.Black)
                },
                placeholder = {
                    Text(
                        text = "Masukkan nominal biaya arisan",
                        style = Typography.subtitle2
                    )
                }
            )

            //STATUS ARISAN
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp),
                textAlign = TextAlign.Start,
                text = "Jumlah Anggota",
                color = Color.Black,
                style = Typography.body2
            )

            //USED FOR DROPDOWN
            val status = listOf("Publik", "Privat")
            val isStatusExpanded = remember {
                mutableStateOf(false)
            }
            var statusExpandIcon = R.drawable.ic_expand_open
            if (isStatusExpanded.value) statusExpandIcon =
                R.drawable.ic_expand_close else statusExpandIcon =
                R.drawable.ic_expand_open
            Column(modifier = Modifier.fillMaxWidth()) {
                OutlinedTextField(
                    readOnly = true,
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    value = myViewModel.addStatus.value,
                    onValueChange = { myViewModel.addStatus.value },
                    shape = RoundedCornerShape(CornerSize(14.dp)),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = OrangeDark,
                        unfocusedBorderColor = GrayMid,
                        textColor = Color.Black,
                        disabledTextColor = Color.Black,
                        backgroundColor = GrayLight,
                        placeholderColor = GrayMid
                    ),
                    placeholder = {
                        Text(
                            text = "Masukkan status arisan",
                            style = Typography.subtitle2
                        )
                    },
                    trailingIcon = {
                        Icon(
                            painter = painterResource(id = statusExpandIcon), "contentDescription",
                            modifier = Modifier.clickable {
                                isStatusExpanded.value = !isStatusExpanded.value
                            },
                            tint = Color.Black
                        )
                    }
                )
                //Dropdown
                DropdownMenu(
                    modifier = Modifier
                        .width((width - 32).dp)
                        .background(color = GrayLight),
                    expanded = isStatusExpanded.value,
                    onDismissRequest = { expanded.value = false }
                ) {
                    status.forEach { item ->
                        DropdownMenuItem(onClick = {
                            myViewModel.addStatus.value = item
                            isStatusExpanded.value = false
                        }) {
                            Text(text = item, color = GrayDark)
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(32.dp))
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp),
                textAlign = TextAlign.Start,
                text = "Pembuatan",
                color = Color.Black,
                style = Typography.body2,
                fontSize = 24.sp
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp),
                textAlign = TextAlign.Start,
                text = "Pembayaran Pembuatan",
                color = Color.Black,
                style = Typography.body1
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 8.dp),
                    textAlign = TextAlign.Start,
                    text = "Kelompok Arisan Anggota ${myViewModel.addJumlah} Orang\nTotal Tagihan",
                    color = Color.Black,
                    style = Typography.body1
                )

                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 8.dp),
                    textAlign = TextAlign.Start,
                    text = "Rp ${myViewModel.addNominal}",
                    color = OrangeDark,
                    style = Typography.body1
                )
            }
        }
    }
}

@Composable
fun AddTopBar() {
    MyTopBar {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = {

                }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_registernext_back),
                    contentDescription = "Back",
                    tint = GrayDark
                )
            }

            Text(
                text = "Buat Kelompok Arisan",
                style = Typography.h1,
                textAlign = TextAlign.Center,
                color = GrayDark
            )
        }
    }
}