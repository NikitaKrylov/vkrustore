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
import androidx.compose.ui.layout.HorizontalAlignmentLine
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vkrustore.feature.onboarding.api.OnboardingAction
import com.example.vkrustore.uikit.R
import com.example.vkrustore.uikit.TextStyles
import com.example.vkrustore.uikit.buttonPaddingValues
import com.example.vkrustore.uikit.smallShape
import com.example.vkrustore.uikit.spacing12
import com.example.vkrustore.uikit.spacing16
import com.example.vkrustore.uikit.spacing24
import com.example.vkrustore.uikit.spacing48
import com.example.vkrustore.uikit.spacing64
import com.example.vkrustore.uikit.spacing8
import com.example.vkrustore.uikit.theme.VKRuStoreTheme

@Composable
internal fun Onboarding(
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
                painter = painterResource(R.drawable.app_icons_burst),
                contentDescription = "App icons",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize(),
            )
        }

        Column(
            modifier = Modifier
                .padding(
                    start = spacing24,
                    end = spacing24,
                    bottom = spacing48
                ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(spacing8)
            ) {
                Icon(
                    modifier = Modifier.size(36.dp),
                    painter = painterResource(R.drawable.rustore_app_white),
                    contentDescription = "RuStore icon",
                    tint = Color.Unspecified
                )

                Text(
                    text = "RuStore",
                    color = MaterialTheme.colorScheme.onPrimary,
                    style = TextStyles.TitleLarge
                )
            }

            Spacer(Modifier.height(spacing24))

            Text(
                text = "Официальный магазин приложений для Android",
                style = TextStyles.BodyLarge,
                color = MaterialTheme.colorScheme.onPrimary,
                textAlign = TextAlign.Center,
            )

            Spacer(Modifier.height(spacing12))

            Text(
                text = "Любимые приложения и игры уже здесь",
                style = TextStyles.BodyMedium,
                color = MaterialTheme.colorScheme.onPrimary,
                textAlign = TextAlign.Center
            )

            Spacer(Modifier.height(spacing24))

            Button(
                modifier = Modifier
                    .fillMaxWidth(),
                shape = RoundedCornerShape(smallShape),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                ),
                contentPadding = buttonPaddingValues,
                onClick = { onAction(OnboardingAction.Finish) },
            ) {
                Text(
                    text = "Продолжить",
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    style = TextStyles.LabelLarge
                )
            }

            Spacer(Modifier.height(spacing12))

            Text(
                text = "Нажимая «Продолжить», вы принимаете пользовательское соглашение RuStore",
                style = TextStyles.LabelSmall,
                color = MaterialTheme.colorScheme.onPrimary,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview
@Composable
fun PreviewOnBoarding() {
    VKRuStoreTheme {
        Onboarding { }
    }
}