package com.brainx.backupdemo.utils

import android.content.Context
import java.io.*

class Utils {
    companion object {

        fun saveInstanceToDisk(data: String, context: Context): Boolean {
            val savedFile = File(context.filesDir.path + File.separator + Constants.FILE_NAME)
            var objectOutputStream: ObjectOutputStream? = null
            var result = false

            try {
                objectOutputStream = ObjectOutputStream(FileOutputStream(savedFile))
                objectOutputStream.writeObject(data)
                result = true
            } catch (e: IOException) {
            } finally {
                try {
                    objectOutputStream?.close()
                } catch (e: IOException) {
                }
            }

            return result
        }

        fun readInstanceFromDisk(context: Context): String? {
            val savedFile = File(context.filesDir.path + File.separator + Constants.FILE_NAME)
            var objectInputStream: ObjectInputStream? = null
            var data: String? = null

            try {
                objectInputStream = ObjectInputStream(FileInputStream(savedFile))
                data = objectInputStream.readObject() as String
            } catch (e: Exception) {
            } finally {
                try {
                    objectInputStream?.close()
                } catch (e: IOException) {
                }
            }

            return data
        }
    }
}