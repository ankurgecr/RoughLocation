RoughLocations
 ===========

 Getting started
 ---------------

 This library allows you to find the user's approx location based on network call.
 No Location specific permission check required. Simple and Easy!

 This library DO NOT need following permissions

 ~`<uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />`~ <br />
 ~`<uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />`~

 To get started with RoughLocations, you'll need to get
 add the dependency to your project's (app level) build.gradle file:

 ```
 dependencies {
     //other dependencies
     implementation 'info.ankurpandya.roughlocation:roughlocation:1.0.4'
 }
 ```
 and sync up your project.

 Good. Now you are all set to use RoughLocations.

 Retriving Rough Location
 --------
 In order to retrive the location, call this method:

 ```
RoughLocationFetcher.getLocationInBackground(context, new LocationListener() {
    @Override
    public void onLocationReceived(RoughLocation roughLocation) {
        //handle location success
    }

    @Override
    public void onLocationFailed() {
        //handle location failed
    }

    @Override
    public void onNetworkError() {
        //handle network error
    }
});
 ```

The response in `RoughLocation` object contains following information:
 ```
"status": "success",
"country": "Singapore",
"countryCode": "SG",
"region": "",
"regionName": "",
"city": "Queenstown Estate",
"zip": "",
"latitude": 1.12954,
"longitude": 143.79,
"timezone": "Asia/Singapore",
"isp": "StarHub Cable Vision Ltd",
"org": "SGCABLEVISION",
"autonomousSystem": "AS55987 Starhub Ltd",
"query": "112.47.238.20"
 ```

 As the name suggests, this location is approximate and totally based on internet setting of user. If the User is using VPN or network configurations, the response may not show the correct information.

 Debugging
 --------
 In order to enable the logs, use this:
 ```
 RoughLocationFetcher.setDebug(true);
 ```
 Demo
 --------
 Check the demo `app` for more details.