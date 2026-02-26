package com.example.vkrustore.data.apps.repository

import com.example.vkrustore.data.apps.models.ApplicationBusiness
import java.util.UUID
import kotlin.random.Random

internal class AppsRepositoryImpl : AppsRepository {
    override fun findById(appId: String): ApplicationBusiness? = apps.find { it.id == appId }

    override fun getByCategory(category: String): List<ApplicationBusiness> = apps.filter {
        it.category == category
    }

    override fun getAll(): List<ApplicationBusiness> = apps

    companion object {
        private val apps = listOf(
            ApplicationBusiness(
                id = UUID.randomUUID().toString(),
                name = "Ninjagram",
                category = "some category",
                rating = Random.nextFloat(),
                ratingCount = Random.nextInt(100, 100_000_000),
                description = "some description",
                apkSourceUrl = "https://fs286.tbx.su/files10/2214012_bc24de/ninjagram-all-abi-12.0.1.1.apk",
                appIconUrl = "https://trashbox.ru/apk_icons/topic_200596_320.png?1760633467",
                bannerImageUrl = null,
            ),
            ApplicationBusiness(
                id = UUID.randomUUID().toString(),
                name = "VK Мессенджер",
                category = "some category",
                rating = Random.nextFloat(),
                ratingCount = Random.nextInt(100, 100_000_000),
                description = "some description",
                apkSourceUrl = "https://fs366.tbx.su/files10/2436220_0035cc/com.vk.im_33096_rs.apk",
                appIconUrl = "https://trashbox.ru/apk_icons/topic_121662_256.png?1771263620",
                bannerImageUrl = null,
            ),
            ApplicationBusiness(
                id = UUID.randomUUID().toString(),
                name = "VK Видео",
                category = "some category",
                rating = Random.nextFloat(),
                ratingCount = Random.nextInt(100, 100_000_000),
                description = "some description",
                apkSourceUrl = "https://fs304.tbx.su/files10/2435762_c65af6/com.vk.vkvideo_38830_rs.apk",
                appIconUrl = "https://trashbox.ru/apk_icons/topic_161878_256.png?1771400482",
                bannerImageUrl = null,
            ),
            ApplicationBusiness(
                id = UUID.randomUUID().toString(),
                name = "ВКонтакте",
                category = "some category",
                rating = Random.nextFloat(),
                ratingCount = Random.nextInt(100, 100_000_000),
                description = "some description",
                apkSourceUrl = "https://fs301.tbx.su/files10/2434566_4ca057/com.vkontakte.android_48214_rs.apk",
                appIconUrl = "https://trashbox.ru/apk_icons/topic_8069_256.png?1771422435",
                bannerImageUrl = null,
            ),
            ApplicationBusiness(
                id = UUID.randomUUID().toString(),
                name = "Автору",
                category = "some category",
                rating = Random.nextFloat(),
                ratingCount = Random.nextInt(100, 100_000_000),
                description = "some description",
                apkSourceUrl = "https://fs301.tbx.su/files10/2436262_6c2f96/ru.auto.ara_97686_rs.apk",
                appIconUrl = "https://trashbox.ru/apk_icons/topic_68043_256.png?1771368010",
                bannerImageUrl = null,
            ),
            ApplicationBusiness(
                id = UUID.randomUUID().toString(),
                name = "Яндекс Задания",
                category = "some category",
                rating = Random.nextFloat(),
                ratingCount = Random.nextInt(100, 100_000_000),
                description = "some description",
                apkSourceUrl = "https://fs383.tbx.su/files10/2436328_2a856b/com.yandex.tasks.androidapp_1230023292_rs.apk",
                appIconUrl = "https://trashbox.ru/apk_icons/topic_168505_256.png?1771397299",
                bannerImageUrl = null,
            ),
            ApplicationBusiness(
                id = UUID.randomUUID().toString(),
                name = "Алиса",
                category = "some category",
                rating = Random.nextFloat(),
                ratingCount = Random.nextInt(100, 100_000_000),
                description = "some description",
                apkSourceUrl = "https://fs301.tbx.su/files10/2433639_0963f0/com.yandex.aliceapp_260311305_rs.apk",
                appIconUrl = "https://trashbox.ru/apk_icons/topic_185080_256.png?1771425363",
                bannerImageUrl = null,
            ),
            ApplicationBusiness(
                id = UUID.randomUUID().toString(),
                name = "UTK.io для Minecraft",
                category = "some category",
                rating = Random.nextFloat(),
                ratingCount = Random.nextInt(100, 100_000_000),
                description = "some description",
                apkSourceUrl = "https://fs296.tbx.su/files10/1731054_f19fb6/io.utk.android_1.7.99634_9634.apk",
                appIconUrl = "https://trashbox.ru/apk_icons/1731054_192.png",
                bannerImageUrl = null,
            ),
            ApplicationBusiness(
                id = UUID.randomUUID().toString(),
                name = "Pinterest",
                category = "some category",
                rating = Random.nextFloat(),
                ratingCount = Random.nextInt(100, 100_000_000),
                description = "some description",
                apkSourceUrl = "https://fs389.tbx.su/files10/2436744_6b469a/com.pinterest-14058020.apk",
                appIconUrl = "https://trashbox.ru/apk_icons/topic_67045_256.png?1771754237",
                bannerImageUrl = null,
            ),
            ApplicationBusiness(
                id = "com.twinby",
                name = "Twinby",
                category = "some category",
                rating = Random.nextFloat(),
                ratingCount = Random.nextInt(100, 100_000_000),
                description = "some description",
                apkSourceUrl = "https://fs343.tbx.su/files10/1970016_025858/twinby_1.5.9.apk",
                appIconUrl = "https://trashbox.ru/apk_icons/topic_170290_256.png?1771587620",
                screens = listOf(
                    "https://trashbox.ru/ifiles2/1914796_c78f5e_screen-0.webp.png-orig.jpg/twinby-android-7.webp",
                    "https://trashbox.ru/ifiles2/1914797_c056f9_screen-1.webp.png-orig.jpg/twinby-android-8.webp",
                    "https://trashbox.ru/ifiles2/1914798_764ee0_screen-2.webp.png-orig.jpg/twinby-android-9.webp",
                ),
                bannerImageUrl = null,
            ),
            ApplicationBusiness(
                id = UUID.randomUUID().toString(),
                name = "Spotify",
                category = "some category",
                rating = Random.nextFloat(),
                ratingCount = Random.nextInt(100, 100_000_000),
                description = "some description",
                apkSourceUrl = "https://fs321.tbx.su/files10/2434696_8e0d2d/com.spotify.music-138161298.apk",
                appIconUrl = "https://trashbox.ru/apk_icons/topic_17376_256.png?1771799958",
                bannerImageUrl = null,
            ),
        )
    }
}