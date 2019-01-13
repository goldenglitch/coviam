class RunnableReader extends Thread {
    private Thread t;
    private String threadName;

    RunnableReader( String name) {
        threadName = name;
        System.out.println("Creating " +  threadName );
    }

    public void run() {
        System.out.println("Running " +  threadName );
        if(threadName == "CSVThread"){
            CSVFileHandler csvFileHandler = new CSVFileHandler();
            for(int i=0;i<100;i++){
                Employee employee = csvFileHandler.read();
                System.out.println(employee.getFirstName());
                MyController.myCollection.addEmployee(employee);
                System.out.println(MyController.myCollection.writeCounter);

            }
        }
        else if(threadName == "XMLThread"){
            XMLFileHandler xmlFileHandler = new XMLFileHandler();
            for(int i=0;i<100;i++){
                Employee employee = xmlFileHandler.read();
                System.out.println(employee.getFirstName());
                MyController.myCollection.addEmployee(employee);
                System.out.println(MyController.myCollection.writeCounter);
            }
        }

        else {
            JsonFileHandler jsonFileHandler = new JsonFileHandler();
            for(int i=0;i<100;i++){
                Employee employee = jsonFileHandler.read();
                System.out.println(employee.getFirstName());
                MyController.myCollection.addEmployee(employee);
                System.out.println(MyController.myCollection.writeCounter);
            }
        }

        System.out.println("Thread " +  threadName + " exiting.");


    }

//    public void start () {
//        System.out.println("Starting " +  threadName );
//        if (t == null) {
//            t = new Thread (this, threadName);
//            t.start ();
//        }
//    }
}

class RunnableWriter extends Thread {
    private Thread t;
    private String threadName;

    RunnableWriter( String name) {
        threadName = name;
        System.out.println("Creating " +  threadName );
    }

    public void run() {
        System.out.println("Running " +  threadName );
        if(threadName == "CSVThread"){
            CSVFileHandler csvFileHandler = new CSVFileHandler();
            for(int i=0;i<100;i++){
                Employee employee = MyController.myCollection.readEmployee();
                System.out.println(MyController.myCollection.readCounter);
                csvFileHandler.write(employee);

            }
        }
        else if(threadName == "XMLThread"){
            XMLFileHandler xmlFileHandler = new XMLFileHandler();
            for(int i=0;i<100;i++){
                Employee employee = MyController.myCollection.readEmployee();
                System.out.println(MyController.myCollection.readCounter);
                xmlFileHandler.write(employee);

            }
        }

        else {
            JsonFileHandler jsonFileHandler = new JsonFileHandler();
            for(int i=0;i<100;i++){
                Employee employee = MyController.myCollection.readEmployee();
                System.out.println(MyController.myCollection.readCounter);
                jsonFileHandler.write(employee);

            }
        }

        System.out.println("Thread " +  threadName + " exiting.");
    }
//
//    public void start () {
//        System.out.println("Starting " +  threadName );
//        if (t == null) {
//            t = new Thread (this, threadName);
//            t.start ();
//        }
//    }
}


public class MyController {
    public static MyCollection myCollection = new MyCollection();

    public static void main(String[] args) throws InterruptedException {

        RunnableReader runnableReaderCSV = new RunnableReader("CSVThread");
        RunnableReader runnableReaderXML = new RunnableReader("XMLThread");
        RunnableReader runnableReaderJSON = new RunnableReader("JSONThread");
        runnableReaderJSON.start();
        runnableReaderXML.start();
        runnableReaderCSV.start();

        runnableReaderCSV.join();
        runnableReaderJSON.join();
        runnableReaderXML.join();

        System.out.println(myCollection.writeCounter);




        RunnableWriter runnableWriterCSV = new RunnableWriter("CSVThread");
        runnableWriterCSV.start();
        RunnableWriter runnableWriterXML = new RunnableWriter("XMLThread");
        runnableWriterXML.start();
        RunnableWriter runnableWriterJSON = new RunnableWriter("JSONThread");
        runnableWriterJSON.start();


    }



}
