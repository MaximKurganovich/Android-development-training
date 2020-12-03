package com.example.moshi

import android.app.Application
import com.example.moshi.networking.Network
import com.facebook.flipper.android.AndroidFlipperClient
import com.facebook.flipper.plugins.inspector.DescriptorMapping
import com.facebook.flipper.plugins.inspector.InspectorFlipperPlugin
import com.facebook.soloader.SoLoader

// Подключаем Flipper в проект (не забыть указать класс в манифесте)
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        SoLoader.init(this, false)

        AndroidFlipperClient.getInstance(this)
            .apply {
                addPlugin(InspectorFlipperPlugin(this@App, DescriptorMapping.withDefaults()))
                addPlugin(Network.flipperNetworkPlugin)
            }
            .start()
    }
}