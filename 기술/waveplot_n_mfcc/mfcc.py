import numpy as np
import librosa     
import librosa.display
import matplotlib.pyplot as plt

path = '8282_mr_removed.wav'
sample_rate=16000

import librosa


y, sr  = librosa.load(path)
'''
mfccs = librosa.feature.mfcc(y, sr=sr)

# MFCC 시각화
plt.figure(figsize=(10, 4))
librosa.display.specshow(mfccs, x_axis='time', sr=sr)
plt.colorbar(format='%+2.0f dB')
plt.title('MFCC - Your File')
plt.tight_layout()
plt.show()

'''
#wave
plt.figure(figsize=(14, 5))
librosa.display.waveshow(y, sr=sr)
plt.title('Waveplot - Your File')
plt.show()