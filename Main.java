public class Main {
    //menu method
    public void menu() throws Exception{
        Dictionary dc = new Dictionary();   //Declare Dictionary as object to load its method easier
        Validator validator = new Validator(); //Declare Validator as object to load its method easier
        int choice = 0; // initialize choice
        while(choice!= 4){
            dc.loadData(); // Preload data of dictionary
            System.out.println("=======Dictionary Program=======");
            System.out.println("1.Add Word");
            System.out.println("2.Delete Word");
            System.out.println("3.Translate");
            System.out.println("4.Exit");
            choice = validator.getInt(); //Get user choice
            //switch-case perform certain operation from the input
            switch (choice){
                case 1:
                dc.addNewWord();//add new word
                break;
                case 2:
                dc.deleteWord();//delete word
                break;
                case 3:
                dc.translateWord();//show translation
                break;
                case 4://exit
                break;
                default://case other input
                System.out.println("Invalid command. Enter again:");
            }
        }
    }    
    //main
    public static void main(String[] args) throws Exception {
        Main main = new Main();
        main.menu();
    }
}