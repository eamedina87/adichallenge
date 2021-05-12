# AdiChallenge by Erick Medina

AdiChallenge is a test app that displays a list of products, its detail, its reviews, and allows to add a new review.

The main screen of the app is the products lists, where we can perform the following actions:

* Filter the list by alphabetical order and price. 
* Display products as a list in which the product occupies the whole screen, or as a grid where we can see more products at once. 
* Search products by name or description.

The product detail screen has a more detailed view  of the product including its description and the reviews summary. From here we can go to the reviews list or add a new one.

The product review screen allows the user to give a rating and a message that can be saved by the api service.


## Libraries


- [Retrofit](https://square.github.io/retrofit/)
- [Hilt](https://developer.android.com/training/dependency-injection/hilt-android)
- [Live Data](https://developer.android.com/topic/libraries/architecture/livedata)
- [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodela)
- [Room Database](https://developer.android.com/jetpack/androidx/releases/room)
- [Coroutines](https://developer.android.com/kotlin/coroutines)
- [MockK](https://mockk.io/)

## Environments

The app has two environments set up:
* **debug:** configured to test the app in the Android Studio emulator when the server is setup in the same machine.
* **release:** configured to test the app both in emulator or physical device when the server is setup in other machine.

To set the IP address of the server, we must do it in  [gradle.properties](gradle.properties). The properties to be modified are ```API_BASE_PRODUCT_URL_PROD``` where we set the URL of the products service, and ```API_BASE_REVIEW_URL_PROD``` where we set the URl of the reviews service.

It is also fundamental to add the URLs in the network security file [security_config.xml](app/src/main/res/xml/security_config.xml), else a connection problem will occurr.

## Features

* **Architecture:** MVVM with Use Cases.
* **Dependency Injection:** Using Android dependency injection library.
* **REST API:** with Retrofit
* **Search:** performed using Room Database with Full Text Search.
* **Testing:** with MockK the repositories and use cases are tested.
* **Crash Tracking:** with Firebase Crashlytics.


## License

MIT License

Copyright (c) [2021] [Erick Medina]

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.