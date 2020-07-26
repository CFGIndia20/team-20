A simple and minimalistic Android application for Women associated with Umeed NGO to solve the challenges faced by them. The web app consists of a frontend using Android framework and a backend using Django Rest Framework. In order to run the web app the steps to be followed are:-

1.Clone the repository or download the zip version.
2.Download and install Python version 3.7.x or.
3.Navigate to the backend/umeed directory. This directory contains the backend code for the mobile app.
4.Run the following command $ pip install -r requirements.txt. This installs all the python modules required for running the backend server.
4.Add a secret.json file with the key "SECRET_KEY" and a value equal to the secret key, os.urandom can be used to generate this key as well. 
5.Start the server on port 8000 with the following command python manage.py runserver.

The web app is also hosted temporarily on ngrok, but may not be availabe at the time of testing because the host name changes on each execution. At the time of testing, the server is hosted on 96275e0db8c3.ngrok.io. This is added to the allowed hosts in settings.py as well. If a new test server is to be hosted, the instructions for this are:-

1. Download ngrok executable from https://ngrok.com/.Ngrok creates a secure tunnel to localhost can can be accessed by the mobile frontend application. 
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

Final Note: All endpoints other than login and signup endpoints are protected and require an authentication header with a auth token  which is recieved as a response on successfully logging in.

