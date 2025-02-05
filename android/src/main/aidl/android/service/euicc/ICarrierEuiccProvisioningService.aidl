package android.service.euicc;

import android.service.euicc.IGetActivationCodeCallback;


oneway interface ICarrierEuiccProvisioningService {
    void getActivationCode(in IGetActivationCodeCallback callback);
    void getActivationCodeForEid(in String eid, in IGetActivationCodeCallback callback);
}
