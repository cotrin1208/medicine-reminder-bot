package io.github.cotrin1208.model.message

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
sealed interface TemplateObject {
    @Serializable
    @SerialName("confirm")
    data class Confirm(
        val text: String,
        val actions: List<Action>,
    ) : TemplateObject
}
