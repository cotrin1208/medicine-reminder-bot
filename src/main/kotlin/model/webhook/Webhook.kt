package io.github.cotrin1208.model.webhook

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Webhook(
    val destination: String,
    val events: List<WebhookEvent>,
)

@Serializable
sealed interface WebhookEvent {
    val mode: String
    val timestamp: Long
    val source: Source
    val webhookEventId: String
    val deliveryContext: DeliveryContext

    @Serializable
    @SerialName("follow")
    data class Follow(
        override val mode: String,
        override val timestamp: Long,
        override val source: Source,
        override val webhookEventId: String,
        override val deliveryContext: DeliveryContext,
        val replyToken: String,
        val follow: FollowType,
    ) : WebhookEvent

    @Serializable
    @SerialName("postback")
    data class Postback(
        override val mode: String,
        override val timestamp: Long,
        override val source: Source,
        override val webhookEventId: String,
        override val deliveryContext: DeliveryContext,
        val replyToken: String,
        val postback: PostbackObject,
    ) : WebhookEvent
}

@Serializable
data class DeliveryContext(
    val isRedelivery: Boolean,
)

@Serializable
data class FollowType(
    val isUnblocked: Boolean,
)

@Serializable
data class PostbackObject(
    val data: String,
    val params: PostbackParams? = null,
)

@Serializable
sealed interface PostbackParams {
    @Serializable
    @SerialName("date_select")
    data class DateSelect(
        val date: String? = null,
        val time: String? = null,
        val datetime: String? = null,
    ) : PostbackParams
}
