package com.teledrive.pro
import android.content.Context
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
val Context.dataStore by preferencesDataStore("teledrive_pro_accounts")
class MultiAccountRepository(private val ctx: Context) {
    private val KEY = stringPreferencesKey("accounts")
    val accounts: Flow<List<AccountSession>> = ctx.dataStore.data.map { Json.decodeFromString(it[KEY] ?: "[]") }
    suspend fun add(a: AccountSession) { ctx.dataStore.edit { val l = Json.decodeFromString<List<AccountSession>>(it[KEY] ?: "[]").toMutableList(); l.removeAll { x -> x.id == a.id }; l.add(a); it[KEY] = Json.encodeToString(l) } }
    suspend fun setActive(id: String) { ctx.dataStore.edit { val l = Json.decodeFromString<List<AccountSession>>(it[KEY] ?: "[]").map { x -> x.copy(isActive = x.id == id) }; it[KEY] = Json.encodeToString(l) } }
    suspend fun remove(id: String) { ctx.dataStore.edit { val l = Json.decodeFromString<List<AccountSession>>(it[KEY] ?: "[]").filter { x -> x.id != id }; it[KEY] = Json.encodeToString(l) } }
}
