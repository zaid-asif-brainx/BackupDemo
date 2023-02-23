package com.brainx.backupdemo.model

import android.app.backup.BackupManager
import android.app.backup.RestoreObserver
import android.content.Context
import android.util.Log
import com.brainx.backupdemo.utils.Utils
import com.brainx.backupdemo.bean.CacheData
import com.brainx.backupdemo.utils.Constants
import dagger.hilt.android.qualifiers.ActivityContext
import javax.inject.Inject

class LocalData @Inject constructor(
    @ActivityContext private val context: Context,
    val backupManager: BackupManager
){
    fun initData(): Boolean {
        return saveData()
    }

    private fun saveData(): Boolean {
        return Utils.saveInstanceToDisk("203030000", context)
    }


    fun loadData(): CacheData {
        val data = Utils.readInstanceFromDisk(context)
        return CacheData(data)
    }

    fun backupData() {
        Log.d(Constants.TAG, "LocalData#backupData()")
        backupManager.dataChanged()
    }

    fun restoreData() {
        Log.d(Constants.TAG, "LocalData#restoreData()")

        backupManager.requestRestore(object: RestoreObserver() {
            override fun restoreStarting(numPackages: Int) {
                Log.d(Constants.TAG_BACKUP, "restoreStarting numPackages:$numPackages")
                super.restoreStarting(numPackages)
            }

            override fun onUpdate(nowBeingRestored: Int, currentPackage: String?) {
                Log.d(Constants.TAG_BACKUP, "restoreStarting nowBeingRestored:$nowBeingRestored currentPackage:$currentPackage")
                super.onUpdate(nowBeingRestored, currentPackage)
            }

            override fun restoreFinished(error: Int) {
                Log.d(Constants.TAG_BACKUP, "restoreFinished error:$error")
                super.restoreFinished(error)
            }
        })
    }
}