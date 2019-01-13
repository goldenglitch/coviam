import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.*;
import java.util.Date;
import java.text.SimpleDateFormat;
public class JsonFileHandler implements MyFileHandler {
    public static Integer readCounter = 0;
    public static PrintWriter pw;

    static {
        try {
            pw = new PrintWriter("/Users/arpitsharma/Downloads/filehandlerwiththreading/write_employee.json");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    @Override
    public Employee read() {
        JSONParser parser = new JSONParser();
        try {
            File file = new File("/Users/arpitsharma/Downloads/filehandlerwiththreading/employee.json");
            JSONArray a = (JSONArray) parser.parse(new FileReader(file));
            Object obj = a.get(readCounter++);
            JSONObject jo = (JSONObject) obj;

            String firstName = (String) jo.get("firstName");
            String lastName = (String) jo.get("lastName");
            String dateOfBirth = (String) jo.get("dateOfBirth");
            Double experience = ((Number)jo.get("experience")).doubleValue();
            Date date = new SimpleDateFormat("dd/MM/yyyy").parse(dateOfBirth);
            Employee employee = new Employee(firstName, lastName, date,experience);
            return employee;

        } catch (Exception e) {
            System.out.println("fiel not found exception");
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void write(Employee employee) {
        try {
            String firstName = employee.getFirstName();
            String lastName = employee.getLastName();
            Date date = employee.getDateOfBorth();
            Double experience = employee.getExperience();
            String strDate = String.valueOf(date);
            JSONObject jo = new JSONObject();

            // putting data to JSONObject
            jo.put("firstName", firstName);
            jo.put("lastName", lastName);
            jo.put("dateOfBirth", strDate);
            jo.put("experience", experience);
            pw.write(jo.toJSONString());

            pw.flush();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
