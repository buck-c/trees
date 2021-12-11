import java.util.Scanner;

public class driver {
    public static void main(String[] args) {
        
        student arr[] = new student[26]; //fills array with all letters of the alphabet for demo purposes, also makes testing insertion / deletion easier

        arr[0]= new student("z","789123","2.8");
        arr[1]= new student("b","789123","2.8");
        arr[2]= new student("c","789123","2.8");       //placed a few letters out of order just to demonstrate inital sort works
        arr[3]= new student("u","789123","2.8");
        arr[4]= new student("r","789123","2.8");
        arr[5]= new student("q","789123","2.8");
        arr[6]= new student("g","789123","2.8");
        arr[7]= new student("h","789123","2.8");
        arr[8]= new student("i","789123","2.8");
        arr[9]= new student("j","789123","2.8");
        arr[10]= new student("l","789123","2.8");
        arr[11]= new student("k","789123","2.8");
        arr[12]= new student("m","789123","2.8");
        arr[13]= new student("n","789123","2.8");
        arr[14]= new student("o","789123","2.8");
        arr[15]= new student("p","789123","2.8");
        arr[16]= new student("f","789123","2.8");
        arr[17]= new student("e","789123","2.8");
        arr[18]= new student("s","789123","2.8");
        arr[19]= new student("t","789123","2.8");
        arr[20]= new student("d","789123","2.8");
        arr[21]= new student("v","789123","2.8");
        arr[22]= new student("w","789123","2.8");
        arr[23]= new student("x","789123","2.8");
        arr[24]= new student("y","789123","2.8");
        arr[25]= new student("a","789123","2.8");
        
        nodeManager tree = new nodeManager(arr); //sends array to nodemanager so it can be edited as needed below
        
        Scanner input=new Scanner(System.in);

        while(true){ //after data has been submitted, prompts user for a new menu with options on what to do with the data

            System.out.println("Select an option:");
		    System.out.println("1: Insert a student at a specific index");
		    System.out.println("2: Fetch a student's information");
            System.out.println("3: Delete a student's information");
            System.out.println("4: Update a student's information");
            System.out.println("5: Output all student information");
            System.out.println("6: Exit program"); //lists selection to edit entries

	        int sel=input.nextInt();
        
            if(sel==1){ // inserts a student in the tree alphabetically
                student student = new student();
                student.input(input);
                tree.insert(student); 
            }
            else if(sel==2){  //pulls a student's information based on a name search
            
                System.out.println("Student name?");
                String name = input.next();
                student student =tree.fetch(name); 
                if(!(student==null))
                    System.out.println(student.toString());
            }
            else if(sel==3){ //deletes a student based on name search
                System.out.println("Student name?");
                String name = input.next();
                tree.delete(name);
            } 
            else if(sel==4){ //updates a student's information based on name search
                student student = new student();
                student.input(input);
                tree.update(student);
                }
            
            else if(sel==5){
                tree.printTree(); //prints tree
            }
            else if(sel==6){
                System.exit(0); //ends program
            }
            else{
                System.out.println("invalid selection"); 
            }
        } 

        
    }
}

