package com.project.toko.core.domain.di.module

import android.content.Context
import coil.ImageLoader
import coil.decode.SvgDecoder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SvgModule {

    @Provides
    @Singleton
    fun provideSvgImageLoader(@ApplicationContext context: Context): ImageLoader {
        return ImageLoader.Builder(context).components {
            add(SvgDecoder.Factory())
        }.build()
    }
}