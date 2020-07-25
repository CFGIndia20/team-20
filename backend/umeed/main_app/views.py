from rest_framework.decorators import api_view,permission_classes
from rest_framework.status import HTTP_400_BAD_REQUEST,HTTP_404_NOT_FOUND,HTTP_200_OK
from django.views.decorators.csrf import csrf_exempt
from rest_framework.permissions import AllowAny,IsAuthenticated
from rest_framework.authtoken.models import Token
from rest_framework.response import Response
from users.models import *
from users.serializers import *

# Create your views here.
@api_view(['POST'])
def check_attendance(request): #admin path
    pass

def view_attendance(request): #user path
    pass

def post_update(request): #user path to update admins
    pass

def profile(request):#return user profile
    pass

def stats(request): #return user stats to admin
    pass

def assign_task(request): #admin assigns a task
    pass

def accept_task(request): #accept task (for user)
    pass

def reject_task(request): #reject task along with voice recording
    pass

@api_view(['POST'])
def give_rating(request):
    return Response()

"""
Calculates the compensation to be given to women
"""
def CompensetaionView(request):
    compensation = 200*rating*number_hours + attended_meets*100
    return compensation
    
