package controller;


import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author KiD
 */
public class TemporaryMessages {
    static ArrayList<String> messages = new ArrayList<>();
    
    public static void putMessage(String message) {
        messages.add(message);
    }

    public static ArrayList<String> getMessages() {
        return messages;
    }
    
}
