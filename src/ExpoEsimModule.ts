import { requireNativeModule } from 'expo';

declare class ExpoEsimModule {
  install(activationCode: string): Promise<string>;
  scanQrCode(): Promise<string>;
}

export default requireNativeModule<ExpoEsimModule>('ExpoEsim');
