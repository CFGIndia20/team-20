from django.shortcuts import render
from django.contrib.auth.models import User
from users.models import *
from django.contrib.auth import authenticate,login,logout
from rest_framework.decorators import api_view,permission_classes
from rest_framework.status import HTTP_400_BAD_REQUEST,HTTP_404_NOT_FOUND,HTTP_200_OK
from django.views.decorators.csrf import csrf_exempt
from rest_framework.permissions import AllowAny,IsAuthenticated
from rest_framework.authtoken.models import Token
from rest_framework.response import Response
from users.serializers import UserSerializer

#dummy test endpoint 
@csrf_exempt
@api_view(['POST'])
@permission_classes((AllowAny,))
def dummy_view(request):
    return Response("OK",status=HTTP_200_OK)

#endpoint for user login
@csrf_exempt
@api_view(['POST'])
@permission_classes((AllowAny,))
def user_login(request): #login a user
    phno=request.data.get('phone')
    passw=request.data.get('password')
    u=authenticate(username=phno,password=passw)
    if not u:
        return Response({'status':'failure','data':{'message':'wrong username or password'}},status=HTTP_400_BAD_REQUEST)
    token,x=Token.objects.get_or_create(user=u)
    return Response({'status':'success','data':{'message':token.key}},status=HTTP_200_OK)

#endpoint for user signup
@csrf_exempt
@api_view(['POST'])
@permission_classes((AllowAny,))
def user_register(request):
    first_name=request.data.get('first_name')
    last_name=request.data.get('last_name')
    phno=request.data.get('phone')
    passw=request.data.get('password')
    skills=request.data.get('skills')
    area=request.data.get('area')
    if User.objects.filter(username=phno).exists():
        return Response({'status':'failure','data':{'message':'Phone number already exists'}},status=HTTP_400_BAD_REQUEST)
    user_obj=User.objects.create_user(username=phno,password=passw,first_name=first_name,last_name=last_name)
    user_obj.save()
    UserProfile.objects.create(user_acc=user_obj,skills=skills,area=area)
    return Response({'status':'success','data':{'message':'Registration Successful'}},status=HTTP_200_OK)

#endpoint for manager login
@csrf_exempt
@api_view(['POST'])
@permission_classes((AllowAny,))
def manager_login(request):
    uname=request.data.get('username')
    passw=request.data.get('password')
    u=authenticate(username=uname,password=passw)
    if not u:
        return Response({'status':'failure','data':{'message':'wrong username or password'}},status=HTTP_400_BAD_REQUEST)
    linked_manager_obj=Manager.objects.get(user_acc=u)
    profile_status="Completed"
    token,x=Token.objects.get_or_create(user=u)
    if linked_manager_obj.unset==True:
        profile_status="Incomplete"
    return Response({'status':'success','data':{'message':token.key}},status=HTTP_200_OK)

#endpoint for manager to change account details after first signup
@csrf_exempt
@api_view(['POST'])
@permission_classes((AllowAny,))
def manager_register(request):
    u=request.user
    new_passw=request.data.get('password')
    u.set_password(new_passw)
    u.first_name==request.data.get('first_name')
    u.last_name=request.data.get('last_name')
    u.email=request.data.get('email')
    linked_manager_obj=Manager.objects.get(user_acc=u)
    linked_manager_obj.phone=request.data.get('phone')
    linked_manager_obj.area=request.data.get('area')
    linked_manager_obj.unset=False
    linked_manager_obj.save()
    u.save()
    return Response({'status':'success','data':{'message':'Completed'}},status=HTTP_200_OK)

#manager logout
@csrf_exempt
@api_view(['POST'])
@permission_classes((AllowAny,))
def manager_logout(request):
    request.user.auth_token.delete()
    logout(request)
    Response({'status':'success','data':{'message':'Logged out'}},status=HTTP_200_OK)

#user logout
@csrf_exempt
@api_view(['POST'])
@permission_classes((AllowAny,))
def user_logout(request):
    request.user.auth_token.delete() 
    logout(request)
    Response({'status':'success','data':{'message':'Logged out'}},status=HTTP_200_OK)

# Fetch All User profiles
@api_view(['GET','POST'])
def fetch_users(request):
    #fetch all the user objects
    user_details = UserProfile.objects.all()
    #serialize the users
    serializer = UserSerializer(user_details, many=True)
    #return Response using rest_framework's response
    return Response({'status':'success','data':{'message':serializer.data}})

# User Info
@api_view(['POST'])
def get_user_info(request):
    dat=UserSerializer(UserProfile.objects.get(user_acc=request.user)).data
    return Response({'status':'success','data':{'message':dat}})


