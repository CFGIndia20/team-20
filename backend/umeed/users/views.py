from django.shortcuts import render
from django.contrib.auth.models import User
from users.models import UserProfile
from django.contrib.auth import authenticate,login,logout
from rest_framework.decorators import api_view,permission_classes
from rest_framework.status import HTTP_400_BAD_REQUEST,HTTP_404_NOT_FOUND,HTTP_200_OK
from django.views.decorators.csrf import csrf_exempt
from rest_framework.permissions import AllowAny,IsAuthenticated
from rest_framework.authtoken.models import Token
from rest_framework.response import Response

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
    return

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
    return HTTP_200_OK

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
    pass

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








