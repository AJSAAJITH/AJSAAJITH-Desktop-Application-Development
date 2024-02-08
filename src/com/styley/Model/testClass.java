
package com.styley.Model;

import java.util.regex.Pattern;

public class testClass {
    public static void main(String[] args) {
       String s = "200124301568";
        System.out.println(Pattern.compile("([0-9]{9}[a-z]{1}|[0-9]{12})").matcher(s).matches());
    }
 
}
