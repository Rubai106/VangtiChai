VangtiChai — CSE 489 Assignment 1
=================================

What it does
------------
- Enters an amount using a numeric keypad built from Android XML views (no system keyboard).
- Appends each tapped digit on the right, so 2, 3, 4 produces 234.
- CLEAR resets the amount to zero.
- Calculates the number of each Taka note using denominations 500, 100, 50, 20, 10, 5, 2, 1.
- Saves the entered amount across orientation changes and restores the note counts.
- Uses ConstraintLayout and separate XML layout resources for phone/tablet and portrait/landscape configurations.
- Text sizes, padding, and margins are defined in dimens.xml resources, not hardcoded in layout XML.

Requirements
------------
- Android Studio (a recent version, with Android SDK Platform 35 installed).
- JDK 17 or newer (Android Studio's bundled JDK is recommended).
- Internet access on the first Gradle sync to download dependencies.

How to run
----------
1. Extract the VangtiChai.zip file to a folder.
2. Open Android Studio.
3. Choose File > Open and select the extracted VangtiChai folder (the folder containing settings.gradle).
4. Wait for Gradle Sync to finish. If asked to install Android SDK Platform 35 or Build Tools, accept the installation.
5. Create an emulator using Tools > Device Manager > Create Device. A Pixel XL profile is recommended for phone testing. For tablet testing, create a Nexus 10 profile or a tablet with an approximately 800 x 1280 dp display.
6. Select the emulator in the device dropdown and press Run (green triangle), with the run configuration set to app.
7. Test portrait and landscape by using the emulator's Rotate buttons or the Extended Controls. The amount should remain unchanged after rotation.

Suggested required test matrix
------------------------------
- Pixel XL, portrait (approximately 411 x 731 dp)
- Pixel XL, landscape
- Nexus 10, portrait (approximately 800 x 1280 dp)
- Nexus 10, landscape

Additional checks
-----------------
- Tap 2, 3, 4: display should become 234.
- For 688, note counts should be 500: 1, 100: 1, 50: 1, 20: 1, 10: 1, 5: 1, 2: 1, 1: 1.
- CLEAR should return the amount and all note counts to zero.
- Rotate while an amount is entered: amount and counts should persist.
- The app accepts up to 12 digits to prevent impractically long values.

Device testing note
------------------
The project includes resource alternatives intended for the four assignment configurations. This source package was not executed on Android emulators in the file-generation environment; please run the test matrix above in Android Studio and record the actual device/emulator versions you tested before submitting. Do not claim a device was tested unless you have run it.

BUILD FIX (JVM TARGET)
----------------------
The app module configures both Java and Kotlin to target JVM 1.8. This avoids the
'Inconsistent JVM-target compatibility' error when the IDE is running on a newer JDK.
The JDK used to run Gradle may still be newer; that is separate from the app bytecode target.

If Android Studio still reports a build error:
1. Use File > Sync Project with Gradle Files.
2. Open Settings > Build, Execution, Deployment > Build Tools > Gradle.
3. Select the embedded JDK (or JDK 17 if available) as the Gradle JDK.
4. Run Build > Clean Project, then Build > Make Project.

