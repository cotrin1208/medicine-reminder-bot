package io.github.cotrin1208.repository

import com.google.cloud.datastore.DatastoreOptions
import com.google.cloud.datastore.Entity
import com.google.cloud.datastore.Key
import com.google.cloud.datastore.Query

class DatastoreRepository : IDatastoreRepository {
    private val datastore by lazy {
        DatastoreOptions.getDefaultInstance().service
    }

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

    override fun queryEntities(kindName: String): List<Entity> {
        val query = Query.newEntityQueryBuilder().setKind(kindName).build()
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
