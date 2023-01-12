# Footea

Footea demonstrates my Android development skills.  
For these purposes I used the best game in the world - Football.

## Tech Stack & Libraries

- **Language**: _Kotlin_
- **Architecture**: _Clean Architecture + MVP_
  - Package by layer
- **Network**
  - [Retrofit2 & OkHttp3](https://github.com/square/retrofit)
  - [RxJava2](https://github.com/ReactiveX/RxJava)
- **DI**: _No. Self-written_
- **Image Processing**: 
  - [Glide](https://github.com/bumptech/glide)
- **Gradle**
  - [Understanding Gradle](https://www.youtube.com/playlist?list=PLWQK2ZdV4Yl2k2OmC_gsjDpdIBTN0qqkE) by Jendrik Johannes
  - [Full Android Project Setup](https://github.com/jjohannes/gradle-project-setup-howto/tree/android)

## Further improvements

- Move to [api-football.com](https://api-football.com) API
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

## Deprecated

At first it was a home assignment for the ForzaFootball company

### Task
Your objective is to create screens with information about football teams using the endpoints below.

The base url for requests and images:  
https://android-exam.s3-eu-west-1.amazonaws.com

Fetch a list of team objects from:  
/teams/teams.json


Further team details can be found at:
/teams/<id>/team.json 

### Method (Deprecated)
We encourage usage of Kotlin and RxJava2 for the implementation but you are free to use Java or any frameworks or libraries that you see fit.

#### Requirements (Deprecated)
1. Write an implementation that fetches teams from the provided endpoints.
2. Present the teams in a list.
3. Create a details screen for a team showing additional information.
4. Cache data to make the application work offline.

#### Bonus tasks (Deprecated)
1. Create Unit tests that proves that your implementation works as intended.
2. Create UI tests that proves that your implementation works as intended.
3. We will not focus on UI design in this assignment. Great UI design will be treated as a good bonus.
4. Exponential backoffs for requests.
