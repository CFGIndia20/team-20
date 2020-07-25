from users.models import *
from .models import *
from rest_framework import serializers
from users.serializers import UserSerializer

class TaskSerializer(serializers.ModelSerializer):
    users_assigned=UserSerializer(read_only=True,many=True)
    class Meta:
        model = ToDoTask
        fields = '__all__'