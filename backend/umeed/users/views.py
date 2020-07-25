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
from rest_framework.serializers import UserSerializer

@csrf_exempt
@api_view(['POST'])
@permission_classes((AllowAny,))
def dummy_view(request):
    return Response("OK",status=HTTP_200_OK)

@csrf_exempt
@api_view(['POST'])
@permission_classes((AllowAny,))
def user_login(request): #login a user
    phno=request.data.get('phone')
    passw=request.data.get('password')
    u=authenticate(username=phno,password=passw)
    if not u:
        return Response({'error':'wrong username or password'},status=HTTP_400_BAD_REQUEST)
    token,x=Token.objects.get_or_create(user=u)
    return Response({'auth_token':token.key},status=HTTP_200_OK)

@csrf_exempt
@api_view(['POST'])
@permission_classes((AllowAny,))
def user_register(request):
    phno=request.data.get('phone')
    passw=request.data.get('password')
    skills=request.data.get('skills')
    area=request.data.get('area')
    user_obj=User.objects.create_user(username=phno,password=passw)
    user_obj.save()
    UserProfile.objects.create(user_acc=user_obj,phn_number=phno,skills=skills,area=area)
    return Response({'success':'Registration successful'},status=HTTP_200_OK)

@csrf_exempt
@api_view(['POST'])
@permission_classes((AllowAny,))
def manager_login(request):
    uname=request.data.get('username')
    passw=request.data.get('password')
    u=authenticate(username=uname,password=passw)
    if not u:
        return Response({'error':'wrong username or password'},status=HTTP_400_BAD_REQUEST)
    return Response({'status':'success'},status=HTTP_200_OK)

@csrf_exempt
@api_view(['POST'])
@permission_classes((AllowAny,))
def manager_register(request):
    uname=request.data.get('username')
    passw=request.data.get('password')
    firstname=request.data.get('first_name')
    lastname=request.data.get('last_name')
    email=request.data.get('email')
    phone=request.data.get('phone')
    area=request.data.get('area')
    user_obj=User.objects.create_user(username=uname,password=passw,first_name=firstname,last_name=lastname,email=email)
    Manager.objects.create(area=area,user_acc=user_obj,phn_number=phone)
    return Response({'status':'success'},status=HTTP_200_OK)

@csrf_exempt
@api_view(['POST'])
@permission_classes((AllowAny,))
def manager_logout(request):
    request.user.auth_token.delete()
    logout(request)
    return Response()

@csrf_exempt
@api_view(['POST'])
@permission_classes((AllowAny,))
def user_logout(request):
    request.user.auth_token.delete() 
    logout(request)
    return Response()

# Fetch All User profiles
@api_view(['GET'])
def fetch_users(request):
    #fetch all the user objects
    user_details = User.objects.all()
    #serialize the users
    serializer = UserSerializer(user_details, many=True)
    #return Response using rest_framework's response
    return Response(serializer.data)

# User Info
@api_view(['POST'])
def get_user_info(request):
    dat=UserSerializer(request.user).data
    return Response(data=dat,content_type='application/json')



