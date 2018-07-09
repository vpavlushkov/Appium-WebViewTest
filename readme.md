# Appium tests for Kyyti-app

## Manually upload to Appcenter

* Pack project for upload: `mvn -DskipTests -P prepare-for-upload package`
* Run tests: `appcenter test run appium --app "Tuup/Kyyti-app" --devices "Tuup/pixel-2-xl" --app-path "pathToFile.apk" --test-series "appium" --locale "en_US" --build-dir target/upload`

## Documentation

* https://docs.microsoft.com/en-us/appcenter/test-cloud/preparing-for-upload/appium
