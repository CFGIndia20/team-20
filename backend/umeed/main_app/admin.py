from django.contrib import admin
from .models import *

@admin.register(ToDoTask)
class AdminTask(admin.ModelAdmin):
    pass

@admin.register(DailyProgress)
class AdminProgress(admin.ModelAdmin):
    pass

@admin.register(Meeting)
class AdminMeeting(admin.ModelAdmin):
    pass