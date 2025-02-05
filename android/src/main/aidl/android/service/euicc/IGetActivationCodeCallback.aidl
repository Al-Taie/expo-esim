package android.service.euicc;


oneway interface IGetActivationCodeCallback {
    void onSuccess(String activationCode);
    void onFailure();
}
