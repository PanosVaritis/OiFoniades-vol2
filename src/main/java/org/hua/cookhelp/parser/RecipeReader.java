/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.hua.cookhelp.parser;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class RecipeReader {
    public String readCookFile(String filePath) {
        StringBuilder content = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "UTF-8"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        } catch (IOException e) {
            System.err.println("Error during read process: " + e.getMessage());
        }

        return content.toString();
    }

    public String[] splitIntoSteps(String content) {
        return content.split("\\n\\s*\\n");
    }
}