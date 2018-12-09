package com.example.dony.practice.architectures.bases

import android.app.Activity
import android.arch.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {
    protected abstract fun onErrorHandle(activity: Activity?, error: Throwable, errorUnit: (() -> Unit)? = null)
}