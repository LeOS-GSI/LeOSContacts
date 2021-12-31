package ch.abwesend.privatecontacts.infrastructure.room.contactdata

import ch.abwesend.privatecontacts.domain.model.contactdata.PhoneNumber
import java.util.UUID

fun PhoneNumber.toEntity(contactId: UUID): ContactDataEntity =
    ContactDataEntity(
        id = id,
        contactId = contactId,
        type = ContactDataType.PHONE_NUMBER,
        subType = type.toEntity(),
        sortOrder = sortOrder,
        isMain = isMain,
        value = value
    )
