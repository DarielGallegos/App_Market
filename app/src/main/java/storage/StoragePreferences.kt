package storage

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.map
import model.Credentials
import storage.src.KEY_PREFERENCE_STORAGE.Companion.KEY_ID
import storage.src.KEY_PREFERENCE_STORAGE.Companion.KEY_NAME
import storage.src.KEY_PREFERENCE_STORAGE.Companion.KEY_ROL
import storage.src.KEY_PREFERENCE_STORAGE.Companion.KEY_USERNAME

class StoragePreferences(private val context: Context) {
    private val Context.dataStore by preferencesDataStore(name = "USER_CREDENTIALS")

    fun getCredentiales() = context.dataStore.data.map {
        preferences ->
        Credentials(
            id = preferences[KEY_ID],
            nombre = preferences[KEY_NAME].orEmpty(),
            username = preferences[KEY_USERNAME].orEmpty(),
            Rol = preferences[KEY_ROL].orEmpty()
        )
    }

    suspend fun saveCredentials(e: Credentials){
        context.dataStore.edit { preferences ->
            e.id?.let {preferences[KEY_ID] = e.id}
            e.nombre?.let { preferences[KEY_NAME] = e.nombre }
            e.username?.let { preferences[KEY_USERNAME] = e.username }
            e.Rol?.let { preferences[KEY_ROL] = e.Rol }
        }
    }

    suspend fun deleteCredentials(){
        context.dataStore.edit { preferences ->
            preferences.clear()
        }
    }
}