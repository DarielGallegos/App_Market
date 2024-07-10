package storage

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.map
import model.dto.REQUEST.Credentials
import storage.src.KEY_PREFERENCE_STORAGE.Companion.KEY_ID
import storage.src.KEY_PREFERENCE_STORAGE.Companion.KEY_NAME
import storage.src.KEY_PREFERENCE_STORAGE.Companion.KEY_ROL
import storage.src.KEY_PREFERENCE_STORAGE.Companion.KEY_USERNAME

val Context.dataStore by preferencesDataStore(name = "USER_CREDENTIALS")

class StoragePreferences(private val context: Context) {

    companion object {
        @Volatile
        private var INSTANCE: StoragePreferences? = null

        fun getInstance(context: Context): StoragePreferences {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: StoragePreferences(context).also { INSTANCE = it }
            }
        }
    }

    fun getCredentiales() = context.dataStore.data.map { preferences ->
        Credentials(
            id = preferences[KEY_ID],
            nombre = preferences[KEY_NAME].orEmpty(),
            usuario = preferences[KEY_USERNAME].orEmpty(),
            rol = preferences[KEY_ROL].orEmpty()
        )
    }

    suspend fun saveCredentials(e: Credentials) {
        context.dataStore.edit { preferences ->
            e.id?.let { preferences[KEY_ID] = it }
            e.nombre?.let { preferences[KEY_NAME] = it }
            e.usuario?.let { preferences[KEY_USERNAME] = it }
            e.rol?.let { preferences[KEY_ROL] = it }
        }
    }

    suspend fun deleteCredentials() {
        context.dataStore.edit { preferences ->
            preferences.clear()
        }
    }
}