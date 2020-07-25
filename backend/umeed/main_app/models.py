from django.db import models
from django.contrib.auth.models import User
from users.models import *

class ToDoTask(models.Model):
    status=models.TextChoices()
    description=models.TextField()
    pass


"""
Model to keep track of attended users
"""
class Meeting(models.Model):
    attended_users = models.ForeignKey(UserProfile, on_delete=models.CASCADE)
    date_time = models.DateTimeField(auto_now_add=True) 
    attendance = models.BooleanField(default=True)
    pass


