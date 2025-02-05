package expo.modules.esim

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.service.euicc.ICarrierEuiccProvisioningService
import android.service.euicc.IGetActivationCodeCallback


class CarrierEuiccProvisioningService : Service() {

    override fun onBind(intent: Intent?): IBinder = binder

    private val binder = object : ICarrierEuiccProvisioningService.Stub () {
        override fun getActivationCode(callback: IGetActivationCodeCallback?) {
            callback?.onSuccess(activationCode)
        }

        override fun getActivationCodeForEid(eid: String?, callback: IGetActivationCodeCallback?) {
            callback?.onSuccess(activationCode)
        }
    }

    companion object {
        var activationCode = ""
            private set

        fun setActivationCode(value: String) {
            activationCode = value
        }
    }
}
