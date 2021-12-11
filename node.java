import java.util.Arrays;

public class node { //reused your code to make the base tree, just switched a few things so it was compatible with student objects

    public student s;
    public node leftChild;
    public node rightChild;

    public node(student s) {
        this.s = s;
    }

    public node(student[] students) {

        int length = students.length;

        switch (length) {
            case 1: // Base Case 1
                this.s = students[0].deepCopy();
                return;
            case 2: // Base Case 2
                    student first = students[0];
                    student second = students[1];
                if ((students[1].getName()).compareTo(students[0].getName())>=0){
                    second = students[0];
                    first = students[1];
                }
                this.s = second.deepCopy();
                this.leftChild = new node(first.deepCopy());
            default: // Recursion
                int median = length / 2;
                student[] leftArray = Arrays.copyOfRange(students, 0, median);
                student[] rightArray = Arrays.copyOfRange(students, median + 1, students.length);
                if (leftArray.length > 0) this.leftChild = new node(leftArray);
                this.s = students[median];
                if (rightArray.length > 0) this.rightChild = new node(rightArray);
        }
    }

    
}