from django.shortcuts import render
from django.contrib.auth.models import User
from users.models import UserProfile

def user_login(request):
     if request.method=="POST":
        phno=request.data.get('phone')
        passw=request.data.get('password')
        skills=request.data.get('skills')
        area=request.data.get('area')
        user_obj=User.objects.create_user(username=phno,password=passw)
        user_obj.save()
        UserProfile.objects.create(user_acc=user_obj,phn_number=phno,skills=skills,area=area)

def user_register():
    pass

def manager_login():
    pass

def manager_register():
    pass
