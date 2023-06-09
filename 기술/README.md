테스트 한 기술 설명

[Face detection기술]

#얼굴 인식 : openCV, Dlib, Mediapipe와 같은 라이브러리를 사용하여 RGB 2D camera와 Face Landmark를 이용하여 특징점 추출 비교 

#입모양 인식 
1. 얼굴 및 입 영역 검출 (landmark_detector = dlib.shape_predictor("shape_predictor_68_face_landmarks.dat"))
2. 입모양 특징점 추출 (landmark->49~68)
3. 입모양 분석 단계로 구현한다. (mouth_opened와 initial_mouth_open를 비교하여 입을 크게 사용하였는지 확인)
입모양 분석 단계에서 딥러닝 CNN또는 pre-trained 모델을 사용하여 모델을 구축하고 이 때 가려진 입모양을 보간하는 기술도 구현
CNN으로 구현이 어려운 것은 PCA (Principal Component Analysis), Facial Reconstruction을 이용해 가려진 입모양을 보간

= > Face detection을 통해 입모양을 분석하고, 음성 데이터를 통해 복식호흡의 유무를 분석하여 사용자를 훈련 시키고자 함


[음성데이터 기술]
음성처리 : 음성 데이터 분석하는 librosa를 사용하여 알고리즘 MFCC을 사용하여 노이즈를 제거 
복식호흡을 판단하기 위해 음성 데이터에서 spleeter와 ffmpeg를 통해 MR과 잡음을 전처리하고, 전처리한 데이터에 librosa 라이브러리의 waveplot으로 목소리의 강도를 시각화하고,
MFCC(음성의 특성 추출)를 사용하여 각 샘플의 음색과 피치를 분석