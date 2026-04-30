package com.ElOuedUniv.maktaba.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.storage.Storage
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SupabaseModule {

    @Provides
    @Singleton
    fun provideSupabaseClient(): SupabaseClient {
        return createSupabaseClient(
            supabaseUrl = "https://xiuxrwkormcibhkdcrrm.supabase.co",
            supabaseKey = "sb_publishable_fnQtJhfXfanrO1rUrQj60Q_rt6y-Hmr"
        ) {
            install(Postgrest)
            install(Storage)
        }
    }
}