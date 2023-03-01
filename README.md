# Footea

Footea demonstrates my Android development skills.  
For these purposes I used the best game in the world - Football.

## Tech Stack & Libraries

- Minimum SDK level 26 ([92.5% users in Jan 2023](https://apilevels.com/))
- [Kotlin](https://kotlinlang.org/) based, [RxJava2](https://github.com/ReactiveX/RxJava) for asynchronous.
- Architecture:
  - _Clean Architecture_
  - _MVP_
- [Retrofit2 & OkHttp3](https://github.com/square/retrofit) for communicating with REST APIs.
- [Room](https://developer.android.com/training/data-storage/room) for a fluent database access.
- **No DI frameworks**
- [Glide](https://github.com/bumptech/glide) for downloading and processing images
- Gradle
  - [Understanding Gradle](https://www.youtube.com/playlist?list=PLWQK2ZdV4Yl2k2OmC_gsjDpdIBTN0qqkE) by Jendrik Johannes
  - [Full Android Project Setup](https://github.com/jjohannes/gradle-project-setup-howto/tree/android)

## Further improvements

- DiffUtils and AdapterDelegates for list of teams
- Move to MVVM or MVI  (or any other UDF architecture)
- Cache to DB or file
- Use Jetpack Compose for UI
- Use [Cicerone](https://github.com/terrakok/Cicerone) to open another screen
- Recycler Item full screen width
- Beautiful activity transitions
- Kotlin coroutines + Flow ??

## Open API

Footea is using [api-football](https://www.api-football.com/) for constructing RESTful API.
api-football provides RESTful API to highly detailed objects built from thousands of lines of data related to football.

# License

```xml
MIT License

Copyright (c) 2023 Grigorii Bykov (grine4ka)

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
```
