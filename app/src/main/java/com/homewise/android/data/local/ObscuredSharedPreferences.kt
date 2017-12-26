package com.homewise.android.data.local

import android.content.Context
import android.content.SharedPreferences
import android.provider.Settings
import android.util.Base64
import android.util.Log
import java.nio.charset.Charset
import javax.crypto.Cipher
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.PBEKeySpec
import javax.crypto.spec.PBEParameterSpec
import kotlin.text.Charsets.UTF_8

/**
 * Created by @raj on 26/12/17.
 */
class ObscuredSharedPreferences : SharedPreferences {
    protected val UTF8 = "UTF-8"
    //this key is defined at runtime based on ANDROID_ID which is supposed to last the life of the device
    private var SEKRIT: CharArray? = null


    protected lateinit var delegate: SharedPreferences
    protected lateinit var context: Context

    //Set to true if a decryption error was detected
    //in the case of float, int, and long we can tell if there was a parse error
    //this does not detect an error in strings or boolean - that requires more sophisticated checks
    var decryptionErrorFlag = false

    /**
     * Constructor
     * @param context
     * @param delegate - SharedPreferences object from the system
     */
    constructor(context: Context, delegate: SharedPreferences) {
        this.delegate = delegate
        this.context = context
        SEKRIT = Settings.Secure.ANDROID_ID.toCharArray()
    }

    /**
     * Only used to change to a new key during runtime.
     * If you don't want to use the default per-device key for example
     * @param key
     */
    fun setNewKey(key: String) {
        SEKRIT = key.toCharArray()
    }

    /**
     * Accessor to grab the preferences in a singleton.  This stores the reference in a singleton so it can be accessed repeatedly with
     * no performance penalty
     * @param c - the context used to access the preferences.
     * @param appName - domain the shared preferences should be stored under
     * @param contextMode - Typically Context.MODE_PRIVATE
     * @return
     */
    companion object {
        private var prefs: ObscuredSharedPreferences? = null

        @Synchronized
        fun getPrefs(c: Context, appName: String, contextMode: Int): ObscuredSharedPreferences {
            if (prefs == null) {
                //make sure to use application context since preferences live outside an Activity
                //use for objects that have global scope like: prefs or starting services
                prefs = ObscuredSharedPreferences(
                        c.applicationContext, c.applicationContext.getSharedPreferences(appName, contextMode))
            }
            return prefs!!
        }
    }

    inner class Editor : SharedPreferences.Editor {
        protected var delegate: SharedPreferences.Editor

        init {
            this.delegate = this@ObscuredSharedPreferences.delegate.edit()
        }

        override fun putBoolean(key: String, value: Boolean): Editor {
            delegate.putString(key, encrypt(java.lang.Boolean.toString(value)))
            return this
        }

        override fun putFloat(key: String, value: Float): Editor {
            delegate.putString(key, encrypt(java.lang.Float.toString(value)))
            return this
        }

        override fun putInt(key: String, value: Int): Editor {
            delegate.putString(key, encrypt(Integer.toString(value)))
            return this
        }

        override fun putLong(key: String, value: Long): Editor {
            delegate.putString(key, encrypt(java.lang.Long.toString(value)))
            return this
        }

        override fun putString(key: String, value: String?): Editor {
            delegate.putString(key, encrypt(value))
            return this
        }

        override fun apply() {
            //to maintain compatibility with android level 7
            delegate.commit()
        }

        override fun clear(): Editor {
            delegate.clear()
            return this
        }

        override fun commit(): Boolean {
            return delegate.commit()
        }

        override fun remove(s: String): Editor {
            delegate.remove(s)
            return this
        }

        override fun putStringSet(key: String, values: Set<String>?): android.content.SharedPreferences.Editor {
            throw RuntimeException("This class does not work with String Sets.")
        }
    }

    override fun edit(): Editor {
        return Editor()
    }


    override fun getAll(): Map<String, *> {
        throw UnsupportedOperationException() // left as an exercise to the reader
    }

    override fun getBoolean(key: String, defValue: Boolean): Boolean {
        //if these weren't encrypted, then it won't be a string
        val v: String?
        try {
            v = delegate.getString(key, null)
        } catch (e: ClassCastException) {
            return delegate.getBoolean(key, defValue)
        }

        return if (v != null) java.lang.Boolean.parseBoolean(decrypt(v)) else defValue
    }

