# 📱 expo-esim

**eSIM Installation Native Module for React Native (Expo)**

A native module for managing eSIM activation and QR code scanning in React Native apps using Expo.

---

## 🚀 Features

- Install eSIM profiles using activation codes
- Scan QR codes for eSIM activation
- Seamless integration with Expo and React Native

---

## 📦 Installation

```bash
npx expo install expo-esim
```

Make sure you are using the custom development client:

```bash
npx expo run:android
```

---

## ⚙️ Usage

### 1️⃣ **Install eSIM**

```typescript
import ExpoEsimModule from 'expo-esim';

const installEsim = async (activationCode: string) => {
  try {
    const result = await ExpoEsimModule.install(activationCode);
    console.log('Success:', result);
  } catch (error) {
    console.error('Error:', error);
  }
};
```

### 2️⃣ **Scan eSIM QR Code**

```typescript
import ExpoEsimModule from 'expo-esim';

const scanEsimQrCode = async () => {
  try {
    const result = await ExpoEsimModule.scanQrCode();
    console.log('Success:', result);
  } catch (error) {
    console.error('Error:', error);
  }
};
```

---

## 📋 API Reference

### `install(activationCode: string): Promise<string>`
- **Description:** Installs an eSIM profile using the provided activation code.
- **Parameters:**
  - `activationCode` (*string*): The eSIM activation code.
- **Returns:** Promise resolving with a success message or rejecting with an error.

### `scanQrCode(): Promise<string>`
- **Description:** Opens the QR code scanner to activate an eSIM.
- **Returns:** Promise resolving with a success message or rejecting with an error.

---

## 📱 Android Requirments

Ensure the app targets Android 11 (API level 30) or higher for eSIM functionality.

---

## 🐛 Reporting Issues

Found a bug or want to request a feature? [Open an issue here](https://github.com/Al-Taie/expo-esim/issues).

---

## 👨‍💻 Author

**Ahmed Mones**  
🔗 [GitHub - Al-Taie](https://github.com/Al-Taie)

---

## 📄 License

This project is licensed under the [MIT License](LICENSE).

---

## 🌟 Support

If you find this module helpful, please consider starring the repository to support the project!

[⭐ Star the repo](https://github.com/Al-Taie/expo-esim)
