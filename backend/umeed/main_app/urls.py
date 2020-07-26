from main_app.views import *
from django.urls import path

urlpatterns = [
    path('create_meeting',create_meeting),
    path('get_meetings',get_all_meetings),
    path('mark_attendance',mark_attendance),
    path('check_attendance',check_attendance),
    path('view_attendance',view_attendance),
    path('post_update',post_update),
    path('get_progress',get_progress),
    path('accept_task',accept_task),
    path('reject_task',reject_task),
    path('create_task',create_task),
    path('end_task_assignment',user_threshold),
    path('fetch_unassigned',fetch_unassigned),
    path('rate_task',rate_task),
    path('set_compensation',CompensetaionView)
]