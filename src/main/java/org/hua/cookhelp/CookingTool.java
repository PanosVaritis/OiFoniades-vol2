package org.hua.cookhelp;

public class CookingTool {
    private String name;

    public CookingTool(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    @Override
    public String toString(){
        return name;
    }
}
