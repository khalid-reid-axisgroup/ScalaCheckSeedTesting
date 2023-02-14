# ScalaCheckSeedTesting

### update: The fix has been included and is explained [here](https://github.com/scalatest/scalatest/issues/2225#issuecomment-1428956921).

Below are the result of 3 executions of the tests.  The test case is designed to fail.  

Expected:
The test fails with a seed number.  I should be able to copy that seed number, set the `-S` parameter in sbt and get the same failing data when I rerun the test.

Actual:
When I rerun the test with the seed from the original failing test, I get different data.  


First Run:
```
sbt:ScalaCheckSeedTesting> test
[info] compiling 2 Scala sources to /Users/khalid/dev/code/ScalaCheckSeedTesting/target/scala-2.12/test-classes ...
Foo(,965892544)
[info] MyTest:
[info] - should work *** FAILED ***
[info]   TestFailedException was thrown during property evaluation.
[info]     Message: "[]" was not equal to "[1]"
[info]     Location: (MyTest.scala:15)
[info]     Occurred when passed generated values (
[info]       arg0 = Foo("", 965892544)
[info]     )
[info]   Init Seed: -2140287876048928510
[info] Run completed in 1 second, 41 milliseconds.
[info] Total number of tests run: 1
[info] Suites: completed 1, aborted 0
[info] Tests: succeeded 0, failed 1, canceled 0, ignored 0, pending 0
[info] *** 1 TEST FAILED ***
[error] Failed tests:
[error] 	MyTest
[error] (Test / test) sbt.TestsFailedException: Tests unsuccessful
[error] Total time: 20 s, completed Feb 10, 2023 4:08:20 PM
```

set the `-S` parameter with the failing seed number:

```
sbt:ScalaCheckSeedTesting> set ThisBuild/Test/testOptions += Tests.Argument(TestFrameworks.ScalaTest, "-S", "-2140287876048928510")
[info] Defining ThisBuild / Test / testOptions
[info] The new value will be used by Test / test / testOptions, Test / testOnly / testOptions, Test / testQuick / testOptions
[info] Reapplying settings...
[info] set current project to ScalaCheckSeedTesting (in build file:/Users/khalid/dev/code/ScalaCheckSeedTesting/)
```

rerun the failing test.  

```
sbt:ScalaCheckSeedTesting> test
Foo(,2012575285)
[info] MyTest:
[info] - should work *** FAILED ***
[info]   TestFailedException was thrown during property evaluation.
[info]     Message: "[]" was not equal to "[1]"
[info]     Location: (MyTest.scala:15)
[info]     Occurred when passed generated values (
[info]       arg0 = Foo("", 2012575285)
[info]     )
[info]   Init Seed: -2140287876048928510
[info] Run completed in 296 milliseconds.
[info] Total number of tests run: 1
[info] Suites: completed 1, aborted 0
[info] Tests: succeeded 0, failed 1, canceled 0, ignored 0, pending 0
[info] *** 1 TEST FAILED ***
[error] Failed tests:
[error] 	MyTest
[error] (Test / test) sbt.TestsFailedException: Tests unsuccessful
[error] Total time: 1 s, completed Feb 10, 2023 4:08:52 PM
```
Notice it produces different data.


Run it again:

```
sbt:ScalaCheckSeedTesting> test
Foo(,2012575285)
[info] MyTest:
[info] - should work *** FAILED ***
[info]   TestFailedException was thrown during property evaluation.
[info]     Message: "[]" was not equal to "[1]"
[info]     Location: (MyTest.scala:15)
[info]     Occurred when passed generated values (
[info]       arg0 = Foo("", 2012575285)
[info]     )
[info]   Init Seed: -2140287876048928510
[info] Run completed in 302 milliseconds.
[info] Total number of tests run: 1
[info] Suites: completed 1, aborted 0
[info] Tests: succeeded 0, failed 1, canceled 0, ignored 0, pending 0
[info] *** 1 TEST FAILED ***
[error] Failed tests:
[error] 	MyTest
[error] (Test / test) sbt.TestsFailedException: Tests unsuccessful
[error] Total time: 1 s, completed Feb 10, 2023 4:09:01 PM
```
Notice that it is the same data as the second run.
