import java.util.Scanner;
import java.util.StringTokenizer;
import java.io.PrintWriter;
import java.io.RandomAccessFile;

public class Dictionary implements Database {

    //declare scanner
    //The scanner will not be modified during the execution of the program
    //hence we declare it final static
    final static Scanner sc = new Scanner(System.in);

    //get string
    //This method is used to send a message that requires end-users 
    //to enter the string
    // return String
    public String returnString(String msg){
        System.out.print(msg);//print out the prompt
        return sc.nextLine().toLowerCase().trim();
    }

    // Add a new word to the database
    // This method will require end-users to enter English content along with its Vietnamese translation
    // Data will be stored in HashMap for easier data access
    public void addNewWord() throws Exception{
        loadData();//load data from the file and turn it into HashMap Data Structure
        System.out.println("------------Add-------------");
        String englishString = returnString("Enter English:");//User enter data
        if(!dictionary.containsKey(englishString)){
            //If the database exists such entered word
            //It will execute this block of code
            String vietnameseString = returnString("Enter Vietnamese:");
            dictionary.put(englishString, vietnameseString);
        }else{
            //If the database doesn't exist such entered word
            //It will execute this block of code
            System.out.println("This word is already exist in the database.");
            return;//exit immediately
        }
        //Because there was a change in the database, proceed to update the file
        updateFile();
    }

    //remove a word from the database
    // This method will require end-users to enter English content
    // Then it will search in the data base, find the cosresponding data to the entered word
    // If found{
    //      detete()
    //}else return
    public void deleteWord() throws Exception{
        loadData();//load data from the file and turn it into HashMap Data Structure
        if (dictionary.isEmpty()){//Check if the database is empty
            System.out.println("Database is empty.");
            return;//exit if empty
        }
        System.out.println("----------Delete----------");
        String englishString = returnString("Enter English:");//User enter data
        if (dictionary.containsKey(englishString)){
            //If the database exists such entered word
            //It will execute this block of code
            dictionary.remove(englishString);
            System.out.println("Successful.");
        }else{
            //If the database doesn't exist such entered word
            //It will execute this block of code
            System.out.println("This word does not exist in the database.");
            return;//exit immediately
        }
        //Because there was a change in the database, proceed to update the file
        updateFile();
    }

    //translate a word from the database
    // This method will require end-users to enter English content
    // Then it will search in the data base, find the cosresponding data to the entered word
    // If found, it will print out the console the cosresponding Vietnamese translation.
    // else return 
    public void translateWord() throws Exception{
        loadData();//load data from the file and turn it into HashMap Data Structure
        if (dictionary.isEmpty()){//User enter data
            System.out.println("Database is empty");
            return;//exit if empty
        }
        System.out.println("------------Translate-----------");
        String englishString = returnString("Enter English:");//User enter data
        if (dictionary.containsKey(englishString)){
            //If the database exists such entered word
            //It will execute this block of code
            System.out.println("Vietnamese:"+ dictionary.get(englishString));
        }else{
            //If the database doesn't exist such entered word
            //It will execute this block of code
            System.out.println("This word does not exist in the database.");
            return;//exit immediately
        }
        //Because there was a change in the database, proceed to update the file
        updateFile();
    }


    //clean file
    // Because RandomAccessFile doesn't support such way to clean file
    // We simply just write empty with PrintWriter
    public void cleanFile() throws Exception {
        //Access DictionaryData.txt with PrintWriter
        PrintWriter writer = new PrintWriter("DictionaryData.txt");
        //Replace all content of file with "" (literal empty)
        writer.print("");
        writer.close();
    }

    // rewrite file after every action
    // Because data would be modified after adding or removing, we need to update file content
    public void updateFile() throws Exception{
        // To prevent any file disruption, we clean file then rewrite the content
        cleanFile();
        //Access DictionaryData.txt with RandomAccessFile, allowing read and write on it
        RandomAccessFile f = new RandomAccessFile("DictionaryData.txt", "rw");
        //Rewrite the content of file from the database
        for (String englishString: dictionary.keySet()){
            //We need to encrypt all " " to "_" so that StringTokenizer will be able to read all data 
            //It should be like this:
            //                  -----------------------------------------------------------------
            //        Console: |English:the cat                                                  | as input
            //                 |Vietnamese:con meo                                               |
            //                  ----------------------------------------------------------------- 
            //
            //        file:  the_cat con_meo   (2 Strings, basically fit the HashMap data structure)  
            f.writeBytes(englishString.replace(" ", "_")+ " " + dictionary.get(englishString).replace(" ", "_"));
            //next line in file
            f.writeBytes("\r\n");
        }
    }

    //loadData
    //We need to load data before performing any action
    public void loadData() throws Exception{
        //Access DictionaryData.txt with RandomAccessFile, allowing read and write on it
        RandomAccessFile f = new RandomAccessFile("DictionaryData.txt", "rw");
        //StringTokenizer to split the whole read line into 2 Strings
        StringTokenizer t;
        try{//because reading file is a dangerous action(case file not exists)
            //We need to put it in a try-catch block
            String temp; //temp String which would be the whole line
            while ((temp = f.readLine())!=null){// f.readLine() alone would skip 1 lone, so will need to store it immediately with String temp
                //remove any exceeding spaces
                temp=temp.trim();
                //Tokenizing the whole line into String
                t = new StringTokenizer(temp);
                //Store the Token into to 2 Strings
                String englishString = t.nextToken();
                String vietnameseString = t.nextToken();
                //As the data in the file are encrypted, we need to decrypt it from "_" to " "
                //It should be like this:
                //        file:  the_cat con_meo   (2 Strings, basically fit the HashMap data structure)
                //
                //                  -----------------------------------------------------------------
                //        Console: |English:the cat                                                  | as output
                //                 |Vietnamese:con meo                                               |
                //                  ----------------------------------------------------------------- 
                dictionary.put(englishString.replace("_", " "), vietnameseString.replace("_", " "));
            }
        }catch (Exception e){}
    }
}