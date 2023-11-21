package com.safi.designsystem.extfun

import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.launch


/** Fragment lifecycle aware coroutine executor extension function*/
inline fun Fragment.execute(crossinline job: suspend ()->Unit) {

    with(viewLifecycleOwner) {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                job.invoke()
            }
        }
    }

}