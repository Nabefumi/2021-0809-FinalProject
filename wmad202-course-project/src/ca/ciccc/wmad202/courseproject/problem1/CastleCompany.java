package ca.ciccc.wmad202.courseproject.problem1;

import java.util.ArrayList;
import java.util.Iterator;

public class CastleCompany {

    //Keep multiple constants in one place.
    enum landSlope {
        LandDOWN,
        LandUP,
        PEAKSANDVALLEY,
        HILL
    }

    public static void main(String[] args) {

        System.out.println("--------- Problem1 ---------");

        //1.ArrayList - peaksandvalleyLists
        ArrayList<ArrayList<Integer>> peaksandvalleyLists = OpenFileReader.getIntegerArray();

        OpenFileReader.InnerOpenFileReader l = new OpenFileReader.InnerOpenFileReader();

        //2.ArrayList - castlesFieldLists
        ArrayList<Integer> castlesFieldLists = l.getIntegerNumbers(peaksandvalleyLists, new IntegerInterface() {

            @Override
            public Integer getInteger(ArrayList<Integer> fieldLists) {
                int castlesCount = 0;
                int begin;
                int next = 0;
                int end;
                landSlope state;

                //Check for empty arrays
                if (fieldLists.isEmpty()) {
                    System.out.println("Empty always. You can not build a castle");
                    return 0;

                } else {
                    castlesCount++;
                }

                begin = fieldLists.get(0);

                if (fieldLists.size() != 1) {
                    next = fieldLists.get(1);
                }

                state = landSlope.PEAKSANDVALLEY;

                for (int i = 1; i < fieldLists.size() - 1; i++) {
                    end = begin;
                    begin = next;
                    next = fieldLists.get(i + 1);

                    // Upland
                    if (begin > end) {
                        if (begin < next)

                            // hill
                            state = landSlope.HILL;

                        else if (begin == next)

                            // Upper flat
                            state = landSlope.LandUP;

                        else if (begin > next) {

                            // Peaks
                            state = landSlope.PEAKSANDVALLEY;
                            castlesCount++;

                        }
                    }

                    // Downland
                    else if (begin < end) {
                        if (begin < next) {

                            // Peaks
                            state = landSlope.PEAKSANDVALLEY;
                            castlesCount++;

                        } else if (begin == next)

                            // Lower flat
                            state = landSlope.LandDOWN;
                        else if (begin > next) {

                            // Uphill
                            state = landSlope.HILL;
                        }
                    }
                    // Straight land
                    else if (begin == end) {
                        if (begin < next) {

                            // It isNot long peaks
                            if (state == landSlope.LandUP)
                                continue;

                            // Long peaks
                            else if (state == landSlope.LandDOWN) {
                                castlesCount++;
                                state = landSlope.LandUP;
                            }

                            // Some
                        } else if (begin == next)
                            continue;

                        else if (begin > next) {

                            // Long valley
                            if (state == landSlope.LandUP) {
                                castlesCount++;
                                state = landSlope.LandDOWN;

                                // It isNot long valley
                            } else if (state == landSlope.LandDOWN)
                                continue;
                        }
                    }
                }
                return castlesCount;
            }
        });

        Iterator<Integer> problem1BiltCastles = castlesFieldLists.iterator();
        Integer castlesValues = 0;

        while (problem1BiltCastles.hasNext()) {
            castlesValues++;
            System.out.println("Number" + castlesValues + "of castle build available: " + "" + problem1BiltCastles.next());
        }
    }
}
