# Tisk

[![Build Status](https://travis-ci.org/Commit451/Tisk.svg?branch=master)](https://travis-ci.org/Commit451/Tisk) [![](https://jitpack.io/v/Commit451/Tisk.svg)](https://jitpack.io/#Commit451/Tisk)

Convert from GCM Tasks to RxJava types

## Usage
Tasks can be converted to a `Single`, `Observable`, or `Completable`
```kotlin
//example using the Google Drive API. Works with any API that returns a task
val driveClient = Drive.getDriveClient(applicationContext, signInAccount)
val task = driveClient.rootFolder
val folder = task.toSingle()
    .blockingGet()
```

License
--------

    Copyright 2018 Commit 451

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
