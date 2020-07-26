A simple and minimalistic Android application for Women associated with Umeed NGO to solve the challenges faced by them. The web app consists of a frontend using Android framework and a backend using Django Rest Framework. In order to run the web app the steps to be followed are:-

1.Clone the repository or download the zip version.
2.Download and install Python version 3.7.x or.
3.Navigate to the backend/umeed directory. This directory contains the backend code for the mobile app.
4.Run the following command $ pip install -r requirements.txt. This installs all the python modules required for running the backend server.
4.Add a secret.json file with the key "SECRET_KEY" and a value equal to the secret key, os.urandom can be used to generate this key as well. 
5.Start the server on port 8000 with the following command python manage.py runserver.

The web app is also hosted temporarily on ngrok, but may not be availabe at the time of testing because the host name changes on each execution. At the time of testing, the server is hosted on 96275e0db8c3.ngrok.io. This is added to the allowed hosts in settings.py as well. If a new test server is to be hosted, the instructions for this are:-

1. Download ngrok executable from https://ngrok.com/. Ngrok creates a secure tunnel to localhost can can be accessed by the mobile frontend application. 
2. Ngrox can then be made to tunnel to the port on which the django server is running by executing ./ngrok -http {port_number}.
3. The ngrok hostname must then be added to the allowed hostes list in the settings.py file.
4. The server is now started using the command python manage.py runserver

The backend consists of multiple API endpoints, some of which are written with future use in mind and have not been completely tested. Some of the API endpoints that are being used are :-

/ops: This is the prefix for all operations that are going to be performed. These endpoints include:-
/ops/create_meeting: Used by the manager to create a new meeting.
/ops/get_meetings: Used by the manager in order to get meeting ids, date and time , along with an attendance list
/ops/view_attendance: Used by the manager to view the attendance statistics for a particular user.
/ops/mark_attendance: Used by the manage to mark attendance for a particular meeting for a list of user accounts.
/auth/user_register: Used for user signup.
/auth/user_login: Used for user login. 

In order to use a manager account, it need to be created by a superuser only. Signup for manager is disabled. The manager is only provided the login function. The steps to create a manager account are:-

1. Create a superuser account with the command python manage.py createsuperuser.
2. On creation, the super user can access the admin dashboard on the path /admin.
3. Here, first a basic user account for authentication must be created.
4. This is then linked to a manager user account.
5. After following these steps the manager account can be used directly from the mobile app.

The android application by the name 'Umeed' has been developed by us made on Android Studio using Java. The android app consists of the following modules of functionality as specified in the code using a Navigation Architecture by Android (MVC-like architecture). The code has been divided into :

i) data - which has all the request and response models for the API Integration using Retrofit Service. The Preference Manager Class to cache/save all the data which will be required further ahead and the OkHTTP 2.0 library which has been used along with Retrofit.
ii)models - These are the View Models for the data definition along with the constructors mentioned for easy user/developer understanding. 
iii) ui -All the designs made in XML integrated with the Java Classes along with the View Model Providers for displaying the data obtained from (i) above.

The modules developed are mentioned below:
i) attendance : For individual attendance tracking and collective individual tracking by the manager who can log into the application
ii) dashboard : For displaying the brief of the tasks assigned to the user for that particular week along with progress bar representation for better data interpretation
iii) Experience Details : For mentioning and viewing the experiences of the tasks done previously to show the rigorous hardships also faced at times
iv)login and register - basic easy-to-understand authentication pages
v) manager_user - For displaying all the users which come under a manager and her area
vi) meeting - for creation and assignment of meetings
vii) profile - for displaying the user details along with compensation, attendance and skills equipped with 
viii) task_details - a detailed task progression displayed in order to keep a level-1 check allowing for a daily upload picture option (backend integration not done for the same due to time constraints)

For running the application :
1) A JRE would be required along with an SDK for Android for development purpose
2) A gradle version of 3.6.1+ is required
3) The minimum SDK version required would be 21 which is Android 5+, so 94.1% of the phones can have access to the app
4) There is no external data being accessed,so the application is free from such threats
5) Android Studio with the proper gradle configuration would be enough to go ahead and access the application

Final Note: All endpoints other than login and signup endpoints are protected and require an authentication header with a auth token  which is recieved as a response on successfully logging in.


