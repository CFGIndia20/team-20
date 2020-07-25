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
    al = request.data.get('attendence_list')
    mi = request.data.get('meeting_id')
    m = Meeting.objects.get(id=mi)
    for u in al:
        us = UserProfile.objects.get(user_acc__username=u)
        m.attended_users.add(us)
    m.save()
    return Response({'status': 'success', 'data': {'message': 'Attendence marked'}}, status=HTTP_200_OK)


@api_view(['POST'])
def check_attendance(request): #admin path
    mi = request.data.get('meeting_id')
    m = Meeting.objects.get(id=mi)
    serializer = UserSerializer(m.attended_users, many=True)
    m.save()
    return Response({'status': 'success', 'data': {'message': serializer.data}}, status=HTTP_200_OK)


def view_attendance(request): #admin path to check attendance overall of all meetings attended
    user_id=request.data.get('phone')
    all_meetings=Meetings.objects.all()
    u=UserProfile.objects.get(user_acc__username=user_id)
    cnt=0
    for m in all_meetings:
        if u in m.attended_users:
            cnt+=1
    return Response({'status': 'success', 'data': {'message': {'total':len(all_meetings),'attended':cnt}}}, status=HTTP_200_OK)

def post_update(request): #user path to update admins

    i = request.data.get('task_id')
    t = ToDoTask.objects.get(id=i)
    p = DailyProgress.objects.get(task=t)
    txt = request.data.get('text')
    p.progress_text = txt
    img = request.FILES['img.jpg']
    p.image = img
    p.save()
    return Response({'status': 'success', 'data': {'message': 'Progress posted'}}, status=HTTP_200_OK)

def get_progress(request):
    i = request.data.get('task_id')
    t = ToDoTask.objects.get(id=i)
    p = DailyProgress.objects.get(task=t)
    serializer = ProgressSerializer(p)
    return Response({'status': 'success', 'data': {'message': serializer.data}})
    

def rate_post(request):
    r = request.data.get('rate')
    i = request.data.get('task_id')
    u = request.user
    tn = ToDoTask.objects.get(id=i)
    d = DailyProgress.objects.get(task=tn.name)
    d.rating = r
    d.save() 
    return Response({'status':'success','data':{'message':'Rating Done'}}, status=HTTP_200_OK)


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
def rate_task(request):
    i = request.data.get('task_id')
    t = ToDoTask.objects.get(id=i)
    t.rating = request.data.get('rate')
    t.save()
    return Response({'status':'success','data':{'message':'Task Rated'}})

"""
Calculates the compensation to be given to women
"""
def CompensetaionView(request):
    i = request.data.get('task_id')
    t = ToDoTask.objects.get(id=i)
    t.compensation = request.data.get('compensation')
    t.save()
    return Response({'status':'success','data':{'message':'Task Compensation'}})
    
