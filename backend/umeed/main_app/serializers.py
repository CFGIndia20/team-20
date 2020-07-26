from users.models import *
from .models import *
from rest_framework import serializers
from users.serializers import UserSerializer

class TaskSerializer(serializers.ModelSerializer):
    users_assigned=UserSerializer(read_only=True,many=True)
    class Meta:
        model = ToDoTask
        fields = '__all__'


class ProgressSerializer(serializers.ModelSerializer):
    task = TaskSerializer()
    class Meta:
        model = DailyProgress
        fields = '__all__'

class MeetingSerializer(serializers.ModelSerializer):
    attended_users=UserSerializer(read_only=True,many=True)
    class Meta:
        model= Meeting
        fields='__all__'
    
