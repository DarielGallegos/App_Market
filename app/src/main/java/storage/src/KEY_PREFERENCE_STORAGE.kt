package storage.src

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey

enum class KEY_PREFERENCE_STORAGE {
    ;
    companion object {
        val KEY_ID = intPreferencesKey("id")
        val KEY_NAME = stringPreferencesKey("nombre")
        val KEY_USERNAME = stringPreferencesKey("username")
        val KEY_ROL = stringPreferencesKey("rol")
        val KEY_TELEFONO = stringPreferencesKey("telefono")
        val KEY_EMPLEADO = booleanPreferencesKey("empleado")
    }
}