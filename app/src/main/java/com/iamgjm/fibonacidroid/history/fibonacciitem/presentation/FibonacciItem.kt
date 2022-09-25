package com.iamgjm.fibonacidroid.history.fibonacciitem.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.iamgjm.fibonacidroid.presentation.compose.FibonacciTheme
import com.iamgjm.fibonacidroid.presentation.compose.secondary
import com.iamgjm.fibonacidroid.presentation.compose.secondaryText

@Composable
fun FibonacciItem(
    data: FibonacciItemData
) {
    Box {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(24.dp)
                .background(FibonacciTheme.colors.toolbar)
        ) {

            Column(modifier = Modifier.weight(3f)) {
                Row(Modifier.fillMaxWidth()) {
                    Row(
                        Modifier.weight(4f)
                    ) {
                        Text(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxWidth()
                                .padding(8.dp),
                            text = if (data.sequence.size > 5) {
                                data.sequence[data.sequence.size - 6].toString() + ".."
                            } else "",
                            textAlign = TextAlign.Center
                        )

                        Text(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxWidth()
                                .padding(8.dp),
                            text = if (data.sequence.size > 4) {
                                data.sequence[data.sequence.size - 5].toString() + ".."
                            } else "",
                            textAlign = TextAlign.Center
                        )

                        Text(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxWidth()
                                .padding(8.dp),
                            text = if (data.sequence.size > 3) {
                                data.sequence[data.sequence.size - 4].toString() + ".."
                            } else "",
                            textAlign = TextAlign.Center
                        )

                        Text(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxWidth()
                                .padding(8.dp),
                            text = if (data.sequence.size > 2) {
                                data.sequence[data.sequence.size - 3].toString() + ".."
                            } else "",
                            textAlign = TextAlign.Center
                        )

                        Text(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxWidth()
                                .padding(8.dp),
                            text = if (data.sequence.size > 1) {
                                data.sequence[data.sequence.size - 2].toString() + ".."
                            } else "",
                            textAlign = TextAlign.Center
                        )
                    }
                }

                Spacer(modifier = Modifier.size(16.dp))

                Text(
                    text = data.date,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 16.dp, bottom = 16.dp),
                    textAlign = TextAlign.End,
                    color = secondaryText
                )
            }

            Box(
                Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .padding(12.dp)
                    .background(secondary)
            ) {
                Text(
                    text = "  " + data.sequence[data.sequence.size - 1].toString(),
                    style = MaterialTheme.typography.h3,
                    textAlign = TextAlign.Center,
                )
            }
        }
    }
}

data class FibonacciItemData(
    val sequence: List<Int>,
    val date: String
)