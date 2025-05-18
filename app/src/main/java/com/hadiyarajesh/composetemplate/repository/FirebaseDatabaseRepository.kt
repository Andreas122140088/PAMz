package com.hadiyarajesh.composetemplate.repository

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.tasks.await

class FirebaseDatabaseRepository {

    private val database: FirebaseDatabase = FirebaseDatabase.getInstance()
    private val rootRef: DatabaseReference = database.reference

    // Example: Write data to a specified path
    suspend fun writeData(path: String, data: Any) {
        rootRef.child(path).setValue(data).await()
    }

    // Example: Read data from a specified path
    suspend fun <T> readData(path: String, clazz: Class<T>): T? {
        val snapshot = rootRef.child(path).get().await()
        return snapshot.getValue(clazz)
    }
}
