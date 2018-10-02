package ALGORYTM_GENETYCZNY2;
import java.util.ArrayList;
import java.util.Arrays;


public class Data {
    private ArrayList<Room> rooms;
    private ArrayList<Instructor> instructors;
    private ArrayList<Course> courses;
    private ArrayList<Department> depts;
    private ArrayList<MeetingTime> meetingTimes;
    private int numberOfClasses = 0;

    public ArrayList<Room> getRooms() {return rooms;}
    public ArrayList<Instructor> getInstructors() {return instructors;}
    public ArrayList<Course> getCourses() {return courses;}
    public ArrayList<Department> getDepts() {return depts; }
    public ArrayList<MeetingTime> getMeetingTimes() {return meetingTimes;}
    public int getNumberOfClasses() {return numberOfClasses;}   
    
    public Data ()
    {
        initialize();
    }
    private Data initialize() {
        Room room1 = new Room("R1",25);
        Room room2 = new Room("R2",45);
        Room room3 = new Room("R3",35);
        rooms = new ArrayList<>(Arrays.asList(room1,room2,room3));
        
        MeetingTime meetingtime1 = new MeetingTime("MT1", "MWF 9:00 - 10:00 ");
        MeetingTime meetingtime2 = new MeetingTime("MT2", "MWF 10:00 - 11:00");
        MeetingTime meetingtime3 = new MeetingTime("MT3", "MWF 9:00 - 10:30 ");
        MeetingTime meetingtime4 = new MeetingTime("MT4", "MWF 10:30 - 12-00");
        meetingTimes = new ArrayList<>(Arrays.asList(meetingtime1,meetingtime2,meetingtime3,meetingtime4));
        
        Instructor instructor1 = new Instructor("I1", "Dr James Web ");
        Instructor instructor2 = new Instructor("I2", "Mr Mike Brown");
        Instructor instructor3 = new Instructor("I3", "Dr Steve Day ");
        Instructor instructor4 = new Instructor("I4", "Mrs Jane Doe ");
        instructors = new ArrayList<>(Arrays.asList(instructor1,instructor2,instructor3,instructor4));
        
        Course course1 = new Course("C1", "325K", new ArrayList<Instructor>(Arrays.asList(instructor1,instructor2)),25 );
        Course course2 = new Course("C2", "319K", new ArrayList<Instructor>(Arrays.asList(instructor1,instructor2,instructor3)),35);
        Course course3 = new Course("C3", "462K", new ArrayList<Instructor>(Arrays.asList(instructor1,instructor2)),25);
        Course course4 = new Course("C4", "464K", new ArrayList<Instructor>(Arrays.asList(instructor3,instructor4)),30);
        Course course5 = new Course("C5", "360C", new ArrayList<Instructor>(Arrays.asList(instructor4)),35);
        Course course6 = new Course("C6", "303K", new ArrayList<Instructor>(Arrays.asList(instructor1,instructor3)),45);
        Course course7 = new Course("C7", "303L", new ArrayList<Instructor>(Arrays.asList(instructor2,instructor4)),45);
        courses = new ArrayList<>(Arrays.asList(course1,course2,course3,course4,course5,course6,course7));
        
        Department dept1 = new Department("MATH", new ArrayList<>(Arrays.asList(course1,course3))); //2 + 2
        Department dept2 = new Department("JAVA", new ArrayList<>(Arrays.asList(course2,course4,course5)));//3 +2 + 1
        Department dept3 = new Department("PHYT", new ArrayList<>(Arrays.asList(course6,course7))); //2 + 2 
        depts = new ArrayList<Department>(Arrays.asList(dept1,dept2,dept3));
        
        depts.forEach(x-> numberOfClasses += x.getCourses().size()); //tutaj wylicza calkowity number klas w sumie 14 
        
        return this;
    }
//    public static void main(String[]args)
//    {
//        Data data = new Data();
//        data.initialize();
//        System.out.println(data.numberOfClasses);
//    }
}
