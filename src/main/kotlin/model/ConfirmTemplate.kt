package io.github.cotrin1208.model

import kotlinx.serialization.Serializable

@Serializable
data class ConfirmTemplate(
    val type: String,
    val altText: String,
    val template: Template,
)

@Serializable
data class Template(
    val type: String,
    val text: String,
    val actions: List<Action>,
)

@Serializable
data class Action(
    val type: String,
    val label: String,
    val text: String,
)

@Serializable
data class MessageRequest(
    val to: String,
    val messages: List<ConfirmTemplate>,
)
