public class nodeManager {

    mergeSort alph = new mergeSort();
    private node root;
    private node key;
    private student student = new student();
    student[] students;
    
    nodeManager(student[] unsorted){
        students = new student[unsorted.length];
        int end = unsorted.length-1;
        alph.mSort(unsorted,0,end); // sorts intial array provided by main.
        students = alph.getArray();
        root= new node(students); //creates tree
        alph.print(); //prints intial students in alphabetical order
    }

    void insert(student s){            // checks to make sure root isn't empty, as long as it isn't, sends a temp root
        node newStudent = new node(s); // and the new student to another method to attempt insertion recursively
        if(root==null){
            root = newStudent;
            System.out.println("Student Inserted, new root created");
        }
        else{
            node temp=root;
            insert(newStudent,temp);
        }

    }

    void insert(node newStudent,node tempRoot){
        String newName = newStudent.s.getName(); //grabs names of student being inserted and current root so new student can be placed accurately
        String rootName = tempRoot.s.getName();

        if(newName.compareTo(rootName)<=0){ // if the new student's name comes before the root alphabetically (or has the same name)
            if(tempRoot.leftChild==null){
                tempRoot.leftChild=newStudent; // if no node on the left, new student gets inserted
                System.out.println("Student Inserted");
                return;
                }
            else
                insert(newStudent,tempRoot.leftChild); //if there is a node on the left, repeat insert operation on that node
        }

        else{ // if the new student's name comes after the root alphabetically
            if(tempRoot.rightChild==null){
                tempRoot.rightChild=newStudent;  // do the same stuff as above, but on the right side
                System.out.println("Student Inserted");
                return;
                }
            else
                insert(newStudent,tempRoot.rightChild);
        }

    }

    student fetch(String name){
        if(root==null){
            System.out.println("Tree Empty!"); //if tree is empty, says so
            return null;
        }
        else{
            node temp=root; //otherwise, sends name and the root node to the recursive fetch method below
            fetch(name,temp);
            student s = getStudent().deepCopy(); //if recursive method found somthing, sets the temp student variable to whatever it found
            setStudent(null); //clears the temp student variable after use so it doesnt mess up other functions
            return s.deepCopy();
        }
    }

    void fetch(String name,node walker){ 
        String wName = walker.s.getName();//grabs names of student being inserted and current root so new student can be placed accurately

        if(name.compareTo(wName)==0){ //if a match is found, return the student
            setStudent(walker.s); //making this method return something gave me trouble so instead i used setters and getters to effectively return stuff
        }
        else if(name.compareTo(wName)<0){ // if the new student's name comes before the root alphabetically (or has the same name)
            if(!(walker.leftChild==null))
                fetch(name,walker.leftChild); //if there is a node on the left, repeat fetch operation on that node
            }

        else if(name.compareTo(wName)>0){ // if the new student's name comes after the root alphabetically
            if(!(walker.rightChild==null))
                fetch(name,walker.rightChild); //if there is a node on the right, repeat fetch operation on that node
            }
        
    }

    void delete(String name){
        delete(name,root); //sends name and root to recursive delete method below
    }

    void delete(String name,node walker){ 

        String wName = walker.s.getName();//grabs names of student being inserted and current root so new student can be placed accurately
        
        if(name.compareTo(wName)==0){ //if a match is found, return the student
            System.out.println("Student found");
            if(walker == root){ //if the desired node to be deleted is also the root node
                if(walker.leftChild == null && walker.rightChild==null) // just delete it if it doesnt have any children
                    root=null;
                else if(walker.leftChild == null && !(walker.rightChild==null)) // if it has a right child, but no left child make that right child the new root
                    root = walker.rightChild;
                else if(walker.rightChild == null && !(walker.leftChild==null)) // sames as above but the other way around
                    root = walker.leftChild;
                else{ //if it has two children
                    node newRoot=smallestChild(walker.rightChild); //new root will be the smallest value greater than the current root
                    node temp=getParent(newRoot); //grabs parent of new root for use later
                    temp.leftChild=newRoot.rightChild; //if the new root had any right children, assigns them to its old parent (new root won't have any left children since its the smallest value on the right subtree)
                    newRoot.leftChild = root.leftChild; //assigns the new root to the children of the old root
                    newRoot.rightChild = root.rightChild;
                    root = newRoot; //reassigns root to newroot
                }
                System.out.println("Student Deleted");
            }
            else{
                node p=getParent(walker); //repeats above, but more generally and without reassigning root
                if(p.leftChild==walker){  //had to seperate delete method into whether the node was a right or a left child so that reassigning parent could be done correctly
                    if(walker.leftChild == null && walker.rightChild==null)
                        p.leftChild=null;
                    else if(walker.leftChild == null && !(walker.rightChild==null))
                        p.leftChild = walker.rightChild;
                    else if(walker.rightChild == null && !(walker.leftChild==null))
                        p.leftChild = walker.leftChild;
                    else{
                        node newRoot=smallestChild(walker.rightChild);
                        node temp=getParent(newRoot);
                        if(temp!=p.leftChild)
                            temp.leftChild=newRoot.rightChild;
                        newRoot.leftChild = walker.leftChild;
                        if(temp!=p.rightChild)
                            newRoot.rightChild = walker.rightChild;
                        else
                            newRoot.rightChild = null;
                        p.leftChild=newRoot;
                        }
                    System.out.println("Student Deleted");
                    }

                else if(p.rightChild==walker){
                    if(walker.leftChild == null && walker.rightChild==null)
                        p.rightChild=null;
                    else if(walker.leftChild == null && !(walker.rightChild==null))
                        p.rightChild = walker.rightChild;
                    else if(walker.rightChild == null && !(walker.leftChild==null))
                        p.rightChild = walker.leftChild;
                    else{
                        node newRoot=smallestChild(walker.rightChild);
                        node temp=getParent(newRoot);
                        if(temp!=p.rightChild){
                            temp.leftChild=newRoot.rightChild;}
                        newRoot.leftChild = walker.leftChild;
                        if(temp!=p.rightChild)
                            newRoot.rightChild = walker.rightChild;
                        else
                            newRoot.rightChild=null;
                        p.rightChild=newRoot;
                    }
                    System.out.println("Student Deleted");
                }   
            }
        }      
                
        if(name.compareTo(wName)<0){ // if the new student's name comes before the root alphabetically (or has the same name)
            if(!(walker.leftChild==null))
                delete(name,walker.leftChild); //if there is a node on the left, repeat delete operation on that node
        }

        if(name.compareTo(wName)>0){ // if the new student's name comes after the root alphabetically
            if(!(walker.rightChild==null))
                delete(name,walker.rightChild); //if there is a node on the right, repeat delete operation on that node
        }
    }
       
