# pug-life-app
Pug Life is an app that serves user with a list of images of Pugs. "Pug" is a breed of dogs.

## Features:
* To provide a collection of Pug images to user, the app connects to Dog API on https://dog.ceo/dog-api/
* Images are shown to user in a Grid View.
* To render images from URLs to ImageViews, Square's Picasso library is used. By using Picasso, Buffer-overflow exception is prevented which occurs when dealing with lots of 
images to be downloaded from a remote source. Using libraries also leads to cleaner code. For more information on Picasso please refer to: http://square.github.io/picasso/
* *Note that the Pug Life logo used in this app is found online from Spreadshirt.com and its rights are not belong to me*.
