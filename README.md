# Mandiri Test Movie

Android multi-module movie discovery app. Browse popular, top-rated, upcoming, and now-playing movies. Watch trailers, search films, save favourites, and manage your profile — all with background services for heartbeat, location, and profile sync.

## Technologies

- [Kotlin](https://kotlinlang.org/) — 100% Kotlin
- [Ktor](https://ktor.io/) — HTTP client for TMDB API
- [Firebase Realtime Database](https://firebase.google.com/docs/database) + [Firestore](https://firebase.google.com/docs/firestore) — Real-time data & user storage
- [Firebase Auth](https://firebase.google.com/docs/auth) & [Google Sign-In](https://developers.google.com/identity/sign-in/android) — Authentication
- [Firebase Cloud Messaging](https://firebase.google.com/docs/cloud-messaging) — Push notifications
- [Room](https://developer.android.com/training/data-storage/room) — Local database (favourites, heartbeat, users)
- [DataStore](https://developer.android.com/topic/libraries/architecture/datastore) + SharedPreferences — Local preferences
- [Material Design 3](https://m3.material.io/) — UI components
- [Glide](https://github.com/bumptech/glide) — Image loading with blur transformations
- [BannerView](https://github.com/zhpanvip/BannerViewPager) — Movie banner carousel
- [Coroutines & Flow](https://kotlinlang.org/docs/coroutines-overview.html) — Async operations
- [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) — State management
- [Dagger Hilt](https://developer.android.com/training/dependency-injection/hilt-android) — Dependency injection
- [Moshi](https://github.com/square/moshi) — JSON serialization
- [Paging 3](https://developer.android.com/topic/libraries/architecture/paging/v3-overview) — Paginated movie lists
- [Navigation Component](https://developer.android.com/guide/navigation) — Multi-graph navigation
- [The Movie Database (TMDB) API](https://developers.themoviedb.org/3) — Movie data source

## Architecture

Multi-module project with clean architecture layers:

```
:app                              → Application entry point
:core:common                      → Base classes, preferences, utilities
:core:data                        → Repository layer
:core:domain                      → Use case layer
:core:model                       → Data models (remote & local entities)
:core:nav                         → Navigation graphs & navigators
:core:network                     → Ktor client, Firebase/Firestore sources
:core:ui                          → Shared UI components, dialogs, themes
:core:utilities                   → Extension functions, constants, listeners
:feature:auth                     → Login, register, Google Sign-In
:feature:common                   → Shared feature utilities
:feature:menu                     → Bottom nav menu, settings
:feature:movie                    → Home, search, detail, favourites, credits
:feature:services                 → Foreground services (heartbeat, location, profile)
:feature:splash                   → Splash screen
```

<img src="https://github.com/boysmtv/boys-learn-fe-android-ktor-movie/assets/30995595/2b6bbb0e-f49a-45ab-8673-208e50f2802a" width="300" height="650" />
<img src="https://github.com/boysmtv/boys-learn-fe-android-ktor-movie/assets/30995595/61a80e56-2ed4-440b-9288-79a1244ceeee" width="300" height="650" />
<img src="https://github.com/boysmtv/boys-learn-fe-android-ktor-movie/assets/30995595/879e561a-d8b4-4a8e-8883-5dc01a6c6c89" width="300" height="650" />
<img src="https://github.com/boysmtv/boys-learn-fe-android-ktor-movie/assets/30995595/5369bf9e-bfab-4d12-a23a-a5d7131fbf17" width="300" height="650" />
<img src="https://github.com/boysmtv/boys-learn-fe-android-ktor-movie/assets/30995595/2099377c-d096-497b-9490-103dbbab06a1" width="300" height="650" />
<img src="https://github.com/boysmtv/boys-learn-fe-android-ktor-movie/assets/30995595/9d1fbc1b-2b3f-457e-9a0c-514ca35d631c" width="300" height="650" />
<img src="https://github.com/boysmtv/boys-learn-fe-android-ktor-movie/assets/30995595/f500ba24-5960-4609-81f5-e6d9127f5c13" width="300" height="650" />
<img src="https://github.com/boysmtv/boys-learn-fe-android-ktor-movie/assets/30995595/bd16758f-1137-45c2-a916-063a7d2ef022" width="300" height="650" />
<img src="https://github.com/boysmtv/boys-learn-fe-android-ktor-movie/assets/30995595/fd26d732-b9eb-4d15-922a-b542e2ede67f" width="300" height="650" />
<img src="https://github.com/boysmtv/boys-learn-fe-android-ktor-movie/assets/30995595/07b75b90-3eb5-4773-a910-4bd17f59f1de" width="300" height="650" />

