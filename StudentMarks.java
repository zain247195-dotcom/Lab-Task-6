import java.util.HashMap;
import java.util.Map;

public class StudentMarks {
    public static void main(String[] args) {
        //Insert five student records into the HashMap
        HashMap<String, Integer> students = new HashMap<>();
        students.put("Mueed", 85);
        students.put("Qasim", 78);
        students.put("Asad", 92);
        students.put("Ali", 88);
        students.put("Moiz", 75);
//Display all student records
        System.out.println("All Student Records:");
for (Map.Entry<String, Integer> entry : students.entrySet()) {
    System.out.println(entry.getKey() + " : " + entry.getValue());
}
//Update the marks of one student
students.put("Ahmed", 90);
System.out.println("\nUpdated Marks:");
System.out.println("Ahmed's updated marks: " + students.get("Ahmed"));
//Retrieve and display marks of a specific student
String studentName = "Sara";
System.out.println("\nMarks of a Specific Student:");
System.out.println(studentName + "'s marks: " + students.get(studentName));
//Display the total number of students
System.out.println("\nTotal Number of Students: " + students.size());
    }
}