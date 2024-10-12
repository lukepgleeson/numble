package online.glees.numble.service;

import java.util.Arrays;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * Group
 */
@Getter
@Setter
public class Group {
    private String answer;
    private List<Integer> values;

    public Group(String answer, int... x) {
        this.answer = answer;
        this.values = Arrays.stream(x).boxed().toList();
    }

    public String print() {
        String print = "";
        print += "The values ";
        values.forEach(value -> {
        //    print += value + ", ";
        });
        print += "are " + answer;
        return print;
    }

    public boolean validate (List<Integer> submission) {
        return submission.equals(values);
    }
}
