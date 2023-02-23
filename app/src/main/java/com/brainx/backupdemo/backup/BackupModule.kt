package com.brainx.backupdemo.backup

import android.app.Application
import android.app.backup.BackupManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class BackupModule {
    @Singleton
    @Provides
    fun provideStringDatabase(application: Application): BackupManager {
        return BackupManager(application)
    }
}