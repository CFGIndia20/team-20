from main_app.views import *
from django.urls import path

urlpatterns = [
    path('compensation/',CompensetaionView),
    # path('manager_login/',delete_query),
    # path('user_logout/',edit_query),
    # path('manager_logout',create_query),
]