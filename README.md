# ForzaFootball Home Assignment

## Task
Your objective is to create screens with information about football teams using the endpoints below.

The base url for requests and images:  
https://android-exam.s3-eu-west-1.amazonaws.com

Fetch a list of team objects from:  
/teams/teams.json


Further team details can be found at:
/teams/<id>/team.json 

## Method
We encourage usage of Kotlin and RxJava2 for the implementation but you are free to use Java or any frameworks or libraries that you see fit.

### Requirements
1. Write an implementation that fetches teams from the provided endpoints.
2. Present the teams in a list.
3. Create a details screen for a team showing additional information.
4. Cache data to make the application work offline.

### Bonus tasks
1. Create Unit tests that proves that your implementation works as intended.
2. Create UI tests that proves that your implementation works as intended.
3. We will not focus on UI design in this assignment. Great UI design will be treated as a good bonus.
4. Exponential backoffs for requests.

## Further possible improvements
- Move to MVVM or MVI  (or any other UDF architecture)
- Cache to DB or file
- Use Jetpack Compose for UI
- Use [Cicerone](https://github.com/terrakok/Cicerone) to open another screen
- Recycler Item full screen width
- Beautiful activity transitions