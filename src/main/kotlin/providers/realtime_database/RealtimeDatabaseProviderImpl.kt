package providers.realtime_database

import com.google.firebase.FirebaseApp
import com.google.firebase.database.*
import com.google.firebase.database.utilities.encoding.CustomClassMapper
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

    override suspend fun <T : Any> getMapOfDocuments(type: Class<T>): Map<String, T> = suspendCoroutine { cont ->
        db.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot?) {
                snapshot?.let { dataSnapshot ->
                    val map = mutableMapOf<String, T>()
                    (dataSnapshot.value as Map<*, *>).forEach { (key, value) ->
                        map[key.toString()] = CustomClassMapper.convertToCustomClass(value, type)
                    }
                    cont.resumeWith(Result.success(map))
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