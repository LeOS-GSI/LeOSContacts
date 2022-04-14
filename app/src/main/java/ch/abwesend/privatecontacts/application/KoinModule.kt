/*
 * Private Contacts
 * Copyright (c) 2022.
 * Florian Gubler
 */

package ch.abwesend.privatecontacts.application

import ch.abwesend.privatecontacts.domain.lib.coroutine.ApplicationScope
import ch.abwesend.privatecontacts.domain.lib.coroutine.Dispatchers
import ch.abwesend.privatecontacts.domain.lib.coroutine.IDispatchers
import ch.abwesend.privatecontacts.domain.lib.logging.ILoggerFactory
import ch.abwesend.privatecontacts.domain.repository.ContactPagerFactory
import ch.abwesend.privatecontacts.domain.repository.IContactRepository
import ch.abwesend.privatecontacts.domain.service.ContactLoadService
import ch.abwesend.privatecontacts.domain.service.ContactSaveService
import ch.abwesend.privatecontacts.domain.service.ContactValidationService
import ch.abwesend.privatecontacts.domain.service.EasterEggService
import ch.abwesend.privatecontacts.domain.service.FullTextSearchService
import ch.abwesend.privatecontacts.domain.service.IncomingCallService
import ch.abwesend.privatecontacts.domain.settings.SettingsProvider
import ch.abwesend.privatecontacts.infrastructure.calldetection.NotificationRepository
import ch.abwesend.privatecontacts.infrastructure.logging.LoggerFactory
import ch.abwesend.privatecontacts.infrastructure.paging.ContactPagingSource
import ch.abwesend.privatecontacts.infrastructure.repository.ContactDataRepository
import ch.abwesend.privatecontacts.infrastructure.repository.ContactRepository
import ch.abwesend.privatecontacts.infrastructure.room.database.DatabaseFactory
import ch.abwesend.privatecontacts.infrastructure.room.database.IDatabaseFactory
import ch.abwesend.privatecontacts.infrastructure.settings.DataStoreSettingProvider
import ch.abwesend.privatecontacts.view.permission.PermissionHelper
import ch.abwesend.privatecontacts.view.routing.AppRouter
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

internal val koinModule = module {
    single { ContactLoadService() }
    single { ContactValidationService() }
    single { ContactSaveService() }
    single { FullTextSearchService() }
    single { PermissionHelper() }
    single { IncomingCallService() }
    single { EasterEggService() }

    single<IContactRepository> { ContactRepository() }
    single { ContactDataRepository() }
    single<ContactPagerFactory> { ContactPagingSource.Companion }

    single { NotificationRepository() }

    single<SettingsProvider> { DataStoreSettingProvider(androidContext()) }

    single<ILoggerFactory> { LoggerFactory() }
    single<IDatabaseFactory> { DatabaseFactory }
    single<IDispatchers> { Dispatchers }

    single { ApplicationScope() }
    factory { AppRouter(get()) }

    single {
        val context = androidContext()
        val factory: IDatabaseFactory = get()
        factory.createDatabase(context)
    }
}
