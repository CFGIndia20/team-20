from rest_framework.decorators import api_view,permission_classes
from rest_framework.status import HTTP_400_BAD_REQUEST,HTTP_404_NOT_FOUND,HTTP_200_OK
from django.views.decorators.csrf import csrf_exempt
from rest_framework.permissions import AllowAny,IsAuthenticated
from rest_framework.authtoken.models import Token
from rest_framework.response import Response
from users.models import *
from main_app.models import *
from users.serializers import *
from main_app.serializers import *

@api_view(['POST'])
def mark_attendance(request):
    user_id=request.data.get('phone')
    pass
# Create your views here.
@api_view(['POST'])
def check_attendance(request): #admin path
    pass

def view_attendance(request): #user path
    pass

def post_update(request): #user path to update admins
    pass

def stats(request): #return user stats to admin
    pass

def assign_task(request): #admin assigns a task
    pass

def accept_task(request): #accept task (for user)
    u = request.user
    us = UserProfile.objects.get(user_acc=u)
    i = request.data.get('task_id')
    t = ToDoTask.objects.get(id=i)
    t.users_assigned.add(us)
    t.save()
    return Response({'status':'success','data':{'message':'Task Accepted'}}, status=HTTP_200_OK)
    

def reject_task(request): #reject task along with voice recording
    u = request.user
    us = UserProfile.objects.get(user_acc=u)
    i = request.data.get('task_id')
    t = ToDoTask.objects.get(id=i)
    t.users_assigned.remove(us)
    t.save()
    return Response({'status':'success','data':{'message':'Task Rejected'}}, status=HTTP_200_OK)

def create_task(request): #manager creates a new task
    tname=request.data.get('t_name')
    tdsc=request.data.get('t_dsc')
    ToDoTask.objects.create(name=tname, description=tdsc, status='unassigned')
    return Response({'status':'success','data':{'message':'Task Created'}}, status=HTTP_200_OK)

def user_threshold(request): #bloack new users
    i = request.data.get('task_id')
    t = ToDoTask.objects.get(id=i)
    t.status = 'assigned'
    t.save()
    return Response({'status':'success','data':{'message':'Task Already Assigned'}}, status=HTTP_200_OK)

def fetch_unassigned(request):  #get all the unassigned tasks
    tk = ToDoTask.objects.filter(status="unassigned")
    if tk.exists():
        #serialize the users
        serializer = TaskSerializer(tk, many=True)
        #return Response using rest_framework's response
        return Response({'status':'success','data':{'message':serializer.data}})
    
    return Response({'status':'failure','data':{'message':'No Unassigned Task'}})





@api_view(['POST'])
def give_rating(request):
    return Response()

"""
Calculates the compensation to be given to women
"""
def CompensetaionView(request):
    compensation = 200*rating*number_hours + attended_meets*100
    return compensation
    
