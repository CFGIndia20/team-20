from django.contrib import admin
from django.urls import path,include

urlpatterns = [
    path('admin/', admin.site.urls),
    path('auth/',include('users.urls')),
    path('ops/',include('main_app.urls')),
]
