package com.example.vkrustore.feature.onboarding.impl.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vkrustore.feature.onboarding.api.OnboardingAction
import com.example.vkrustore.uikit.R
import com.example.vkrustore.uikit.smallShape
import com.example.vkrustore.uikit.spacing16
import com.example.vkrustore.uikit.theme.VKRuStoreTheme

@Composable
fun Onboarding(
    onAction: (OnboardingAction) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Box(
            modifier = Modifier
                .weight(1f)
        ) {
            Image(
                painter = painterResource(R.drawable.app_icons),
                contentDescription = "App icons",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize(),
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.22f)
                    .align(Alignment.BottomCenter)
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(Color.White, MaterialTheme.colorScheme.primary)
                        )
                    )
            )
        }

        Column(
            modifier = Modifier
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = Modifier.size(36.dp),
                    painter = painterResource(R.drawable.rustore_app_white),
                    contentDescription = "RuStore icon",
                    tint = Color.Unspecified
                )

                Spacer(Modifier.width(6.dp))

                Text(
                    text = "RuStore",
                    color = MaterialTheme.colorScheme.onPrimary,
                    fontSize = 26.sp,
                    fontWeight = FontWeight.W500
                )
            }

            Spacer(Modifier.height(24.dp))

            CenterText(
                text = "Официальный магазин приложений для Android",
                fontSize = 20.sp
            )

            Spacer(Modifier.height(12.dp))

            CenterText(
                text = "Любимые приложения и игры уже здесь",
            )

            Spacer(Modifier.height(24.dp))

            Button(
                modifier = Modifier
                    .fillMaxWidth(),
                shape = RoundedCornerShape(smallShape),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                ),
                contentPadding = PaddingValues(
                    start = spacing16,
                    end = spacing16,
                    top = spacing16,
                    bottom = spacing16
                ),
                onClick = { onAction(OnboardingAction.Finish) },
            ) {
                Text(
                    text = "Продолжить",
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    fontSize = 16.sp
                )
            }

            Spacer(Modifier.height(12.dp))

            CenterText(
                text = "Нажимая «Продолжить», вы принимаете пользовательское соглашение RuStore",
                fontSize = 12.sp,
                lineHeight = 16.sp
            )

            Spacer(Modifier.height(24.dp))
        }
    }
}

@Composable
fun CenterText(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.onPrimary,
    fontSize: TextUnit = 16.sp,
    lineHeight: TextUnit = TextUnit.Unspecified
) {
    Text(
        modifier = modifier,
        text = text,
        color = color,
        fontSize = fontSize,
        textAlign = TextAlign.Center,
        lineHeight = lineHeight
    )
}

@Preview
@Composable
fun PreviewOnBoarding() {
    VKRuStoreTheme {
        Onboarding { }
    }
}