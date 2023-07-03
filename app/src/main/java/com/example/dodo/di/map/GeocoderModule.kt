package com.example.dodo.di.map

import android.content.Context
import android.location.Geocoder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object GeocoderModule {

    @Singleton
    @Provides
    fun provideGeocoder(
        @ApplicationContext context: Context
    ): Geocoder = Geocoder(context)
}