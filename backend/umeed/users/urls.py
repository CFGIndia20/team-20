from users.views import *
from django.urls import path

urlpatterns = [
    path('user_login',user_login),
    path('user_register',user_register),
    path('dummy_test',dummy_view),
    path('fetch_users',fetch_users),
    path('get_user_info',get_user_info),
    path('manager_login',manager_login),
    path('user_logout',user_logout),
    path('manager_reset',manager_register),
    path('manager_logout',manager_logout),
    # path('user_logout/',edit_query),
    # path('manager_logout',create_query),
]