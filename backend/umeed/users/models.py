from django.db import models
from django.contrib.auth.models import User
from PIL import Image

#stores additional information about the user
class UserProfile(models.Model):
    user_acc=models.OneToOneField(User,on_delete=models.CASCADE,null=False)
    area = models.CharField(null=False,max_length=100)
    image = models.ImageField(default='default.jpg', upload_to="user_images",null=True)
    skills= models.TextField(null=False)

#stores additional data about the manager
class Manager(models.Model):
    phn_number = models.CharField(max_length=12,null=True)
    image = models.ImageField(default='default.jpg', upload_to="manager_images",null=True)
    area = models.CharField(null=False,max_length=100)
    user_acc=models.OneToOneField(User,on_delete=models.CASCADE,null=False)
    unset=models.BooleanField(default=True) #check if profile is filled


