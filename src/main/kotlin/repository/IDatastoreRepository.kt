package io.github.cotrin1208.repository

import com.google.cloud.datastore.Entity
import com.google.cloud.datastore.Key

interface IDatastoreRepository {
    fun createKey(kindName: String, keyName: String): Key

    fun createEntity(key: Key, func: Entity.Builder.() -> Unit): Entity

    fun readEntity(key: Key): Entity?

    fun queryEntitiesInKind(kindName: String): List<Entity>

    fun queryEntitiesWithPropertyName(kindName: String, propertyName: String, value: String): List<Entity>

    fun updateParameters(key: Key, func: Entity.Builder.() -> Unit): Entity?

    fun updateEntity(key: Key, func: Entity.Builder.() -> Unit): Entity?

    fun deleteEntity(key: Key)
}
