from django.db import models
from django.contrib.auth.models import User
from users.models import *

class ToDoTask(models.Model):
    status=models.TextChoices()
    description=models.TextField()
    pass


