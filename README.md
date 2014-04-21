mECE
====

mECE, a prototype application for the University of Texas Electrical and Computer Engineering Department, allows students to create study groups, view professor check-ins, and check local bus times.  The application, which was commissioned by the department chair as a senior design project, fills a need to move the student community to a virtual space in light of the demolition of a key engineering building on UT's campus.


Key Libraries and Dependencies
==========================

## Libraries
Parse - Included in the libs directory; allows interaction between application and Parse back-end.  This will become unnecessary once the application is integrated with UT's servers.

TestApp - This contains the body of our program.

AppCompat - This will be included in the Android SDK.

PullToRefresh - Downloaded from here: https://github.com/chrisbanes/ActionBar-PullToRefresh.  This library enables a pull-to-refresh feature for the Study Groups and Professor Check-Ins features.

SmoothProgressBar - Downloaded from here: https://github.com/dentex/SmoothProgressBar/tree/eclipse-port.  This library enables the progress bar, which is used for pull-to-refresh.

## Dependencies
This should be done for you, but if you have build errors, the following steps may be necessary.

Once you have imported all of the listed libraries (excluding Parse, which is included in TestApp), please right-click each folder and select "Properties".  On the pop-up menu, select "Android" on the left navigation bar, and check "Is Library" for every folder except TestApp.  Following this step, follow the same right-click -> "Properties" -> "Android" process and add the following libraries to the bottom boxes:

actionbarcompat: android-support-v7-appcompat, library
library: SmoothProgressBar-eclipse-port
TestApp: android-support-v7-appcompat, actionbarcompat, library


Source Files
==========

BusActivity - sets up interactive drop-down menus for bus times

FeedReaderContract - holds column names for Parse database tables

MainActivity - sets up home screen.  This will be changed to accommodate a User Login feature.

NewProfessorActivity - controls process for adding a Professor Check-In.

NewStudentActivity - controls process for adding a Study Group.

ProfessorActivity - controls process for listing Professor Check-Ins.

StudyActivity - controls process for listing Study Groups.


Layout Files
==========

activity_bus - XML layout for bus times

activity_main - XML layout for home page with radio buttons

activity_new_professor - XML layout for adding a professor check-in

activity_new_student - XML layout for posting a study group

activity_professor - XML layout for displaying professor check-ins

activity_student - XML layout for displaying study groups

fragment_main_dummy - default XML layout

Adding Features
=============

## User Login
Adding a user login feature will require some modifications to MainActivity.java, particularly in the onCreate method.  A new Intent for LoginActivity should be called from onCreate, and the userGroup and submit parameters should be removed.  If LoginActivity returned a type for User, that parameter could be used to determine where ActionBar buttons sent the user around the application.  A Student User should be able to view Study Groups, Professor Check-Ins, and Bus Times and should be able to create Study Groups.  A Professor User should be able to view Study Groups, Professor Check-Ins, and Bus Times and should be able to create Professor Check-Ins.  Anyone else should be removed from the application without being able to view anything.

## Back-end Integration
Right now, the application sends and receives information through a cloud database called Parse.  Once UT adopts this application, the data will need to be made more secure, so the storage source will need to change.  We currently send data to Parse in NewProfessorActivity.java and NewStudentActivity.java and receive data in ProfessorActivity.java and StudyActivity.java.  Posting occurs on Lines 125-136 of NewProfessorActivity.java and Lines 128-139 of NewStudentActivity.java.  Retrieving data occurs on Lines 129-138 of ProfessorActivity.java and Lines 145-153 of StudyActivity.java.  These lines should be revisited and modified to integrate the application with the UT servers instead of the Parse back-end.


Collaborators
===========

Eric Lucha

Michael Mahoney

Rex Richardson

Brian Salyer

Bobby Woolweaver


Special Thanks to Our Commissioners
==============================

Dr. Ahmed Tewfik, Ph.D.

Dr. Dewayne Perry, Ph.D.

Dr. Michael Becker, Ph.D.

Dr. Gary Hallock, Ph.D.

Tejaswini Ganapathi

Allison Alford

Ludmila Krivitsky
