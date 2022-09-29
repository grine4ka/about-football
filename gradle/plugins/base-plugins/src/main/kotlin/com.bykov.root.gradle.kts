// Configure the ':tasks' task of the root project to only show
// the main lifecycle tasks as entry points to the build
val mainBuildGroup = "main build"
tasks.named<TaskReportTask>("tasks") {
    displayGroup = mainBuildGroup
}

tasks.register("build") {
    group = mainBuildGroup
    description = "Complete build of all modules and the application"
    dependsOn(subprojects.map { ":${it.name}:$name" })
}

tasks.register("check") {
    group = mainBuildGroup
    description = "Runs all checks and produces test summary and code coverage reports"
    dependsOn(subprojects.map { ":${it.name}:$name" })
    doLast {
        println("Unit test summary: app/build/reports/tests/unit-test/aggregated-results/index.html")
        println("Unit test code coverage: app/build/reports/jacoco/testCodeCoverageReport/html/index.html")
    }
}
