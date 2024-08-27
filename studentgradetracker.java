 import java.util.Scanner;

public class studentgradetracker {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        final int num_subjects = 5;
        float Average;
        float totalMarks = 0;
        
        //Entering number of students
        System.out.println("Enter number of students: ");
        int numStudents = sc.nextInt();
        String[] studentIds = new String[numStudents];
        String[] studentName = new String[numStudents];
        int[][] studentMarks = new int[numStudents][5];
        String[] resultstatus=new String[numStudents];
        String[] subjects = {"Math", "Science", "SocialSci", "English", "Hindi"};

        //enter students name,id and marks
        for (int i = 0; i < numStudents; i++) {
            System.out.println("Enter student " + (i + 1) + "'s id:");
            studentIds[i] = sc.next();
            System.out.println("Enter student " + (i + 1) + "'s name: ");
            studentName[i] = sc.next();
            totalMarks = 0; // reset totalMarks for each student

             // marks obtained (out of 100) in each subject
            for (int j = 0; j < subjects.length; j++) {
                System.out.println("Enter student " + (i + 1) + "'s marks in " + subjects[j] + " out of 100:");
                studentMarks[i][j] = sc.nextInt();
                totalMarks += studentMarks[i][j];
            }

            
           //calculating average here
            Average = totalMarks / num_subjects;
            System.out.println("Total Marks out of 500: " + totalMarks);
            System.out.print("The student Grade is: ");
            if (Average >= 80) {
                System.out.print("A");
            } else if (Average >= 60 && Average < 80) {
                System.out.print("B");
            } else if (Average >= 40 && Average < 60) {
                System.out.print("C");
            } else {
                System.out.print("D");
            }

            // Determine grade based on average
            if (Average < 33) {
                System.out.println("\nStatus: Fail");
                resultstatus[i]="Fail";
                System.out.println("\nYou'll do better next time!");
            } else if(Average >=40 && Average < 80) {
                System.out.println("\nStatus: Pass");
                resultstatus[i]="Pass";
                System.out.println("Good work, keep it up!");}
              else
              {
                System.out.println("\nStatus: Pass");
                resultstatus[i]="Pass";
                System.out.println("Excellent  work, keep it up!");
              }  
            }
            System.out.println();
        sc.close();
        //Displaying the total marks, average percentage,result status and the corresponding grade to the user

        System.out.println("Student Details:");
        System.out.println("---------------------------------------------------------------------------------------------");
        System.out.println("| Id | Name      | Math | Science | SocialSci | English | Hindi | Total Marks | Grade |Status");
        System.out.println("---------------------------------------------------------------------------------------------");

        for (int i = 0; i < numStudents; i++) {
            System.out.print("| " + studentIds[i] + " | " + studentName[i] +   " | ");

            float totalMarksForStudent = 0;
            for (int j = 0; j < subjects.length; j++) {
                System.out.print(studentMarks[i][j] +    "     | ");
                totalMarksForStudent += studentMarks[i][j];

            }
            
            System.out.println(totalMarksForStudent +"  " + "      | " + getGrade(totalMarksForStudent / num_subjects) + "  " +"     | " + resultstatus[i] + "|");
            System.out.println("----------------------------------------------------------------------------------------------");
        }


    }
    public static String getGrade(float average) {
        if (average >= 80) {
            return "A";
        } else if (average >= 60 && average < 80) {
            return "B";
        } else if (average >= 40 && average < 60) {
            return "C";
        } else {
            return "D";

    }

    }
}