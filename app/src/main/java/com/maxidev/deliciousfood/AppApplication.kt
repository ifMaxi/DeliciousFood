package com.maxidev.deliciousfood

import android.app.Application
import coil.ImageLoader
import coil.ImageLoaderFactory
import coil.disk.DiskCache
import coil.memory.MemoryCache
import coil.util.DebugLogger
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class AppApplication: Application(), ImageLoaderFactory {

    override fun newImageLoader(): ImageLoader {

        return ImageLoader.Builder(this)
            .memoryCache {
                MemoryCache.Builder(this)
                    .maxSizePercent(0.25)
                    .build()
            }
            .diskCache {
                DiskCache.Builder()
                    .maxSizePercent(0.02)
                    .directory(cacheDir.resolve("image_cache"))
                    .build()
            }
            .allowHardware(true)
            .logger(DebugLogger())
            .respectCacheHeaders(false)
            .build()
    }
}