---
title: Overview description: Project summary and users menu: Overview order: 0
---

## Summary

**Sip & Score** is an app intended to allow users to rate new cocktail drinks and save new bars.
Users will be able to take pictures of the drink and give the drink a drinkRating with a comment.
There will be an interactive map to save new bars they are at.

[Target Users and User Stories](../personal-android-project-rbrazell1/users.html)<br>

[App Core Functionality](../personal-android-project-rbrazell1/functionality.html)<br>

[Wireframe Flow Chart](../personal-android-project-rbrazell1/wireframe.html)<br>

[Persistent Data to be Stored on the Device](../personal-android-project-rbrazell1/persistent-data.html)<br>

[Data Model Implementation](../personal-android-project-rbrazell1/data-model-implementation.html)<br>

[Entity Relationship Diagram for Persistent Data](../personal-android-project-rbrazell1/erd.html)<br>

[Device and External API's](../personal-android-project-rbrazell1/api.html)<br>

[Future Implementation Goals](../personal-android-project-rbrazell1/goals.html)<br>

**Sip & Score** is an app intended to allow users to rate new cocktail drinks and save new bars. In
the release version of the app a user will be able to take a picture of a new drink and attach a
rating before saving it to their list of drinks. The rating will include the picture, a title for
the picture, a zero-to-five star rating, a small description of the picture (or any other comment
about it), and the bar the drink was made at. The drinks will be displayed on a list view on the
home page of the app to help the user remember and decide which is the best drink to order. When a
user wants to find a new bar they will go to the map screen where they will find a map centered on
their current location. The map will be displaying an interactive search for near-by bars. As a
final feature the bars screen will host a searchable list of saved bars for quick access to find the
bar again or find any drinks that came from that bar.

One aspect that interested me the most about making an app of this nature was the expansion
potential. While the release version of the app does not include a way to share drinks with other
users it is my intention to continue development to the point where that is possible. At that point
the app will include a way to search all drinks to find average user rating and where has the best
version of a drink. I feel that the current app is useful to users that like to try new thing and
experience new places; and want to be able to make informed suggestions as to where to go out next
with their friends.

The current functionality of the app, at this time, does not yet include all of the intended
functions. At present the app does as follows: displays a list of drinks with a part of the rating
next to it, when a drink is selected it brings up the full rating card( the card is not yet auto
populating ), the add a drink button allows the user to select an image and fill out a rating card
to save the new drink, the add a bar button allows a user to save a bar, the bar list will display
the saved bars when searched, and the map fragment shows an embedded google maps screen. A few of
the unrealized features include: the map starting centered on the user location, and adding a bar to
your bar list from the maps screen, and lastly when adding a new drink the user can not yet directly
take a picture to save with the drink.

Next steps before release to prototype:
Have the drink rating card auto populate Have the full bar rating card show up auto populated Fix
the bug to bring up the add a bar dialog at the correct times Implement the camera function The map
to start centered on the user The map to start with a search of near-by bars Link the list of bars
to the map to give directions when selected Styling and ascetics to improve before release:
Over all consistent font size and locations Consistent and on brand color coordination More clearly
defined buttons and controls Background colors/ images for all screens Key stretch goals Add a way
share drinks with other users Have drinks upload to a shared database to find average ratings Allow
other users to see individual drink ratings 





## Copyright (c) 2021, Russell Brazell.