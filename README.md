![](https://github.com/iambaljeet/Covid19Tracker/blob/master/art/covid19Updates.png)

# Covid19Tracker 🦠

[![GitHub license](https://img.shields.io/github/license/iambaljeet/Covid19Tracker)](LICENSE)
![Github Followers](https://img.shields.io/github/followers/iambaljeet?label=Follow&style=social)
![GitHub stars](https://img.shields.io/github/stars/iambaljeet/Covid19Tracker)
![GitHub forks](https://img.shields.io/github/forks/iambaljeet/Covid19Tracker)
![GitHub watchers](https://img.shields.io/github/watchers/iambaljeet/Covid19Tracker?style=social)
![Tweet](	https://img.shields.io/twitter/url?url=https%3A%2F%2Fgithub.com%2Fiambaljeet%2FCovid19Tracker)
![Twitter Follow](https://img.shields.io/twitter/follow/baljeet_dev?label=Follow&style=social)

**Covid19Tracker** is an Android applciation 📱 used to track Covid 19 data. 

***You can Install and test latest Foodium app from below 👇***

[![Foodium App](https://img.shields.io/badge/Foodium🍲-APK-red.svg?style=for-the-badge&logo=android)](https://github.com/iambaljeet/Covid19Tracker/blob/master/apk/Covid19Tracker.apk)


## About
. This application first conenct to API to get Data. It saves the data to Local DB and fetched it ans shows to UI everytime App is opened.
Also it synchronize the Data with Remote server.
- This makes it offline capable 😃. 
- Clean and Simple Material UI.
- It supports dark theme too 🌗.

*API used in this demo [API](https://covid19.mathdro.id/api)*.

## Built With 🛠
- [Kotlin](https://kotlinlang.org/) - First class and official programming language for Android development.
- [Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html) - For asynchronous and more..
- [Flow](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/-flow/) - A cold asynchronous data stream that sequentially emits values and completes normally or with an exception.
- [Android Architecture Components](https://developer.android.com/topic/libraries/architecture) - Collection of libraries that help you design robust, testable, and maintainable apps.
  - [LiveData](https://developer.android.com/topic/libraries/architecture/livedata) - Data objects that notify views when the underlying database changes.
  - [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - Stores UI-related data that isn't destroyed on UI changes. 
  - [ViewBinding](https://developer.android.com/topic/libraries/view-binding) - Generates a binding class for each XML layout file present in that module and allows you to more easily write code that interacts with views.
  - [Room](https://developer.android.com/topic/libraries/architecture/room) - SQLite object mapping library.
- [Koin](https://insert-koin.io/) - Dependency Injection Framework
- [Retrofit](https://square.github.io/retrofit/) - A type-safe HTTP client for Android and Java.
- [Gson](https://github.com/google/gson) - A JSON library for Kotlin and Java.
- [Material Components for Android](https://github.com/material-components/material-components-android) - Modular and customizable Material Design UI components for Android.

**Contributed By:** [Baljeet Singh](https://github.com/iambaljeet/)

# Package Structure
    
    com.app.covid19tracker    # Root Package
    .
    ├── adapter             # List adapters
    |
    ├── app                 # Application class
    |
    ├── db                  # Local database classes
    |
    ├── model               # Model classes
    |
    ├── networking          # Networking helper classes
    |
    ├──repository           # Repository class for managing local and remote data
    |
    ├── di                  # Dependency Injection 
    │   └── module          # Koin DI Modules
    |
    ├── ui                  # Activity/View layer
    │   ├── daily           # Daily Data Fragment and ViewModel
    │   ├── main            # Main Screen Activity
    │   └── home            # Home Fragment and ViewModel
    │   └── splash          # Splash Screen Activity
    |
    └── utility             # Utility Classes / Kotlin extensions


## Architecture
This app uses [***MVVM (Model View View-Model)***](https://developer.android.com/jetpack/docs/guide#recommended-app-arch) architecture.

![](https://developer.android.com/topic/libraries/architecture/images/final-architecture.png)

## Contribute
If you want to contribute to this library, you're always welcome!
See [Contributing Guidelines](CONTRIBUTING.md). 

## Contact
If you need any help, you can connect with me.

Visit:- [baljeet.dev](https://baljeet.dev)

## License
```
GNU License Visit:- [LICENSE](https://github.com/iambaljeet/Covid19Tracker/blob/master/LICENSE)

```
