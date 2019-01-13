import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CSVFileHandler implements MyFileHandler{
    private static Integer readCounter = 0;

    private static File file=new File("/Users/arpitsharma/Downloads/filehandlerwiththreading/write_employees.csv");
    FileWriter fileWriter;

    {
        try {
            fileWriter = new FileWriter(file);
            CSVWriter writer=new CSVWriter(fileWriter);
            //Array of Strings and Data
            String[] csvHeaders={"firstName","lastName","dateOfBirth","experience"};
            writer.writeNext(csvHeaders);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    @Override
    public Employee read() {
        try
        {
            String fileLocation = "/Users/arpitsharma/Downloads/filehandlerwiththreading/employee.csv";
            FileReader fileReader=new FileReader(fileLocation);
            CSVReader csvReader=new CSVReader(fileReader);
            String[] readEmployee = csvReader.readAll().get(readCounter++);
            SimpleDateFormat formatter1=new SimpleDateFormat("dd/MM/yyyy");
            Date dateOfBirth = (Date) formatter1.parse(readEmployee[2]);
            Employee employee = new Employee(readEmployee[0],readEmployee[1], dateOfBirth,Double.parseDouble(readEmployee[3]));
            return employee;
        }
        catch (Exception e)
        {
            //it is a class of java.lang package and helps to understand where the actual problem is
            e.printStackTrace();
            Employee employee = null;
            return employee;
        }
    }

    @Override
    public void write(Employee employee) {
        try
        {

            FileWriter fileWriter=new FileWriter(file,true);
            CSVWriter writer=new CSVWriter(fileWriter);
            //Array of Strings and Data
            //String[] csvHeaders={"firstName","lastName","dateOfBirth","experience"};
            //writer.writeNext(csvHeaders);
            Date dateex = new Date();
            String[] entry={employee.getFirstName(),employee.getLastName(), String.valueOf(dateex),employee.getExperience().toString()};
            writer.writeNext(entry);

            writer.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
