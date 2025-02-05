package expo.modules.esim

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Build
import android.telephony.euicc.EuiccManager
import androidx.annotation.RequiresApi
import expo.modules.kotlin.Promise
import expo.modules.kotlin.activityresult.AppContextActivityResultContract
import expo.modules.kotlin.activityresult.AppContextActivityResultLauncher
import expo.modules.kotlin.exception.CodedException
import expo.modules.kotlin.modules.Module
import expo.modules.kotlin.modules.ModuleDefinition
import java.io.Serializable


class ExpoEsimModule : Module() {

    @RequiresApi(Build.VERSION_CODES.R)
    override fun definition() = ModuleDefinition {
        Name(NAME)

        AsyncFunction("install") { activationCode: String, promise: Promise ->
            CarrierEuiccProvisioningService.setActivationCode(activationCode)
            launchEsimManagerIntent(
                promise = promise,
                useQrCode = false
            )
        }

        AsyncFunction("scanQrCode") { promise: Promise ->
            launchEsimManagerIntent(
                promise = promise,
                useQrCode = true
            )
        }

        RegisterActivityContracts {
            activityResultLauncher = registerForActivityResult(activityResultContract)
        }
    }

    private var currentPromise: Promise? = null
    private var activityResultLauncher: AppContextActivityResultLauncher<IntentWrapper, ActivityResult>? =
        null

    private val activityResultContract =
        object : AppContextActivityResultContract<IntentWrapper, ActivityResult> {
            override fun createIntent(context: Context, input: IntentWrapper): Intent = input.intent

            override fun parseResult(
                input: IntentWrapper,
                resultCode: Int,
                intent: Intent?
            ): ActivityResult = ActivityResult(resultCode = resultCode, data = intent)
        }

    @RequiresApi(Build.VERSION_CODES.R)
    private fun launchEsimManagerIntent(
        promise: Promise,
        useQrCode: Boolean = false
    ) = runCatching {
        currentPromise = promise
        val intent = Intent(EuiccManager.ACTION_START_EUICC_ACTIVATION).apply {
            putExtra(EuiccManager.EXTRA_USE_QR_SCANNER, useQrCode)
        }
        activityResultLauncher?.launch(
            input = IntentWrapper(intent),
            callback = ::handleResult
        )
    }.onFailure(::installError)

    private fun handleResult(result: ActivityResult) {
        when(result.resultCode) {
            Activity.RESULT_OK -> success()
            Activity.RESULT_CANCELED -> userCanceledError()
            else -> unknownError()
        }
    }

    private fun success() = currentPromise?.resolve(result = "eSIM installed successfully")

    private fun unknownError() = failure(
        code = "UNKNOWN_ERROR",
        message = "Unknown error occurred during eSIM activation"
    )

    private fun userCanceledError() = failure(
        code = "USER_CANCELED",
        message = "User canceled eSIM activation"
    )

    private fun installError(throwable: Throwable) = failure(
        code = "INSTALL_ERROR",
        message = throwable.message ?: ""
    )

    private fun failure(code: String, message: String) = currentPromise?.reject(
        exception = CodedException(
            code = code,
            message = message,
            cause = null
        )
    )

    companion object {
        const val NAME = "ExpoEsim"
    }
}

private class ActivityResult(
    val resultCode: Int,
    val data: Intent?
) : Serializable

private data class IntentWrapper(val intent: Intent) : Serializable
