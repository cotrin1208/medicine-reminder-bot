package io.github.cotrin1208.service

import com.google.cloud.datastore.Key
import io.github.cotrin1208.model.message.Action
import io.github.cotrin1208.model.message.Message
import io.github.cotrin1208.model.message.PushMessage
import io.github.cotrin1208.model.message.TemplateObject
import io.github.cotrin1208.repository.IDatastoreRepository
import io.github.cotrin1208.repository.ILineApiRepository
import io.github.cotrin1208.util.ActionData
import io.github.cotrin1208.util.KindName
import io.github.cotrin1208.util.PropertyName

class MessageService(
    private val datastoreRepository: IDatastoreRepository,
    private val lineApiRepository: ILineApiRepository,
) : IMessageService {
    override suspend fun sendFridayMorningReminder() {
        val userList = datastoreRepository.queryEntities(KindName.USER_KIND)
        for (user in userList) {
            sendReminder(
                user.getString(PropertyName.USER_ID),
                "金曜日朝のお薬リマインダー",
                ActionData.FRIDAY_MORNING_RESPONDED
            )
            setFridayMorningFlag(user.key)
        }
    }

    override suspend fun sendFridayEveningReminder() {
        val userList = datastoreRepository.queryEntities(KindName.USER_KIND)
        for (user in userList) {
            sendReminder(
                user.getString(PropertyName.USER_ID),
                "金曜日夜のお薬リマインダー",
                ActionData.FRIDAY_EVENING_RESPONDED
            )
            setFridayEveningFlag(user.key)
        }
    }

    override suspend fun sendSundayMorningReminder() {
        val userList = datastoreRepository.queryEntities(KindName.USER_KIND)
        for (user in userList) {
            sendReminder(
                user.getString(PropertyName.USER_ID),
                "日曜日朝のお薬リマインダー",
                ActionData.SUNDAY_MORNING_RESPONDED
            )
            setSundayMorningFlag(user.key)
        }
    }

    override suspend fun sendFridayMorningReminderAgain() {
        val userList = datastoreRepository.queryEntities(KindName.USER_KIND)
        for (user in userList) {
            if (user.getBoolean(PropertyName.FRIDAY_MORNING_RESPONDED)) continue
            sendReminder(
                user.getString(PropertyName.USER_ID),
                "金曜日朝のお薬リマインダー",
                ActionData.FRIDAY_MORNING_RESPONDED
            )
        }
    }

    override suspend fun sendFridayEveningReminderAgain() {
        val userList = datastoreRepository.queryEntities(KindName.USER_KIND)
        for (user in userList) {
            if (user.getBoolean(PropertyName.FRIDAY_EVENING_RESPONDED)) continue
            sendReminder(
                user.getString(PropertyName.USER_ID),
                "金曜日夜のお薬リマインダー",
                ActionData.FRIDAY_EVENING_RESPONDED
            )
        }
    }

    override suspend fun sendSundayMorningReminderAgain() {
        val userList = datastoreRepository.queryEntities(KindName.USER_KIND)
        for (user in userList) {
            if (user.getBoolean(PropertyName.SUNDAY_MORNING_RESPONDED)) continue
            sendReminder(
                user.getString(PropertyName.USER_ID),
                "日曜日朝のお薬リマインダー",
                ActionData.SUNDAY_MORNING_RESPONDED
            )
        }
    }

    private fun setFridayMorningFlag(key: Key) {
        datastoreRepository.updateParameters(key) {
            set(PropertyName.FRIDAY_MORNING_RESPONDED, false)
        }
    }

    private fun setFridayEveningFlag(key: Key) {
        datastoreRepository.updateParameters(key) {
            set(PropertyName.FRIDAY_EVENING_RESPONDED, false)
        }
    }

    private fun setSundayMorningFlag(key: Key) {
        datastoreRepository.updateParameters(key) {
            set(PropertyName.SUNDAY_MORNING_RESPONDED, false)
        }
    }

    private fun createConfirmTemplate(title: String, data: String): Message.Template {
        return Message.Template(
            altText = title,
            template = TemplateObject.Confirm(
                text = title,
                actions = listOf(
                    Action.PostBack(
                        label = "スキップ",
                        data = data,
                    ),
                    Action.PostBack(
                        label = "飲んだ",
                        data = data,
                    )
                )
            )
        )
    }

    private fun createPushMessage(to: String, message: Message): PushMessage {
        return PushMessage(to, listOf(message))
    }

    private suspend fun sendReminder(userId: String, title: String, data: String) {
        val message = createPushMessage(
            userId,
            createConfirmTemplate(title, data)
        )
        lineApiRepository.sendPushMessage(message)
    }
}
