package com.brainx.backupdemo

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.activity.viewModels
import com.brainx.backupdemo.bean.CacheData
import com.brainx.backupdemo.databinding.ActivityMainBinding
import com.brainx.backupdemo.utils.Constants
import com.brainx.backupdemo.viewmodel.TransferViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val transferViewModel: TransferViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    private lateinit var sp: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()
        sp = getSharedPreferences(Constants.SP_NAME, Context.MODE_PRIVATE)
        syncInitButtonState(binding.btnInit)
    }

    fun onButtonClick(view: View) {
        when (view) {
            binding.btnInit -> {
                transferViewModel.initAndLoadData(sp)
                // Then load data directly.
                syncInitButtonState(binding.btnInit)
            }
            binding.btnBackup -> transferViewModel.backupData()
            binding.btnRestore -> transferViewModel.restoreData()
        }
    }

    private fun syncInitButtonState(button: Button) {
        if (!sp.getBoolean(Constants.SP_INIT_KEY, false)) {
            Log.d(Constants.TAG, "initAndLoadData() INIT NOT YET")
            button.isEnabled = true
        } else {
            Log.d(Constants.TAG, "initAndLoadData() INIT ALREADY")
            button.isEnabled = false
            transferViewModel.loadData(this) { cacheData: CacheData ->
                Log.d(Constants.TAG, "initAndLoadData() LOAD SUCCEED WITH $cacheData")
                reflectToUI(cacheData)
            }
        }
    }

    private fun reflectToUI(cacheData: CacheData) {
        binding.spWidget.text = cacheData.data ?: ""
    }
}