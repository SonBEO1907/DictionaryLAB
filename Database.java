import java.util.HashMap;

public interface Database {
    //The sole purpose of this database is to store the data of the translation temporarily, with the English word as keys and Vietnamese translation as content
    HashMap<String/*This is the english word */,String/*This is VNese translation */> dictionary = new HashMap<>();
}
