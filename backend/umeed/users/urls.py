from users.views import *
from django.urls import path

urlpatterns = [
    path('user_login/',user_login),
    path('user/register',user_register)
    # path('manager_login/',delete_query),
    # path('user_logout/',edit_query),
    # path('manager_logout',create_query),
]