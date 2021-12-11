import java.util.Scanner;

public class student {

    private String name;
    private String id;
    private String gpa;
    private String quit="quit";
        
    student() {	
        name="";
        id="";
        gpa="";
    }
        
    student(String student, String studentId, String grade) {
        name=student;
        id=studentId;
        gpa=grade;
    }
       
    void input(Scanner input){
        
        System.out.println("Student name?");
        name=input.next();

        if (!quit.equalsIgnoreCase(name)){
            System.out.println("Student ID?");
            id=input.next();
            System.out.println("GPA?");
            gpa=input.next();
        }
    }

    public student deepCopy() {
        student clone = new student(name,id,gpa); 
        return clone;
    }
    
    public int compareTo(String target){ 
        return(name.compareTo(target));
    }
    
     public String toString() { 
        return name+" Student ID: "+id+" GPA: "+gpa;
    }
    
    public String getName(){
        return name;
    }
}  