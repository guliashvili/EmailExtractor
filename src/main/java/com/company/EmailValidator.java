package com.company;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gguliash on 11/14/15.
 */
public class EmailValidator {
    private List<Validator> validators = new ArrayList<>();
    public void addValidator(Validator validator){
        validators.add(validator);
    }

    public List<String> validate(List<String> possibles){
        List<String> ret = new ArrayList<>();
        for(String possible : possibles){
            boolean isCorrect = true;
            for(Validator validator : validators){
                if(!(isCorrect &= validator.matches(possible))) break;
            }
            if(isCorrect) ret.add(possible);
        }
        return ret;
    }
}
