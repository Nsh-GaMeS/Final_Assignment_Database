
// Java program to demonstrate Working of
// Comparator interface

// Importing required classes

import java.util.Comparator;

// Class 1
// A class to represent a student.
class Student {
    int rollno;
    String name, address;

    String diractor;

    // Constructor
    public Student(int rollno, String name, String address, String diractor)
    {
        // This keyword refers to current object itself
        this.rollno = rollno;
        this.name = name;
        this.address = address;
        this.diractor = diractor;
    }

    // Used to print student details in main()
    public String toString()
    {
        return this.rollno + " " + this.name + " " + this.address + " " + this.getDiractor();
    }

    public String getDiractor() {
        return diractor;
    }
}

// Class 2
// Helper class extending Compatator interface
class Sortbyroll implements Comparator<Student> {
    // Used for sorting in ascending order of
    // roll number
    public int compare(Student a, Student b)
    {
        System.out.println("compare called");
        System.out.println(a.rollno + " " + b.rollno);
        return a.rollno - b.rollno;
    }
}

class Sort implements Comparator<Student> {
    // Used for sorting in ascending order of
    // roll number
    public int compare(Student a, Student b)
    {
        System.out.println("SortbyDirector compare called");
        System.out.println(a.diractor + " " + b.diractor);
        return a.getDiractor().compareTo(b.getDiractor());
    }
}

// Class 3
// Main class
class GFG {

    // Main driver method
    public static void main(String[] args)
    {
        Student[] arr
                = { new Student(111, "bbbb", "london", "nate"),
                new Student(131, "aaaa", "nyc", "gabe"),
                new Student(121, "cccc", "jaipur", "olga") };

        System.out.println("Unsorted");

        for (int i = 0; i < arr.length; i++)
            System.out.println(arr[i]);

        // Sorting on basic as per class 1 created
        // (user-defined)
//        Arrays.sort(arr, new Sortbyroll());
//        Arrays.sort(arr, new SortbyDirector());

        System.out.println("\nSorted by rollno");

        for (int i = 0; i < arr.length; i++)
            System.out.println(arr[i]);
    }
}