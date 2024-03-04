package io.github.cotrin1208.plugin.routing

import io.github.cotrin1208.service.IMessageService
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Route.pushMessageRoute() {
    val messageService: IMessageService by inject()

    route("notify") {
        post("friday-morning") {
            messageService.sendFridayMorningReminder()
        }
        post("friday-evening") {
            messageService.sendFridayEveningReminder()
        }
        post("sunday-morning") {
            messageService.sendSundayMorningReminder()
        }
    }
    route("check") {
        post("friday-morning") {
            messageService.sendFridayMorningReminderAgain()
        }
        post("friday-evening") {
            messageService.sendFridayEveningReminderAgain()
        }
        post("sunday-morning") {
            messageService.sendSundayMorningReminderAgain()
        }
    }
}
