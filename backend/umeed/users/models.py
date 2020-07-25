from django.db import models
from django.contrib.auth.models import User
from PIL import Image


class UserProfile(models.Model):
    username = models.OneToOneField(User, on_delete=models.CASCADE)
    name = models.TextField()
    phn_number = models.IntegerField()
    address = models.TextField()
    image = models.ImageField(default='default.jpg', upload_to=#)


class Manager(models.Model):
    name = models.TextField()
    contact = models.IntegerField()
    area = models.TextChoices



