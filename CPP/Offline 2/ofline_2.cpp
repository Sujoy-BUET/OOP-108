#include<iostream>
using namespace std;

class Course{
    string name;
    float creditHour;
public:
    Course(){
        name = "Undefined";
        creditHour = 0;
    }
    Course(string name,float creditHour){
        this->name = name;
        this->creditHour = creditHour;
    }
    string getName(){
        return name;
    }
    float getCreditHour(){
        return creditHour;
    }
    void setName(string name){
        this->name = name;
    }
    void setCreditHour(float creditHour){
        this->creditHour = creditHour;
    }
    void display(){
        cout << "Course Name: " << name << ", ";
        cout << "Credit Hour: " << creditHour << "\n";
    }
};

class Student{
    string name;
    int id;
    int totalCourses;
    int maxCourses;
    float *gradepoints;
    Course *courses;
public:    
    Student(string name, int id, int maxCourses){
        this->name = name;
        this->id = id;
        this->totalCourses = 0;
        this->maxCourses = maxCourses;
        courses = new Course[maxCourses];
        gradepoints = new float[maxCourses];
    }
    Student(Student &ob){
        this->name = ob.name;
        this->id = ob.id;
        this->totalCourses = ob.totalCourses;
        this->maxCourses = ob.maxCourses;
        courses = new Course[this->maxCourses];
        gradepoints = new float[this->maxCourses];
        for(int i=0;i<this->totalCourses;i++){
            courses[i] = ob.courses[i];
            gradepoints[i] = ob.gradepoints[i];
        }   
    }
    ~Student(){
        delete[] courses;
        delete[] gradepoints;
    }
    void setName(string name){
        this->name = name;
    }
    void setId(int id){
        this->id = id;
    }
    void setInfo(string name, int id){
        this->name = name;
        this->id = id;
    }
    void addCourse(Course c){
        if(totalCourses>=maxCourses){
            cout << "Cannot add more courses to " << name << "\n";
            return;
        }
        courses[totalCourses] = c;
        gradepoints[totalCourses++] = 0;
    }
    void addCourse(Course course, float gradePoint){
        if(totalCourses>=maxCourses){
            cout << "Cannot add more courses to " << name << "\n";
            return;
        }
        courses[totalCourses] = course;
        gradepoints[totalCourses++] = gradePoint;
    }
    void setGradePoint(Course c, float gradePoint){
        for(int i=0;i<totalCourses;i++){
            if(this->courses[i].getName()==c.getName()){
                this->gradepoints[i] = gradePoint;
                return;
            }
        }
        cout << "Course not registered\n"; // extra
    }
    void setGradePoint(float* gradePoints, int n){
        for(int i=0;i<n;i++){
            this->gradepoints[i] = gradePoints[i];
        }
    }
    string getName(){
        return this->name;
    }
    int getMaximumCourse(){
        return this->maxCourses;
    }
    float getCGPA(){  
        float cumulative_grade=0;
        for(int i=0;i<totalCourses;i++){
            cumulative_grade += (courses[i].getCreditHour()* getGradePoint(courses[i]));
        }
        float total_CH = getTotalCreditHours();
        if(total_CH>0) return cumulative_grade/total_CH;
        else return 0;
    }
    float getGradePoint(Course c){
        for(int i=0;i<totalCourses;i++){
            if(courses[i].getName()==c.getName()){
                return this->gradepoints[i];
            }
        }
        return -1.0;
    }
    int getTotalCourses(){
        return this->totalCourses;
    }
    float getTotalCreditHours(){
        float total_creditHour = 0;
        for(int i=0;i<totalCourses;i++){
            if(getGradePoint(courses[i])>0) total_creditHour += this->courses[i].getCreditHour();
        }
        return total_creditHour;
    }
    Course getMostFavoriteCourse(){ //tie is broken by the order of course registration
        int idx = 0; 
        float maxx = -1; 
        for(int i=0;i<totalCourses;i++){
            if(this->gradepoints[i]>maxx){
                maxx = this->gradepoints[i];
                idx = i;
            }
        }
        return courses[idx];
    }
    Course getLeastFavoriteCourse(){ //tie is broken by the order of course registration
        int idx = 0; 
        float minn = 100.0;
        for(int i=0;i<totalCourses;i++){
            if(this->gradepoints[i]<minn){
                minn = this->gradepoints[i];
                idx = i;
            }
        }
        return courses[idx];
    }
    Course* getFailedCourses(int &count){
        Course *courses = new Course[totalCourses];
        int idx = 0;
        for(int i=0;i<totalCourses;i++){
            if(this->gradepoints[i] < 2.0){
                courses[idx++] = this->courses[i];
            }
        }
        count = idx;
        return courses;
    }
    void display(){ 
        cout << "===================================================\n";
        cout << "Student Name: " << this->getName() << ", ID: " << id << "\n";
        for(int i=0;i<totalCourses;i++){
            cout << "Course Name: " << courses[i].getName() << ", Credit Hour: " << courses[i].getCreditHour() << ", gradepoint: " << this->getGradePoint(courses[i]) << "\n";
        }
        cout << "CGPA: " << this->getCGPA() << "\n";
        cout << "Total credit hour eared: " << this->getTotalCreditHours() << "\n";
        cout << "Most Favourite course: " << this->getMostFavoriteCourse().getName() << "\n";
        cout << "Least Favourite course: " << this->getLeastFavoriteCourse().getName() << "\n"; 
        cout << "===================================================\n";
    }
};

