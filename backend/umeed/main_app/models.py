from django.db import models
from django.contrib.auth.models import User
from users.models import *

class ToDoTask(models.Model):
    status=models.CharField(max_length=20,null=False) #status of the task
    description=models.TextField()
    toNotify=models.CharField(max_length=50,null=False)
    assigned=models.OneToOneField(UserProfile,on_delete=models.SET_NULL,null=True)

"""
Model to keep track of attended users
"""
class Meeting(models.Model):
    attended_users = models.ForeignKey(UserProfile, on_delete=models.CASCADE)
    date_time = models.DateTimeField(auto_now_add=True) 
    attendance = models.BooleanField(default=True)
    pass


class DailyProgress(models.Model):
    rating = models.IntegerField()
    date_time = models.DateTimeField(auto_now_add=True)
    text = models.TextField()
    image = models.ImageField(default='default.jpg', upload_to="", null=False)
