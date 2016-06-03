# MusicPlayerUpdated


(1)Open the github link

(2)Clone/Download the project on to Android Studio

—>Steps to clone the project : 

    a) Copy the github link
    
    b) Open the terminal and go to the location you want to place the project
    
    c) Give the command ‘git clone <Github repo link>’
    
    d) Hit Enter
    
    e) Once the cloning is 100% done, continue with step (3)
    
—>Steps to download the project : 

    a) Download the zip from the github link
    
    b) Unzip the folder 
    
    c) Copy to the location you want your project and continue with step (3)
    
(3)Open the project using Android Studio

(4)Run the project



Music Player

This is an app used to play music and has features to fast forward or rewind the songs. Also have a feature of pausing and playing the song. 

Implementation :
 App launches by opening a list of songs along with the artist name image, song name, artist name and duration of the song as shown below:

In order to accomplish this I have used the Recycler view concept where in I reuse the holders as shown below. Data is fetched from the array list and populates accordingly.

Tapping on any view/song opens a player activity with music player seeker, action buttons, image of the artist, Song name, Artist name, Song duration and Song lyrics. Also the name of the song is set as the app bar text and has a back action, clicking on which goes to the previous activity. Player Activity looks as the below screen shot : 

![alt tag](https://github.com/gouthamim/MusicPlayerUpdated/blob/master/app/src/main/res/raw/001.png)

![alt tag](https://github.com/gouthamim/MusicPlayerUpdated/blob/master/app/src/main/res/raw/002.png)

