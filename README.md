SGC-App
=======

Android Application for Amatuer Astronomy Club (formerly known as Star Gazing Club), NITK Surathkal.

Purpose
-------

This app was made before Engineer 2013 (mid September). The purpose was to spread awareness about the events conducted by Astro Committee during Engineer.

Features
--------

1. Download the latest releases of all the newletter and magazines by AAC, NITK. The server where the magazine was being hosted was a free one. It's not working anymore as of now.
2. Calculate your weight on different planets and other celestial objects. Just Enter your weight on Earth
3. See and know about all the members of the club. The list is out-dated as of now.
4. Contact AAC, NITK directly from the application
5. Custom Styles for GUI Elements - New Icons and stuff like that
6. Animations in List Views and Activity transitions

Installation and Running
------------------------

Copy the SGC/bin/SGC.apk file to you Android powered device and install the application. You might get a warning saying that the origin of app could not be verified (as the app is not from play store). Click continue and grant all required permission. Open the app. Use it. Enjoy.

Development
-----------

1. This app was developed using Android SDK Plugin and Eclipse. However, Android Studio has become the new standard IDE for Android Application Development. The SGC folder can be imported as an Eclipse project in Android Studio IDE. Original target platform for Jellybean.
2. The strings.xml file defines several important variables like server url which can be configured from there.
3. Follow a good coding convention to ensure maintainability

Suggested Work
--------------

I didn't work much on this application since an year. However I thought of the following todo's ->

1. Update Server URLs. Make the Digital Magazine thing work - Host all the newsletters on paid servers
2. Use relative font size and other dimension in calculate weight section. It looks weird on high-res phones
3. Use a common font across the app. Develop a color theme if possible. The current fonts are funky and not uniformity
4. Currently People list and their pics are stored locally on app in assets folder- which is bad. Try to host list on server. Use Database and Sync Adapter to fetch images from server and store data. This task will take a lot of time to implement and you will learn a lot.
5. The digital magazine uses a polling mechanism. It's a user-triggered polling mechanism not periodic polling. Use sync adapter instead. Auto Sync with server. Async Task can be avoided and it will also take care of network failures.
6. Make app-size smaller. Use vector graphic images instead of bitmap and stuff.

Contributor
-----------

1. Ashish Kedia (ashish1294@gmail.com)
