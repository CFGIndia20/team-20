from django.db import models
from django.contrib.auth.models import User
from PIL import Image

class UserProfile(models.Model):
    user_acc=models.OneToOneField(User,on_delete=models.CASCADE,null=False)
    phn_number = models.CharField(max_length=12,null=False)
    area = models.CharField(null=False,max_length=100)
    image = models.ImageField(default='default.jpg', upload_to="",null=False)
    skills= models.TextField(null=False)

class Manager(models.Model):
    phn_number = models.CharField(max_length=12,null=False)
    image = models.ImageField(default='default.jpg', upload_to="",null=False)
    area = models.CharField(null=False,max_length=100)
    user_acc=models.OneToOneField(User,on_delete=models.CASCADE,null=False)

class SuperManager(models.Model):
    phn_number = models.CharField(max_length=12,null=False)
    image = models.ImageField(default='default.jpg', upload_to="",null=False)
    user_acc=models.OneToOneField(User,on_delete=models.CASCADE,null=False)


