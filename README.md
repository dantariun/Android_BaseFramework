# Android_BaseFramework

Android 앱 개발을 위한 기반 프레임워크 템플릿. MVVM 아키텍처와 공통 컴포넌트를 미리 세팅한 보일러플레이트다.

## Stack

![Kotlin](https://img.shields.io/badge/Kotlin-Android-7F52FF)
![MVVM](https://img.shields.io/badge/Architecture-MVVM-blue)
![Retrofit](https://img.shields.io/badge/Network-Retrofit2-orange)

- **언어**: Kotlin
- **아키텍처**: MVVM + Repository
- **네트워크**: Retrofit2 + OkHttp3
- **상태 관리**: LiveData + Coroutines
- **저장소**: DataStore Preferences
- **DI**: 수동 DI (Hilt 미적용)
- **내비게이션**: Navigation Component
- **로깅**: Timber

## 프로젝트 구조

```
app/
├── framework/          # BaseActivity, BaseFragment, BaseViewModel
├── api/                # Retrofit 설정, ApiService, Repository, 공통 모델
├── repo/               # DataStore, MemStore (인메모리 상태)
│   └── mem/            # Auth, User, Setting 상태 객체
├── ui/                 # 화면별 Activity/Fragment/ViewModel
│   ├── LoginActivity   # 로그인 화면
│   └── login/signup/   # 로그인·회원가입 Fragment
└── MainActivity        # 메인 (BottomNavigation)
```

## 주요 기능

- **Base 클래스**: 모든 화면에서 공통 사용하는 `BaseActivity`, `BaseFragment`, `BaseViewModel` 제공
- **API 공통 처리**: 응답 래퍼(`ApiResponse`), 에러코드(`ApiErrorCode`), 시스템 타입 공통화
- **인증 흐름**: 로그인 → JWT 저장(DataStore) → 메인 화면 이동
- **Bottom Navigation**: Fragment 기반 탭 전환

## 시작하기

```bash
git clone https://github.com/dantariun/Android_BaseFramework.git
```

`app/src/main/java/.../api/ApiModule.kt`에서 BASE_URL을 프로젝트에 맞게 변경 후 빌드.

**요구사항**: Android Studio Flamingo 이상, compileSdk 33
