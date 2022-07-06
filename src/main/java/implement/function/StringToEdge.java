package implement.function;

import graph.TemporalEdge;
import org.javatuples.Pair;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Extract properties from String and create TemporalEdge
 */
public class StringToEdge {
    /**
     * Get all the TemporalEdge from the CSV-File
     * @param s String from CSV-File
     * @return TemporalEdge from each String
     */
    public static TemporalEdge convertToTemporalEdge(String s){
        String[] temp = s.split(";");
        String[] timeString = temp[temp.length-1].split("\\),\\(");

        String valid_time_string = timeString[0];
        String transaction_time_string = timeString[1];

        // get the time via pattern and matcher
        Pattern pattern = Pattern.compile("-?\\d+");

        Matcher valid_matcher = pattern.matcher(valid_time_string);
        Matcher tx_machter = pattern.matcher(transaction_time_string);

        ArrayList<String> v_timeArray = new ArrayList<>();
        ArrayList<String> tx_timeArray = new ArrayList<>();

        while(valid_matcher.find()){
            v_timeArray.add(valid_matcher.group());
        }
        while(tx_machter.find()){
            tx_timeArray.add(tx_machter.group());
        }

        // the time interval for valid and transaction time
        Pair<Long, Long> valid = new Pair<>(Long.valueOf(v_timeArray.get(0)),Long.valueOf(v_timeArray.get(1)));
        Pair<Long, Long> transaction = new Pair<>(Long.valueOf(tx_timeArray.get(0)),Long.valueOf(tx_timeArray.get(1)));

        return new TemporalEdge(temp[0].hashCode(), temp[1].hashCode(), temp[2].hashCode(), temp[3].hashCode(), temp[4], temp[5], valid,transaction);
    }
}
