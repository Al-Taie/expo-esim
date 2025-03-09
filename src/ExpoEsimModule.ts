import { requireNativeModule } from 'expo';

declare class ExpoEsimModule {
  isEsimSupported(): boolean;
  scanQrCode(): Promise<string>;
  install(activationCode: string): Promise<string>;
}

export default requireNativeModule<ExpoEsimModule>('ExpoEsim');
