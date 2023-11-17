package com.safi.designsystem.extfun

import android.util.Log
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.viewbinding.ViewBinding

inline fun <V : ViewBinding> Fragment.viewBinding(crossinline bindingInflater: (LayoutInflater) -> V) : Lazy<V> {
    var _value:V? = null
    this.lifecycle.addObserver(object : DefaultLifecycleObserver {
        override fun onResume(owner: LifecycleOwner) {
            super.onResume(owner)
            Log.e("checkLifecycle", "onResume: ")
        }

        override fun onStop(owner: LifecycleOwner) {
            super.onStop(owner)
            _value = null
            Log.e("checkLifecycle", "onStop: ")
        }

        override fun onDestroy(owner: LifecycleOwner) {
            super.onDestroy(owner)
            Log.e("checkLifecycle", "onDestroy: ")
        }
    })

    return lazy(LazyThreadSafetyMode.NONE) {
        bindingInflater.invoke(layoutInflater)
    }
}