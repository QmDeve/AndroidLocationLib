<div align="center">

## Android Location Lib
**A lightweight Android Location Library that provides easy-to-use APIs for GPS positioning**

<br>

[![GitHub](https://img.shields.io/badge/GitHub-Repository-black?logo=github)](https://github.com/QmDeve/AndroidLocationLib)
[![License](https://img.shields.io/github/license/QmDeve/AndroidLocationLib.svg?logo=github&color=blue&label=License)](https://github.com/QmDeve/AndroidLocationLib/blob/master/LICENSE)

[![Maven Central Version](https://img.shields.io/maven-central/v/com.qmdeve.location/location?label=Maven%20Central)](https://central.sonatype.com/artifact/com.qmdeve.location/location)
[![JitPack](https://jitpack.io/v/com.qmdeve/AndroidLocationLib.svg)](https://jitpack.io/#com.qmdeve/AndroidLocationLib)

[![Telegram Groups](https://img.shields.io/badge/Telegram%20Groups-2CA5E0?style=brightgreen&logo=telegram&logoColor=white)](https://t.me/QmDeve)
[![XChat Groups](https://img.shields.io/badge/XChat%20Groups-202020?style=brightgreen&logo=x&logoColor=white)](https://x.com/i/chat/group_join/g2048366788006846529/LNvKUQelO9)

</div>

## Integration
[![Maven Central Version](https://img.shields.io/maven-central/v/com.qmdeve.location/location?label=Maven%20Central)](https://central.sonatype.com/artifact/com.qmdeve.location/location)

Add the dependencies to your module's `build.gradle` file:

```gradle
dependencies {
    implementation 'com.qmdeve.location:location:1.0.0'
}
```

## Use
## Permissions
Add to `AndroidManifest.xml`:
```xml
<uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
<uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />
```

You need to check and request the location permission in your application yourself

### Initialize
Kotlin:
```kotlin
FastLocation.init(this)
```

Java:
```java
FastLocation.INSTANCE.init(this);
```

### Single Location

Kotlin:
```kotlin
FastLocation.singleLocation(
    object : LocationCallback {
        override fun onLocation(location: Location) {
            // Callback
        }
    }
)
```

Java:
```java
FastLocation.INSTANCE.singleLocation(new LocationCallback() {
    @Override
    public void onLocation(@NonNull Location location) {
        // Callback
    }
});
```

### Continuous Positioning

kotlin:
```kotlin
var locationHandle: LocationHandle? = null

locationHandle = FastLocation.startLocation(
    object : LocationCallback {
        override fun onLocation(location: Location) {
            // Callback
        }
    }
)
```

java:
```java
LocationHandle locationHandle = null;

locationHandle = FastLocation.INSTANCE.startLocation(new LocationCallback() {
    @Override
    public void onLocation(@NonNull Location location) {
        // Callback
    }
});
```

### Stop Continuous Positioning

kotlin:
```kotlin
locationHandle?.stop()
```

java:
```java
locationHandle.stop();
```