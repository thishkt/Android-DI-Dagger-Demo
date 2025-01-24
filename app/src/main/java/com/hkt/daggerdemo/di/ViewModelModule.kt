package com.hkt.daggerdemo.di

import androidx.lifecycle.ViewModel
import com.hkt.daggerdemo.ui.UserViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import dagger.multibindings.StringKey

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @StringKey("UserViewModel")
    abstract fun bindUserViewModel(userViewModel: UserViewModel): ViewModel
} 