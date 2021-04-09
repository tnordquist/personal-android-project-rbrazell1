## **Sip & Score** can help you keep track of every great drink and not so great drink with just a few clicks.

### Copyright (c) 2021, Russell Brazell.

# Build Instructions

### 1. Clone [Sip & Score](https://github.com/ddc-java-12/personal-android-project-rbrazell1).

### 2. Import the project to your IDE.

### 3. Add the properties files to the correct directory, the default(suggested) location is "$projectDir/../../../services/sip-and-score.properties".
#### 3.1 The build file needs to be in the following format .
```text

client_id = 746842365231-2scssvkquergtfcnv4qrc44c5a52q6le.apps.googleusercontent.com

google_maps_api_key = xxxxxxxxxxxxx_xxxxxxxxxxxxxxx_xxxxxxxxx 

(you will need to get a Google maps API key)

content_format = %s/content


```
#### 3.2 To change the location where you saved your properties file update the [connection_properties](build.gradle).

### 4. Build and install the app to your preferred device or emulator. It _MUST_ be at android API 26 or higher.

### 5. Follow the [Basic User Instructions](docs/instructions.md) to start testing the app.

### 6. Enjoy!