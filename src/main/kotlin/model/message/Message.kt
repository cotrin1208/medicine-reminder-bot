package io.github.cotrin1208.model.message

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
sealed interface Message {
    val quickReply: List<QuickReplyItem>?

    @Serializable
    @SerialName("text")
    data class Text(
        override val quickReply: List<QuickReplyItem>? = null,
        val text: String,
        val emojis: List<Emoji>? = null,
    ) : Message

    @Serializable
    @SerialName("sticker")
    data class Sticker(
        override val quickReply: List<QuickReplyItem>? = null,
        val packageId: String,
        val stickerId: String,
        val quoteToken: String? = null,
    ) : Message

    @Serializable
    @SerialName("template")
    data class Template(
        override val quickReply: List<QuickReplyItem>? = null,
        val altText: String,
        val template: TemplateObject,
    ) : Message
}


