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
import datetime
import base64

#Mark the attendance of all users present for a meeting
@api_view(['POST'])
def mark_attendance(request):
    al = request.data.get('attendance_list')
    mi = request.data.get('meeting_id')
    m = Meeting.objects.get(id=mi)
    for u in al:
        us = UserProfile.objects.get(user_acc__username=u)
        m.attended_users.add(us)
    m.save()
    return Response({'status': 'success', 'data': {'message': 'Attendence marked'}}, status=HTTP_200_OK)

#Check the attendance of any particular meeting
@api_view(['POST'])
def check_attendance(request): #admin path
    mi = request.data.get('meeting_id')
    m = Meeting.objects.get(id=mi)
    serializer = UserSerializer(m.attended_users, many=True)
    m.save()
    return Response({'status': 'success', 'data': {'message': serializer.data}}, status=HTTP_200_OK)

#View the number of meetings attended and missed by a single user
@api_view(['POST'])
def view_attendance(request): #admin path to check attendance overall of all meetings attended
    user_id=request.data.get('phone')
    all_meetings=Meeting.objects.all()
    u=UserProfile.objects.get(user_acc__username=user_id)
    cnt=0
    for m in all_meetings:
        if u in m.attended_users.all():
            cnt+=1
    return Response({'status': 'success', 'data': {'message': {'total':len(all_meetings),'attended':cnt}}}, status=HTTP_200_OK)

#Post an update regarding the current work being done
@api_view(['POST'])
def post_update(request): #user path to update admins
    i = request.data.get('task_id')
    t = ToDoTask.objects.get(id=i)
    p = DailyProgress.objects.get(task=t)
    txt = request.data.get('text')
    p.progress_text = txt
    #img = request.FILES['img.jpg']
    img_str=request.data.get('imgstr')
    img_dat=base64.b64decode(img_str)
    fname = '/tmp/%s.jpg'%('img')
    with open("Progress_Images/"+fname,'wb') as f:
        f.write(imgdata)
    imgname = '%s.jpg'%(str(p.id))
    p.image.save(imgname,File(open(fname,'r'))) 
    p.save()
    return Response({'status': 'success', 'data': {'message': 'Progress posted'}}, status=HTTP_200_OK)

#get the current progress of the task
@api_view(['POST'])
def get_progress(request):
    i = request.data.get('task_id')
    t = ToDoTask.objects.get(id=i)
    p = DailyProgress.objects.get(task=t)
    serializer = ProgressSerializer(p)
    return Response({'status': 'success', 'data': {'message': serializer.data}})
    
#rate the quality of work performed    
# @api_view(['POST'])
# def rate_post(request):
#     r = request.data.get('rate')
#     i = request.data.get('task_id')
#     u = request.user
#     tn = ToDoTask.objects.get(id=i)
#     d = DailyProgress.objects.get(task=tn.name)
#     d.rating = r
#     d.save() 
#     return Response({'status':'success','data':{'message':'Rating Done'}}, status=HTTP_200_OK)

#allows the user to user to accept a task
@api_view(['POST'])
def accept_task(request): 
    u = request.user
    us = UserProfile.objects.get(user_acc=u)
    i = request.data.get('task_id')
    t = ToDoTask.objects.get(id=i)
    t.users_assigned.add(us)
    t.save()
    return Response({'status':'success','data':{'message':'Task Accepted'}}, status=HTTP_200_OK)

#allows the user to user to reject a task    
@api_view(['POST'])
def reject_task(request): #reject task along with voice recording
    u = request.user
    us = UserProfile.objects.get(user_acc=u)
    i = request.data.get('task_id')
    t = ToDoTask.objects.get(id=i)
    t.users_assigned.remove(us)
    t.save()
    return Response({'status':'success','data':{'message':'Task Rejected'}}, status=HTTP_200_OK)

#create a new task
@api_view(['POST'])
def create_task(request): 
    tname=request.data.get('t_name')
    tdsc=request.data.get('t_dsc')
    date_time=datetime.datetime.strptime(request.data.get("date")+" "+request.data.get("time"),"%Y-%m-%d %H:%M")
    ToDoTask.objects.create(name=tname, description=tdsc, status='unassigned',due_date=date_time)
    return Response({'status':'success','data':{'message':'Task Created'}}, status=HTTP_200_OK)

#block more users from accepting 
@api_view(['POST'])
def user_threshold(request): #bloack new users
    i = request.data.get('task_id')
    t = ToDoTask.objects.get(id=i)
    t.status = 'assigned'
    t.save()
    return Response({'status':'success','data':{'message':'Task Already Assigned'}}, status=HTTP_200_OK)

#returns all taks that can have more users
@api_view(['POST'])
def fetch_unassigned(request):  
    tk = ToDoTask.objects.filter(status="unassigned")
    if tk.exists():
        serializer = TaskSerializer(tk, many=True)
        return Response({'status':'success','data':{'message':serializer.data}})
    
    return Response({'status':'failure','data':{'message':'No Unassigned Task'}})

#rate quality of a task
@api_view(['POST'])
def rate_task(request):
    i = request.data.get('task_id')
    t = ToDoTask.objects.get(id=i)
    t.rating = request.data.get('rate')
    t.save()
    return Response({'status':'success','data':{'message':'Task Rated'}})

#allows the manager to enter the amount of compensation for a particular task
@api_view(['POST'])
def CompensetaionView(request):
    i = request.data.get('task_id')
    t = ToDoTask.objects.get(id=i)
    t.compensation = request.data.get('compensation')
    t.save()
    return Response({'status':'success','data':{'message':'Task Compensation'}})

#allows the manager to enter data about a new meeting
@api_view(['GET','POST'])
def create_meeting(request):
    date_time=datetime.datetime.strptime(request.data.get("date")+" "+request.data.get("time"),"%Y-%m-%d %H:%M")
    Meeting.objects.create(date_time=date_time)
    return Response({'status':'success','data':{'message':'Meeting Created'}})

#returns data about all meetings along with attendance list of each
@api_view(['GET','POST'])
def get_all_meetings(request):
    all_meeting_data=Meeting.objects.all()
    serialized=MeetingSerializer(all_meeting_data,many=True)
    return Response({'status':'success','data':{'message':serialized.data}})