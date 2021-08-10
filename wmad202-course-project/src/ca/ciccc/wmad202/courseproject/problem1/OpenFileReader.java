package ca.ciccc.wmad202.courseproject.problem1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class OpenFileReader {

    public static ArrayList<ArrayList<Integer>> getIntegerArray() {

        ArrayList<ArrayList<Integer>> fieldLists = new ArrayList<>();

        Scanner s = null;
        try {
            s = new Scanner(new File("out/castles.txt"));

        while (s.hasNext()) {
            String stringNumbers = s.nextLine();
            ArrayList<Integer> list = new ArrayList<>();

            for (int i = 0; i < stringNumbers.length(); i++) {
                Character ch = stringNumbers.charAt(i);

                // Extract 1 to 9
                // ※ Integer.parseInt() doesn't working.... so I Changed Logic... The code before the change is shown at the end.
                if ('0' <= ch && ch <= '9') {
                    Integer integer = Character.getNumericValue(ch);
                    list.add(integer);
                }
            }
            fieldLists.add(list);
        }
            s.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return fieldLists;
    }

    public static class InnerOpenFileReader {
        // Change ArrayList<ArrayList<Integer>> to ArrayList<Integer> for return

        public ArrayList<Integer> getIntegerNumbers(ArrayList<ArrayList<Integer>> fieldLists, IntegerInterface iif){
            ArrayList<Integer> getIntegerNumbersList = new ArrayList<>();
            System.out.println(fieldLists);

            for (ArrayList<Integer> returnList : fieldLists) {
                Integer integerLists = iif.getInteger(returnList);
                getIntegerNumbersList.add(integerLists);
            }
            return getIntegerNumbersList;
        }
    }
}



//public class OpenFileReader {
//
//    public static void main(String[] args){
//        Scanner s = null;
//        try {
//            s = new Scanner(new File("out/castles.txt"));
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        ArrayList<String> list = new ArrayList<String>();
//        while (s.hasNext()){
//            list.add(s.next());
//        }
//        s.close();
//        ArrayList<Integer> resultList = getIntegerArray(list);
//        System.out.println(resultList);
//
//    }
//
//    private static ArrayList<Integer> getIntegerArray(ArrayList<String> stringArray) {
//        ArrayList<Integer> result = new ArrayList<Integer>();
//
//        for(String stringValue : stringArray) {
//            try {
//                //Convert String to Integer, and store it into integer array list.
//                    result.add(Integer.valueOf(stringValue));
//                    System.out.println(result);
//
//            } catch(NumberFormatException nfe) {
//            }
//        }
//        return result;
//    }
//}
