package providers.realtime_database

import com.google.firebase.FirebaseApp
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import providers.RealtimeDatabaseProvider
import kotlin.coroutines.suspendCoroutine

class RealtimeDatabaseProviderImpl(app: FirebaseApp) : RealtimeDatabaseProvider {
    private val db = FirebaseDatabase.getInstance(app).reference

    override suspend fun saveDocuments(document: Map<String, Any>) = suspendCoroutine<Unit> { cont ->
        db.setValue(document) { err, _ ->
            err?.let {
                cont.resumeWith(Result.failure(it.toException()))
            }
            cont.resumeWith(Result.success(Unit))
        }
    }

    override suspend fun clear() = suspendCoroutine<Unit> { cont ->
        db.setValue(null) { err, _ ->
            err?.let {
                cont.resumeWith(Result.failure(it.toException()))
            }
            cont.resumeWith(Result.success(Unit))
        }
    }

    override suspend fun <T : Any> getDocument(documentName: String, type: Class<T>): T = suspendCoroutine { cont ->
        db.child(documentName).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot?) {
                snapshot?.let {
                    cont.resumeWith(
                        Result.success(it.getValue(type))
                    )
                }
                cont.resumeWith(
                    Result.failure(Exception("Snapshot is null"))
                )
            }

            override fun onCancelled(error: DatabaseError?) {
                error?.let {
                    cont.resumeWith(
                        Result.failure(it.toException())
                    )
                }
                cont.resumeWith(
                    Result.failure(Exception("Function getDocument was cancelled"))
                )
            }
        })
    }

    override suspend fun getListNameOfDocuments(): List<String> = suspendCoroutine { cont ->
        db.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot?) {
                snapshot?.let { dataSnapshot ->
                    val listNameOfDocuments = (dataSnapshot.value as Map<*, *>).keys
                        .filterNotNull()
                        .map { key -> key.toString() }
                        .toList()
                    cont.resumeWith(
                        Result.success(listNameOfDocuments)
                    )
                }
                cont.resumeWith(
                    Result.failure(Exception("Snapshot is null"))
                )
            }

            override fun onCancelled(error: DatabaseError?) {
                error?.let {
                    cont.resumeWith(
                        Result.failure(it.toException())
                    )
                }
                cont.resumeWith(
                    Result.failure(Exception("Function getDocuments was cancelled"))
                )
            }
        })
    }

}