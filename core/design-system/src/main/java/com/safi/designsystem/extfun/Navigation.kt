package com.safi.designsystem.extfun

import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import com.safi.designsystem.R

fun Fragment.navigate(direction: NavDirections) {

    val currentNode = if (findNavController().currentBackStack.value.isEmpty()) findNavController().graph
    else findNavController().currentBackStack.value.last().destination

    val navAction = currentNode.getAction(direction.actionId)

    findNavController().currentDestination?.getAction(direction.actionId)?.run {
        findNavController().navigate(
            direction.actionId,
            direction.arguments,
            navAction?.navOptions?.let {
                navOptions {
                    anim {
                        enter = R.anim.slide_in_right
                        exit = R.anim.slide_out_left
                        popEnter = R.anim.slide_in_left
                        popExit = R.anim.slide_out_right
                    }
                    popUpTo(it.popUpToId) {
                        inclusive = it.isPopUpToInclusive()
                    }

                }
            }

        )
    }
}