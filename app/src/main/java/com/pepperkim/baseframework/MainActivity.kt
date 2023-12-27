package com.pepperkim.baseframework

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.app.NotificationCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.gun0912.tedpermission.coroutine.TedPermission
import com.pepperkim.baseframework.api.model.common.ApiErrorCode
import com.pepperkim.baseframework.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private val viewModel : MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

//        val navView : BottomNavigationView = binding.navView
//        val navController = findNavController(R.id.nav_host_fragment_activity_main)
//        navView.setupWithNavController(navController)

        viewModel.setUserId("둘리")

        initUI()
        setObserver()
        asyncPermissionCheck()
    }

    private fun asyncPermissionCheck() = lifecycleScope.launch{
        TedPermission.create()
            .setPermissions(
//                Manifest.permission.INTERNET,
//                Manifest.permission.ACCESS_FINE_LOCATION,
//                Manifest.permission.BLUETOOTH_SCAN,
//                Manifest.permission.BLUETOOTH_ADVERTISE,
                Manifest.permission.POST_NOTIFICATIONS
            )
            .check()
    }

    private fun initUI(){
        binding.testText.setOnClickListener {
//            viewModel.loadData()

//            val code = ApiErrorCode.dataOf(200)
//            Timber.d("code = $code")

//            viewModel.getUserId()


            val intent = Intent(this, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)
            val channelID = "Channel ID"
            val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
            val notificationBuilder = NotificationCompat.Builder(this, channelID)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Test Title" + System.currentTimeMillis().toInt().toString())
                .setContentText("Test Body")
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent)

            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                val channelName = "Channel Name"
                val channel = NotificationChannel(channelID, channelName, NotificationManager.IMPORTANCE_HIGH)
                notificationManager.createNotificationChannel(channel)
            }

            notificationManager.notify( System.currentTimeMillis().toInt(), notificationBuilder.build())

        }
    }

    private fun setObserver(){
        viewModel.movieContents.observe(this) {data ->
            data?.let{
                // UI Update
                it.documents?.let {doc->
                    for(movie in doc){
                        Timber.d("getData ${movie.title}")
                    }
                }
            }
        }

        viewModel.getData().observe(this){
            Timber.d("id = $it")
        }
    }
}