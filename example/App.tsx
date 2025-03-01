import ExpoEsimModule from 'expo-esim';
import { useState } from 'react';
import { Button, SafeAreaView, Text, TextInput, TouchableOpacity, View, StyleSheet } from 'react-native';
import { Alert } from 'react-native';

export default function App() {
  const [activationCode, setActivationCode] = useState('');

  const installEsim = async () => {
    if (!activationCode) {
      Alert.alert('Error', 'Activation code is empty!');
      return;
    }

    ExpoEsimModule.install(activationCode)
      .then((result) => {
        Alert.alert('Success', JSON.stringify(result));
      })
      .catch((error) => {
        Alert.alert('Error', JSON.stringify(error));
      });
  };

  const scanEsimQrCode = async () => {
    ExpoEsimModule.scanQrCode()
      .then((result) => {
        Alert.alert('Success', JSON.stringify(result));
      })
      .catch((error) => {
        Alert.alert('Error', JSON.stringify(error));
      });
  };

  const setTestActivationCode = () => {
    setActivationCode('1$prod.smdp-plus.rsp.goog$052X-UFXS-CQIY-PNGL');
  };

  return (
    <SafeAreaView style={styles.container}>
      <Text style={styles.title}>Activation Code</Text>
      <TextInput
        style={styles.input}
        value={activationCode}
        onChangeText={setActivationCode}
        placeholder="Enter activation code"
        placeholderTextColor="#999"
      />
      <View style={styles.buttonContainer}>
        <Button title="Install eSIM" onPress={installEsim} color="#6200EE" />
      </View>
      <View style={styles.buttonContainer}>
        <Button title="Scan eSIM Qr Code" onPress={scanEsimQrCode} color="#6200EE" />
      </View>
      <TouchableOpacity style={styles.testButton} onPress={setTestActivationCode}>
        <Text style={styles.testButtonText}>Set Testing Activation Code</Text>
      </TouchableOpacity>
    </SafeAreaView>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    padding: 20,
    backgroundColor: '#f5f5f5',
    justifyContent: 'center',
  },
  title: {
    fontSize: 18,
    fontWeight: 'bold',
    color: '#333',
    marginBottom: 10,
  },
  input: {
    height: 40,
    borderColor: '#ddd',
    borderWidth: 1,
    borderRadius: 8,
    paddingHorizontal: 10,
    marginBottom: 20,
    backgroundColor: '#fff',
    fontSize: 16,
    color: '#333',
  },
  buttonContainer: {
    marginTop: 10,
    borderRadius: 8,
    overflow: 'hidden',
  },
  testButton: {
    marginTop: 20,
    backgroundColor: '#03A9F4',
    paddingVertical: 10,
    paddingHorizontal: 15,
    borderRadius: 8,
    alignItems: 'center',
  },
  testButtonText: {
    fontSize: 16,
    color: '#fff',
    fontWeight: 'bold',
  },
});
