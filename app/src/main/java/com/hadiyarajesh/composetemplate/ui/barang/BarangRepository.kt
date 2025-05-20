package com.hadiyarajesh.composetemplate.ui.barang

import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.hadiyarajesh.composetemplate.ui.barang.dummy.BarangLab
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.Flow

object BarangRepository {
    private val db = FirebaseDatabase.getInstance().reference.child("barang")

    suspend fun tambahBarang(barang: BarangLab) {
        db.push().setValue(barang).await()
    }

    fun listenBarangList(): Flow<List<BarangLab>> = callbackFlow {
        val listener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val list = snapshot.children.mapNotNull { it.getValue(BarangLab::class.java) }
                trySend(list)
            }
            override fun onCancelled(error: DatabaseError) {
                close(error.toException())
            }
        }
        db.addValueEventListener(listener)
        awaitClose { db.removeEventListener(listener) }
    }
}
