from django.db import models
from django.contrib.auth.models import User
from users.models import *
from django.core.validators import MaxValueValidator,MinValueValidator

#status values are= unassigned, assigned, accepted, rejected, in_progress and completed
#toNotify stores if anyone is to be notified
class ToDoTask(models.Model):
    name=models.CharField(null=False,max_length=50)
    status=models.CharField(max_length=20,null=False) #status of the task
    description=models.TextField()
    toNotify=models.CharField(max_length=50,null=False)
    users_assigned=models.ManyToManyField(UserProfile)
    date_time = models.DateTimeField(auto_now_add=True)

    #completion_percentage=models.IntegerField(default=0,validators=[MaxValueValidator(100),MinValueValidator(0)])
    
# class Task(models.Model):
#     task_name = models.CharField(null=False,max_length=20)
#     task_dsc = models.CharField(null=False,max_length=100)
#     task_img = models.ImageField(default='default.jpg', upload_to="",null=True)
"""
Model to keep track of attended users
"""
class Meeting(models.Model):
    #attended_users = models.ForeignKey(UserProfile, on_delete=models.CASCADE)
    attended_users=models.ManyToManyField(UserProfile)
    date_time = models.DateTimeField(auto_now_add=True) 

class DailyProgress(models.Model):
    rating = models.IntegerField()
    date_time = models.DateTimeField(auto_now_add=True)
    task = models.OneToOneField(ToDoTask, on_delete=models.CASCADE)
    progress_text = models.TextField()
    image = models.ImageField(default='default.jpg', upload_to="", null=False)