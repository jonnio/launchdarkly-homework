# LaunchDarkly Homework - Osborn
LD sample project to demonstrate some capabilities, complete the assignment, and have some fun.


## Framework
I chose the following framework and tools for this application primarily because they can get you started
in just a few minutes.
* I did the work on a mac and using `brew` to install `java 21` and `gradle`
* I used IntelliJ as an IDE but, of course, you can use whatever you like
* Spring Boot Initalizr is so great to just download and go so that's what I did
* I picked`vue.js` as the front end because it can be run directly in the browser (without transpiler) and is enough to get this work done

# The Application
I built a simple `Search Application` that will give some flexibility to meet the targets for this project. Clearly, this app is simple
and includes some mock data. Since we're focused on `LD` capabilities, I'm ignoring all that stuff.

## Running the Application

### Configuration
I'm assuming you have access to a modern shell. I used `zsh` which is the default on a mac.
Make sure java and gradle are installed. I'm also assuming you know the basics of `gradle`.
```shell
brew update && brew upgrade
brew install java@21
brew install gradle
```

Clone the repo and change directories to it. I'll assume you know what you are doing, so I'll ignore
directory names, etc.
```shell
git clone https://github.com/jonnio/launchdarkly-homework.git
cd launchdarkly-homework
```

The application needs at least `Java 21` so make sure you either have it setup by running
```shell
./gradlew --version
```
and verifying that the output is similar to this with at least Java 21.
```shell
------------------------------------------------------------
Gradle 8.8
------------------------------------------------------------

Build time:   2024-05-31 21:46:56 UTC
Revision:     4bd1b3d3fc3f31db5a26eecb416a165b8cc36082

Kotlin:       1.9.22
Groovy:       3.0.21
Ant:          Apache Ant(TM) version 1.10.13 compiled on January 4 2023
JVM:          21.0.4 (Homebrew 21.0.4)
OS:           Mac OS X 14.5 x86_64
```
If you have the incorrect version of java, you've installed the correct version using brew, and have
created the symlink (per brew instructions), ``export JAVA_HOME=`/usr/libexec/java_home -v 21` `` will usually get you to the right place. Gradle will respect your `JAVA_HOME`
environment variable.

### Building

## Testing

### Running