    override fun getFloat(key: String, defValue: Float): Float {
        val v: String?
        try {
            v = delegate.getString(key, null)
        } catch (e: ClassCastException) {
            return delegate.getFloat(key, defValue)
        }

        try {
            return java.lang.Float.parseFloat(decrypt(v))
        } catch (e: NumberFormatException) {
            //could not decrypt the number.  Maybe we are using the wrong key?
            decryptionErrorFlag = true
            Log.e(this.javaClass.name, "Warning, could not decrypt the value.  Possible incorrect key.  " + e.message)
        }

        return defValue
    }

    override fun getInt(key: String, defValue: Int): Int {
        val v: String?
        try {
            v = delegate.getString(key, null)
        } catch (e: ClassCastException) {
            return delegate.getInt(key, defValue)
        }

        try {
            return Integer.parseInt(decrypt(v))
        } catch (e: NumberFormatException) {
            //could not decrypt the number.  Maybe we are using the wrong key?
            decryptionErrorFlag = true
            Log.e(this.javaClass.name, "Warning, could not decrypt the value.  Possible incorrect key.  " + e.message)
        }

        return defValue
    }

    override fun getLong(key: String, defValue: Long): Long {
        val v: String?
        try {
            v = delegate.getString(key, null)
        } catch (e: ClassCastException) {
            return delegate.getLong(key, defValue)
        }

        try {
            return java.lang.Long.parseLong(decrypt(v))
        } catch (e: NumberFormatException) {
            //could not decrypt the number.  Maybe we are using the wrong key?
            decryptionErrorFlag = true
            Log.e(this.javaClass.name, "Warning, could not decrypt the value.  Possible incorrect key.  " + e.message)
        }

        return defValue
    }

    override fun getString(key: String, defValue: String?): String? {
        val v = delegate.getString(key, null)
        return if (v != null) decrypt(v) else defValue
    }

    override fun contains(s: String): Boolean {
        return delegate.contains(s)
    }

    override fun registerOnSharedPreferenceChangeListener(onSharedPreferenceChangeListener: SharedPreferences.OnSharedPreferenceChangeListener) {
        delegate.registerOnSharedPreferenceChangeListener(onSharedPreferenceChangeListener)
    }

    override fun unregisterOnSharedPreferenceChangeListener(onSharedPreferenceChangeListener: SharedPreferences.OnSharedPreferenceChangeListener) {
        delegate.unregisterOnSharedPreferenceChangeListener(onSharedPreferenceChangeListener)
    }

    override fun getStringSet(key: String, defValues: Set<String>?): Set<String>? {
        throw RuntimeException("This class does not work with String Sets.")
    }


    protected fun encrypt(value: String?): String {

        try {
            val bytes = value?.toByteArray(charset(UTF8)) ?: ByteArray(0)
            val keyFactory = SecretKeyFactory.getInstance("PBEWithMD5AndDES")
            val key = keyFactory.generateSecret(PBEKeySpec(SEKRIT))
            val pbeCipher = Cipher.getInstance("PBEWithMD5AndDES")
            pbeCipher.init(Cipher.ENCRYPT_MODE, key, PBEParameterSpec(Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID).toByteArray(charset(UTF8)), 20))
            return String(Base64.encode(pbeCipher.doFinal(bytes), Base64.NO_WRAP), UTF_8)
        } catch (e: Exception) {
            throw RuntimeException(e)
        }

    }

    protected fun decrypt(value: String?): String? {
        try {
            val bytes = if (value != null) Base64.decode(value, Base64.DEFAULT) else ByteArray(0)
            val keyFactory = SecretKeyFactory.getInstance("PBEWithMD5AndDES")
            val key = keyFactory.generateSecret(PBEKeySpec(SEKRIT))
            val pbeCipher = Cipher.getInstance("PBEWithMD5AndDES")
            pbeCipher.init(Cipher.DECRYPT_MODE, key, PBEParameterSpec(Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID).toByteArray(charset(UTF8)), 20))
            return String(pbeCipher.doFinal(bytes), UTF_8)
        } catch (e: Exception) {
            Log.e(this.javaClass.name, "Warning, could not decrypt the value.  It may be stored in plaintext.  " + e.message)
            return value
        }

    }
}