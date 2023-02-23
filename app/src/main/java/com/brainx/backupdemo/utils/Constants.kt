package com.brainx.backupdemo.utils

class Constants {
    companion object {
        const val TAG = "BackupRestoreApp"
        const val TAG_BACKUP = "BackupRestoreAgent"


        const val FILE_NAME = "DataFile"

        const val DB_NAME = "Data.db"
        const val BACKUP_KEY_DB = "dbs"

        const val SP_NAME = "SimpleData"
        const val SP_INIT_KEY = "alreadyInit"
        const val BACKUP_KEY_SP = "prefs"

        const val BACKUP_SOURCE_FILE_PREFIX = "backup-source-"
    }
}