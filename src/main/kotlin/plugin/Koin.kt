package io.github.cotrin1208.plugin

import com.google.cloud.datastore.DatastoreOptions
import io.github.cotrin1208.repository.DatastoreRepository
import io.github.cotrin1208.repository.IDatastoreRepository
import io.github.cotrin1208.repository.ILineApiRepository
import io.github.cotrin1208.repository.LineApiRepository
import io.github.cotrin1208.service.IMessageService
import io.github.cotrin1208.service.IWebhookService
import io.github.cotrin1208.service.MessageService
import io.github.cotrin1208.service.WebhookService
import io.ktor.server.application.*
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import org.koin.ktor.plugin.Koin

fun Application.configureKoin() {
    install(Koin) {
        modules(
            repositoryModule,
            serviceModule
        )
    }
}

val repositoryModule = module {
    single { DatastoreOptions.getDefaultInstance().service }
    singleOf(::DatastoreRepository) bind IDatastoreRepository::class
    singleOf(::LineApiRepository) bind ILineApiRepository::class
}

val serviceModule = module {
    singleOf(::WebhookService) bind IWebhookService::class
    singleOf(::MessageService) bind IMessageService::class
}
