package io.github.cotrin1208.service

interface IMessageService {
    suspend fun sendFridayMorningReminder()

    suspend fun sendFridayEveningReminder()

    suspend fun sendSundayMorningReminder()

    suspend fun sendFridayMorningReminderAgain()

    suspend fun sendFridayEveningReminderAgain()
    
    suspend fun sendSundayMorningReminderAgain()
}