Student getTopper(Student *students[], int totalStudents) {
    Student *topper = students[0];
    for (int i = 1; i < totalStudents; i++) {
        if (students[i]->getCGPA() > topper->getCGPA()) {
            topper = students[i];
        }
    }
    return *topper;
}

Student getTopper(Course course, Student *students[], int totalStudents) {
    Student *topper = students[0];
    float highestGrade = students[0]->getGradePoint(course);
    for (int i = 1; i < totalStudents; i++) {
        float grade = students[i]->getGradePoint(course);
        if (grade > highestGrade) {
            highestGrade = grade;
            topper = students[i];
        }
    }
    return *topper;
}

int main() {
    // generate courses
    const int COURSE_COUNT = 6;
    const int STUDENT_COUNT = 10;
    Course courses[COURSE_COUNT] = {
        Course("CSE107", 3),
        Course("CSE105", 3),
        Course("CSE108", 1.5),
        Course("CSE106", 1.5),
        Course("EEE164", 0.75),
        Course("ME174", 0.75),
    };
    float gradePoints[COURSE_COUNT] = {4.0, 4.0, 3.5, 3.5, 4.0, 3.25};
    // generate students
    int totalStudents = 0;
    Student s1 = Student("Sheldon", 1, 5);
    Student* students[STUDENT_COUNT];
    students[totalStudents++] = &s1;
    // add courses to s1
    s1.addCourse(courses[0]);
    s1.addCourse(courses[1]);
    s1.addCourse(courses[2]);
    s1.addCourse(courses[3]);
    s1.addCourse(courses[4]);
    s1.addCourse(courses[5]);
    s1.setGradePoint(gradePoints, s1.getTotalCourses());
    s1.display();
    Student s2 = Student("Penny", 2, 5);
    students[totalStudents++] = &s2;
    s2.addCourse(courses[0]);
    s2.addCourse(courses[2]);
    s2.addCourse(courses[5]);
    s2.setGradePoint(gradePoints, s2.getTotalCourses());
    s2.setGradePoint(courses[0], 3.25);
    s2.display();
    Student s3 = s2;
    students[totalStudents++] = &s3;
    s3.setName("Leonard");
    s3.setId(3);
    s3.setGradePoint(gradePoints, s3.getTotalCourses());
    s3.addCourse(courses[1], 3.75);
    s3.display();
    Student s4 = s3;
    students[totalStudents++] = &s4;
    s4.setInfo("Howard", 4);
    s4.setGradePoint(gradePoints, s4.getTotalCourses());
    s4.addCourse(courses[3], 3.75);
    s4.display();
    Student s5 = s4;
    students[totalStudents++] = &s5;
    s5.setInfo("Raj", 5);
    s5.setGradePoint(gradePoints, s5.getTotalCourses());
    s5.setGradePoint(courses[0], 1.5);
    s5.setGradePoint(courses[2], 2.0);
    s5.setGradePoint(courses[5], 1.75);
    s5.setGradePoint(courses[3], 3.75);
    s5.display();
    int failedCount;
    Course* failedCourses = s5.getFailedCourses(failedCount);
    cout << "Failed Courses for " << s5.getName() << ":" << endl;
    for (int i = 0; i < failedCount; ++i) {
        failedCourses[i].display();
        cout << endl;
    }
    delete[] failedCourses;
    cout << "==================================" << endl;
    Student topper = getTopper(students,totalStudents);
    cout << "Topper: " << topper.getName() << endl;
    cout << "Topper CGPA: " << topper.getCGPA() << endl;
    cout << "==================================" << endl;
    for (int i = 0; i < COURSE_COUNT; ++i) {
        Course c = courses[i];
        Student topperInCourse = getTopper(c,students,totalStudents);
        cout << "Topper in " << c.getName() << ": "<< topperInCourse.getName() << endl;
        cout << "Topper in " << c.getName() << " gradePoint: " <<topperInCourse.getGradePoint(c) << endl;
        cout << "==================================" << endl;
    }
    return 0;
}