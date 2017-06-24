# WeatherTest
* Cookpad Weather app test 
Application Name : Weather app test 

Application Description : Shows wind, humidity, high and low temperature and wind information for your current location.

Technologies I used to Develope this app : 


* 1- Android Native Software Development tools (Android Studio)
* 2- Openweather API 
* 3- Smart Phones Integreated GPS systems


Setup :

Requirements : 
* Android Studio with SDK tools 
* Emulator or Android Device (Minimum API verision 17)

External Libs used : 
* Glide 
* Simple HTTPHunder
* Google Play Services 
* OkHTTP3

Config Files : 

* 1- Google maps API ->  Resources/String.xml
* 2-  Open weather API -> WeatherTest/app/src/main/java/test/ayman/weather/weathertest/Loading.java Line : 100

 
Activities Overview :- 

* 1- MainActivity.java	
* 2- Loading.java	
* 3- MapsActivity.java

First : 
* MainActivity.java  :- 
![Settings Window](https://raw.githubusercontent.com/0x4ym4n/WeatherTest/master/Screenshot_2017-06-25-01-08-58.png)

 
* I attached LocationManager  & LocationListener in this activity , I used 3 methods the first one is onProviderDisabled this take the user directly to Enable his GPS service , the second one is onProviderEnabled which works when the user has succsefully Enabled the GPS service to tell him it's done , The last one is onLocationChanged this take 3 values describes the current Location which are Location Lattitude & Longttitude and the Accuraccy 

* A button when pressed checking is the location is Null or not , if not will take the user to next acticity "Loading.java" 

Second :
* Loading.java :-
![Alt text](https://raw.githubusercontent.com/0x4ym4n/WeatherTest/master/Screenshot_2017-06-25-01-09-40.png "Optional title")


* first it's Asynctask Class Function with default methods , OnPreExecute , onPostExecute , doInBackground , 
first on PreExecute it created progress dialgoue , then onPostExecute it calls HTTPHunder and call the API end point corresponded by Long and Lat values that we got them from the previous activity , Saving JSON string into variable and parsing it's data to string vars and arraylist of hashmap 
finally onPostExecute Dissmis the dialgoue and Pass "Intent" Arraylist & String vars to the next activity "MapsActivity"

Third : 
* MapsActivity.java :-
![Alt text](https://raw.githubusercontent.com/0x4ym4n/WeatherTest/master/Screenshot_2017-06-25-01-09-54.jpg "Optional title")


* It recivied Arraylist & String vars , Setting Google maps fragment pointed to the current location , Creating Custom Listview Adapter , Retriving some images like Countrty Flag & Weather Main status from the internet using Glide Lib , converting milliseconds to Date , accsessing and update  TextViews and ImageViews .
