import { NativeModule, requireNativeModule } from 'expo';

declare class ExpoEsimModule extends NativeModule {
  install(activationCode: string): Promise<string>;
  scanQrCode(): Promise<string>;
}

export default requireNativeModule<ExpoEsimModule>('ExpoEsim');
