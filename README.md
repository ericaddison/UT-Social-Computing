# UT-Social-Computing
Programming for the UT Social Computing Course

To build:
```
gradle wrapper
./gradlew build
```

to test
```
./gradlew test
```

to run DGS algorithm
```
gradle runDGS -Pfilepath='path/to/input/file' 
```

to run KM algorithm
```
gradle runKM -Pfilepath='path/to/input/file'
```

to run Stable Marriage algorithm
```
gradle runSMP -Pcmdline_args='path/to/input/file m'
```
