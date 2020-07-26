from django.db import models
from django.contrib.auth.models import User
from users.models import *
from django.core.validators import MaxValueValidator,MinValueValidator

#information about each task
class ToDoTask(models.Model):
    name=models.CharField(null=False,max_length=50)
    status=models.CharField(max_length=20,null=False) #status of the task
    description=models.TextField()
    toNotify=models.CharField(max_length=50,null=False)
    users_assigned=models.ManyToManyField(UserProfile)
    due_date = models.DateTimeField(null=False)
    rating = models.IntegerField()
    completion_percentage=models.IntegerField(default=0)
    compensation = models.IntegerField(default=0)
    #paid = 
    
#information about each meeting
class Meeting(models.Model):
    #attended_users = models.ForeignKey(UserProfile, on_delete=models.CASCADE)
    attended_users=models.ManyToManyField(UserProfile)
    date_time = models.DateTimeField(null=False) 

#information about the daily progress
class DailyProgress(models.Model):
    date_time = models.DateTimeField(auto_now_add=True)
    task = models.OneToOneField(ToDoTask, on_delete=models.CASCADE)
    progress_text = models.TextField()
    image = models.ImageField(default='default.jpg', upload_to="Progress_Images", null=False)