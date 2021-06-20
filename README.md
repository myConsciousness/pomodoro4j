![Latest Version](https://img.shields.io/badge/Latest_Version-v1.0.0-27ae60.svg?style=for-the-badge)
![License](https://img.shields.io/badge/License-Apache_2.0-e74c3c.svg?style=for-the-badge)</br>
![Java CI with Gradle](https://github.com/myConsciousness/pomodoro4j/workflows/Java%20CI%20with%20Gradle/badge.svg)

# 1. Pomodoro4J

## 1.1. What is it?

`Pomodoro4J` is a Pomodoro Technique binding library for the Java language licensed under Apache License 2.0.

<!-- TOC -->

- [1. Pomodoro4J](#1-pomodoro4j)
  - [1.1. What is it?](#11-what-is-it)
  - [1.2. Motivation](#12-motivation)
  - [1.3. How To Use](#13-how-to-use)
    - [1.3.1. Add the dependencies](#131-add-the-dependencies)
    - [1.3.2. Create Pomodoro instance with configuration](#132-create-pomodoro-instance-with-configuration)
    - [1.3.3. Start Pomodoro cycle](#133-start-pomodoro-cycle)
  - [1.4. License](#14-license)
  - [1.5. More Information](#15-more-information)

<!-- /TOC -->

## 1.2. Motivation

1. Provide a tested, simple, and easy-to-use implementation of the Pomodoro Technique.
2. To standardize the implementation of the Pomodoro Technique in programming.
3. Provide intuitive manipulation of the Pomodoro Technique in programming.
4. Pray that the people of the world can work more productively.

## 1.3. How To Use

Pomodoro4J is a rigorously interpreted program of Pomodoro Technique procedures and methods.</br>
Therefore, users of Pomodoro4J can reproduce the real Pomodoro Technique without being aware of how to use or implement each method by following the instructions below.

### 1.3.1. Add the dependencies

> **_Note:_**</br>
> Replace version you want to use. Check the latest [Packages](https://github.com/myConsciousness/pomodoro4j/packages).</br>
> Please contact me for a token to download the GitHub package.
> Alternatively, you can download it directly as a zip file.

**_Maven_**

```xml
<dependency>
  <groupId>org.pomodoro4j</groupId>
  <artifactId>pomodoro4j</artifactId>
  <version>v1.0.0</version>
</dependency>

<servers>
  <server>
    <id>github</id>
    <username>myConsciousness</username>
    <password>xxxxxxxxxxxxxxxxxx</password>
  </server>
</servers>
```

**_Gradle_**

```gradle
repositories {
    maven {
        name = "GitHubPackages"
        url = uri("https://maven.pkg.github.com/myConsciousness/pomodoro4j")
        credentials {
          username = "myConsciousness"
          password = "xxxxxxxxxxxxxxxxxx"
        }
    }
}

dependencies {
    implementation 'org.pomodoro4j:pomodoro4j:v1.0.0'
}
```

### 1.3.2. Create Pomodoro instance with configuration

Basically, you can create a new Pomodoro instance in the following format.

```java
package demo.pomodoro4j;

import org.pomodoro4j.PomodoroFactory;
import org.pomodoro4j.config.Configuration;
import org.pomodoro4j.config.ConfigurationBuilder;

public class DemoPomodoro {

    public static void main(String[] args) {

        // Set the simple configuration of Pomodoro cycle
        final Configuration configuration = ConfigurationBuilder.newBuilder()
                                                                .setConcentrationMinutes(25)
                                                                .setBreakMinutes(5)
                                                                .setLongerBreakMinutes(15)
                                                                .setCountUntilLongerBreak(4)
                                                                .build();

        final Pomodoro pomodoro = PomodoroFactory.newInstance(configuration).getInstance()
    }
}
```

For the configuration builder object in the above example, specify arbitrary values for the following property fields.</br>
The initial values of each property are specified by referring to the Pomodoro Technique implementation method [here](https://en.wikipedia.org/wiki/Pomodoro_Technique).

| Property                      | Initial Value | Unit    | Description                                                                                                                                                                      |
| ----------------------------- | ------------- | ------- | -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| setConcentrationMinutes(int)  | 25            | Minutes | A property representing the concentration time in the Pomodoro Technique. The unit is minutes and an integer can be specified. The default value is specified as 25 (minutes).   |
| setBreakMinutes(int)          | 5             | Minutes | A property representing the break time in the Pomodoro Technique. The unit is minutes and an integer can be specified. The default value is specified as 5 (minutes).            |
| setLongerBreakMinutes(int)    | 15            | Minutes | A property representing the longer break time in the Pomodoro Technique. The unit is minutes and an integer can be specified. The default value is specified as 15 (minutes).    |
| setCountUntilLongerBreak(int) | 4             | Counts  | A property representing the count until longer break in the Pomodoro Technique. The unit is count and an integer can be specified. The default value is specified as 4 (counts). |

### 1.3.3. Start Pomodoro cycle

```java
package demo.pomodoro4j;

import org.pomodoro4j.PomodoroFactory;
import org.pomodoro4j.config.Configuration;
import org.pomodoro4j.config.ConfigurationBuilder;

public class DemoPomodoro {

    public static void main(String[] args) {

        // Set the simple configuration of Pomodoro cycle
        final Configuration configuration = ConfigurationBuilder.newBuilder()
                                                                .setConcentrationMinutes(25)
                                                                .setBreakMinutes(5)
                                                                .setLongerBreakMinutes(15)
                                                                .setCountUntilLongerBreak(4)
                                                                .build();

        final Pomodoro pomodoro = PomodoroFactory.newInstance(configuration).getInstance()

        // Start Pomodoro cycle
        pomodoro.start();

        // The entire Pomodoro cycle is performed in this while clause.
        // With the above property value setting,
        // concentration and rest will be performed four times, with a longer rest at the end.
        while (pomodoro.isOngoing()) {

            // When the above property value of 25 minutes has elapsed, the break time begins.
            if (pomodoro.shouldStartBreak()) {
                // Break and long break are determined by internal processing, so just call startBreak().
                pomodoro.startBreak();

                // All cycles during the break will be performed in this while clause.
                while (pomodoro.isBreakOngoing()) {

                    // When the above property value of 5 (normal break) or 15 (longer break) minutes has elapsed, the break time ends.
                    if (pomodoro.shouldEndBreak()) {
                        // If it is a long break,
                        // it means that the entire Pomodoro cycle is complete at this point.
                        pomodoro.endBreak();
                    }
                }
            }
        }
    }
}
```

## 1.4. License

```license
Copyright 2021 Kato Shinya.

Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
in compliance with the License. You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software distributed under the License
is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
or implied. See the License for the specific language governing permissions and limitations under
the License.
```

## 1.5. More Information

`Pomodoro4J` was designed and implemented by Kato Shinya, who works as a freelance developer.

Regardless of the means or content of communication, I would love to hear from you if you have any questions or concerns. I do not check my email box very often so a response may be delayed, anyway thank you for your interest!

- [Creator Profile](https://github.com/myConsciousness)
- [Creator Website](https://myconsciousness.github.io)
- [License](https://github.com/myConsciousness/pomodoro4j/blob/master/LICENSE)
- [Release Note](https://github.com/myConsciousness/pomodoro4j/releases)
- [Package](https://github.com/myConsciousness/pomodoro4j/packages)
- [File a Bug](https://github.com/myConsciousness/pomodoro4j/issues)
- [Reference](https://myconsciousness.github.io/pomodoro4j/package-summary.html)
