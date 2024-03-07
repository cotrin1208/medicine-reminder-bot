package io.github.cotrin1208.repository

import com.google.cloud.datastore.*
import io.github.cotrin1208.util.PropertyName

class DatastoreRepository(private val datastore: Datastore) : IDatastoreRepository {
    override fun createKey(kindName: String, keyName: String): Key {
        return datastore.newKeyFactory().setKind(kindName).newKey(keyName)
    }

    override fun createEntity(key: Key, func: Entity.Builder.() -> Unit): Entity {
        val entity = Entity.newBuilder(key).apply(func).build()
        datastore.put(entity)
        return entity
    }

    override fun readEntity(key: Key): Entity? {
        return datastore.get(key)
    }

    override fun queryEntitiesInKind(kindName: String): List<Entity> {
        val query = Query.newEntityQueryBuilder().setKind(kindName).build()
        return datastore.run(query).asSequence().toList()
    }

    override fun queryEntitiesWithPropertyName(kindName: String, propertyName: String, value: String): List<Entity> {
        val query = Query.newEntityQueryBuilder().apply {
            setKind(kindName)
            setFilter(StructuredQuery.PropertyFilter.eq(PropertyName.USER_ID, value))
        }.build()
        return datastore.run(query).asSequence().toList()
    }

    override fun updateParameters(key: Key, func: Entity.Builder.() -> Unit): Entity? {
        val oldEntity = readEntity(key)
        val newEntity = Entity.newBuilder(key).apply {
            oldEntity?.let {
                oldEntity.properties.forEach { (key, value) ->
                    set(key, value)
                }
            }
        }.apply(func).build()
        datastore.update(newEntity)
        return newEntity
    }

    override fun updateEntity(key: Key, func: Entity.Builder.() -> Unit): Entity? {
        val entity = Entity.newBuilder(key).apply(func).build()
        datastore.update(entity)
        return entity
    }

    override fun deleteEntity(key: Key) {
        datastore.delete(key)
    }
}
