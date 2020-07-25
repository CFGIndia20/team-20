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

def user_profile():
    if request.method == "GET" :

@api_view(['get'])
def fetch_users(request):
    #fetch all the user objects
    user_details = User.objects.all()
    #serialize the users
    serializer = UserSerializer(user_details, many=True)
    #return Response using rest_framework's response
    return Response(serializer.data)


