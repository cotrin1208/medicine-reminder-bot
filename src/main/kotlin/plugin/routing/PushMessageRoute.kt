package io.github.cotrin1208.plugin.routing

import io.github.cotrin1208.service.IMessageService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Route.pushMessageRoute() {
    val messageService: IMessageService by inject()

    route("notify") {
        post("friday-morning") {
            messageService.sendFridayMorningReminder()
            call.respond(HttpStatusCode.OK)
        }
        post("friday-evening") {
            messageService.sendFridayEveningReminder()
            call.respond(HttpStatusCode.OK)
        }
        post("sunday-morning") {
            messageService.sendSundayMorningReminder()
            call.respond(HttpStatusCode.OK)
        }
    }
    route("check") {
        post("friday-morning") {
            messageService.sendFridayMorningReminderAgain()
            call.respond(HttpStatusCode.OK)
        }
        post("friday-evening") {
            messageService.sendFridayEveningReminderAgain()
            call.respond(HttpStatusCode.OK)
        }
        post("sunday-morning") {
            messageService.sendSundayMorningReminderAgain()
            call.respond(HttpStatusCode.OK)
        }
    }
}
