import java.util.HashMap;
import java.util.Map;

public class StudentMarks {
    public static void main(String[] args) {
        HashMap<String, Integer> students = new HashMap<>();
        students.put("Mueed", 85);
        students.put("Qasim", 78);
        students.put("Asad", 92);
        students.put("Ali", 88);
        students.put("Moiz", 75);

        System.out.println("All Student Records:");
for (Map.Entry<String, Integer> entry : students.entrySet()) {
    System.out.println(entry.getKey() + " : " + entry.getValue());
}
students.put("Ahmed", 90);
System.out.println("\nUpdated Marks:");
System.out.println("Ahmed's updated marks: " + students.get("Ahmed"));
    }
}