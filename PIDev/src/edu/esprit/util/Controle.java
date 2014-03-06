/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.esprit.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Melek
 */
public class Controle {

    public Controle() {}

   public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
    Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{1,6}$", Pattern.CASE_INSENSITIVE);
   public static final Pattern VALID_STRING_REGEX =
    Pattern.compile("[A-Z]{2,10}", Pattern.CASE_INSENSITIVE);
    public static final Pattern VALID_LOGIN_MDP_REGEX =
    Pattern.compile("\\w", Pattern.CASE_INSENSITIVE);
     public static final Pattern VALID_NUMBER_REGEX =
    Pattern.compile("[0-9]", Pattern.CASE_INSENSITIVE);

public static boolean controlemail(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(emailStr);
        return matcher.find();}
public static boolean controlestring(String chaine) {
        Matcher matcher = VALID_STRING_REGEX .matcher(chaine);
        return matcher.find();}

public static boolean controlelogin(String chaine) {
        Matcher matcher = VALID_LOGIN_MDP_REGEX .matcher(chaine);
        return matcher.find();}

public static boolean controlenumber(String num) {
        Matcher matcher = VALID_NUMBER_REGEX .matcher(num);
        return matcher.find();}

}
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

