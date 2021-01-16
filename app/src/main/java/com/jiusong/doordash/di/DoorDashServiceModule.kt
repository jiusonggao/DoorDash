package com.jiusong.doordash.di

import com.jiusong.doordash.data.network.DoorDashAPi
import com.jiusong.doordash.data.network.DoorDashServiceFactory
import com.jiusong.doordash.util.DoorDashConstants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

/**
 * Created by jiusong.gao on 1/16/21.
 */
@InstallIn(ApplicationComponent::class)
@Module
object DoorDashServiceModule {

    @Provides
    fun providesDoorDashApi(): DoorDashAPi {
        return DoorDashServiceFactory.retrofit(DoorDashConstants.DOOR_DASH_API_BASE_URL).create(DoorDashAPi::class.java)
    }
}