package com.composeit.task.permission

import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.PermissionStatus

internal object PermissionStateFactory {

    @ExperimentalPermissionsApi
    fun getGrantedPermissionState(permission: String): PermissionState = object : PermissionState {

        override val permission: String
            get() = permission

        override val status: PermissionStatus
            get() = PermissionStatus.Granted

        override fun launchPermissionRequest() {

        }
    }

}