    node getParent(node child){
        findParent(child,root,root); //uses findparent method below to return parent
        node p = getNode(); //grabs parent using setters / getters since getting a recrusive method to return stuff wasn't working out for me
        setNode(null); //clears setter / getter after using it as not to mess up other functions
        return p;
    }  

    void findParent(node child, node p, node walker){ //input child node and p/walker both as root

        String name = child.s.getName();//grabs names of student being inserted and current root so new student can be placed accurately
        String wName = walker.s.getName();

        if(name.compareTo(wName)==0){ //if a match is found, return the student
            setNode(p);
        }
        if(!(walker==root)){ //increments p with walker every step except when they're both root
            p=walker;
        }
        if(name.compareTo(wName)<0){ // if the new student's name comes before the root alphabetically (or has the same name)
            if(!(walker.leftChild==null))
                findParent(child, p, walker.leftChild); //if there is a node on the left, repeat findParent operation on that node
            }
        if(name.compareTo(wName)>0){ // if the new student's name comes after the root alphabetically
            if(!(walker.rightChild==null))
                 findParent(child, p, walker.rightChild); //if there is a node on the right, repeat findParent operation on that node
            }

    }

    void update(student s){
        if(root==null){
            System.out.println("Tree Empty!");
        }
        else{
            node temp=root;
            update(s,temp); //sends student and root to recursive method below
        }
    }

    void update(student newStudent,node walker){ 
        String wName = walker.s.getName();//grabs names of student being updated and current root so new student can be located accurately
        String name = newStudent.getName();

        if(name.compareTo(wName)==0){ //if a match is found, update the student
            walker.s=newStudent.deepCopy();
        }
        else if(name.compareTo(wName)<0){ // if the new student's name comes before the root alphabetically (or has the same name)
            if(!(walker.leftChild==null))
                update(newStudent,walker.leftChild); //if there is a node on the left, repeat update operation on that node
            }

        else if(name.compareTo(wName)>0){ // if the new student's name comes after the root alphabetically
            if(!(walker.rightChild==null))
                update(newStudent,walker.rightChild); //if there is a node on the right, repeat update operation on that node
            }
        
    }

    void setStudent(student s){
        student=s;
    }

    student getStudent(){
        return student;
    }

    node smallestChild(node walker){ //returns the smallest child of any given node
        while(!(walker.leftChild==null)){ //keeps reassigning walker to the leftchild until there is no left child
            walker = walker.leftChild;
        }
        node min = walker;
        return min;
    }

    void setNode(node n){
        key = n;
    }

    node getNode(){
        return key;
    }

    void printTree(){ 
        printer(root,root,true); //sends root to recursive printer method
    }

    void printer(node t,node p, boolean left){ //prints all nodes, includes the parent and whether the leaf is a left or right child to verify tree is setup correctly

        if (t==null)
            return;

        System.out.println("Parent: " + p.s.toString());

        if(left)
            System.out.println("Left Leaf: " +t.s.toString());

        else
            System.out.println("Right Leaf: " +t.s.toString());

            System.out.println();
        p=t;
        printer(t.leftChild,p,true);
        printer(t.rightChild,p,false);
    }
}

