package io.github.cotrin1208.util

import io.github.cotrin1208.model.message.Message

object Stickers {
    private val stickers = listOf(
        Message.Sticker(packageId = "789", stickerId = "10856"),
        Message.Sticker(packageId = "789", stickerId = "10858"),
        Message.Sticker(packageId = "789", stickerId = "10871"),
        Message.Sticker(packageId = "6136", stickerId = "10551378"),
        Message.Sticker(packageId = "6136", stickerId = "10551394"),
        Message.Sticker(packageId = "6136", stickerId = "10551398"),
        Message.Sticker(packageId = "6325", stickerId = "10979904"),
        Message.Sticker(packageId = "6325", stickerId = "10979908"),
        Message.Sticker(packageId = "6325", stickerId = "10979913"),
        Message.Sticker(packageId = "6325", stickerId = "10979914"),
        Message.Sticker(packageId = "6325", stickerId = "10979918"),
        Message.Sticker(packageId = "6325", stickerId = "10979919"),
        Message.Sticker(packageId = "6325", stickerId = "10979924"),
        Message.Sticker(packageId = "8515", stickerId = "16581242"),
        Message.Sticker(packageId = "8515", stickerId = "16581245"),
        Message.Sticker(packageId = "8515", stickerId = "16581249"),
        Message.Sticker(packageId = "8515", stickerId = "16581254"),
        Message.Sticker(packageId = "8515", stickerId = "16581252"),
        Message.Sticker(packageId = "8515", stickerId = "16581265"),
        Message.Sticker(packageId = "8515", stickerId = "16581265"),
        Message.Sticker(packageId = "11537", stickerId = "52002735"),
        Message.Sticker(packageId = "11537", stickerId = "52002742"),
        Message.Sticker(packageId = "11537", stickerId = "52002743"),
        Message.Sticker(packageId = "11538", stickerId = "51626500"),
        Message.Sticker(packageId = "11538", stickerId = "51626501"),
        Message.Sticker(packageId = "11539", stickerId = "52114110"),
        Message.Sticker(packageId = "11539", stickerId = "52114111"),
        Message.Sticker(packageId = "11539", stickerId = "52114113"),
        Message.Sticker(packageId = "11539", stickerId = "52114117"),
        Message.Sticker(packageId = "11539", stickerId = "52114118"),
        Message.Sticker(packageId = "11539", stickerId = "52114123"),
    )

    fun random() = stickers.random()
}
