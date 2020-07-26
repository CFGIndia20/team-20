from django.contrib.auth.models import User
from django.contrib import admin
from .models import *

# @admin.register(User)
# class AdminUser(admin.ModelAdmin):
#     fields='__all__'

@admin.register(Manager)
class AdminManager(admin.ModelAdmin):
    pass
@admin.register(UserProfile)
class AdminRegularUser(admin.ModelAdmin):
    pass
