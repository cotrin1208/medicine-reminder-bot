package io.github.cotrin1208.model.message

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
sealed interface Action {
    @Serializable
    @SerialName("postback")
    data class PostBack(
        val label: String,
        val data: String,
        val displayText: String? = null,
        val inputOption: ActionInputOption? = null,
        val fillInText: String? = null,
    ) : Action
}

@Serializable
sealed interface ActionInputOption {
    @Serializable
    @SerialName("closeRichMenu")
    data object CloseRichMenu : ActionInputOption

    @Serializable
    @SerialName("openRichMenu")
    data object OpenRichMenu : ActionInputOption

    @Serializable
    @SerialName("openKeyboard")
    data object OpenKeyboard : ActionInputOption

    @Serializable
    @SerialName("openVoice")
    data object OpenVoice : ActionInputOption
}
