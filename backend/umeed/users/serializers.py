from rest_framework import serializers
from .models import UserProfile
from django.contrib.auth.models import User

class UserAuthSerializer(serializers.ModelSerializer):
    class Meta:
        model=User
        fields= ('first_name','last_name','username')

class UserSerializer(serializers.ModelSerializer):
    user_acc=UserAuthSerializer()
    class Meta:
        model = UserProfile
        fields = '__all__'