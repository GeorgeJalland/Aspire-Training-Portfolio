/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.flooringmastery.view;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
/**
 *
 * @author george
 */
public class UserIOConsoleImpl implements UserIO {
    
    public Scanner userInput = new Scanner(System.in);
    
    @Override
    public void print(String message){
        System.out.println(message);
    }
    
    @Override
    public void getEnter(String prompt){
        System.out.println(prompt);
        userInput.nextLine();
    }
    
    @Override
    public String readString(String prompt){
        boolean validInput = false;
        System.out.println(prompt);
        String out = "default";
        while (!validInput){
            out = userInput.nextLine();
            if (out.equals("")){
                System.out.println("Field cannot be left blank. "+prompt);
            } else {
                validInput = true;
            }
        }
        return out;        
    }

    
    @Override
    public String readString(String prompt, String yes, String no){
        System.out.println(prompt);
        String out = "";
        boolean validInput = false;
        while(!validInput){
            out = userInput.nextLine();            
            if (out.equals(yes) || out.equals(no)){
                validInput = true;                                
            } else {
                System.out.println("Invalid input. "+prompt);
            }
        }
        return out;
    }
    
    @Override
    public String readName(String prompt){
        System.out.println(prompt);
        String out = "";
        boolean validInput = false;
        while(!validInput){
            out = userInput.nextLine();            
            if (out.matches("[\\w\\.,]+")){ //This line validates user name
                validInput = true;                                
            } else {
                System.out.println("Invalid input. "+prompt);
            }
        }
        return out;        
    }
    
    @Override
    public String readDateAsString(String prompt, boolean future){
        System.out.println(prompt);
        String dateAsString = "";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        boolean validInput = false;
        while(!validInput){
            dateAsString = userInput.nextLine();
            try {
                LocalDate date = LocalDate.parse(dateAsString,formatter);
                if (future){
                    if (date.compareTo(LocalDate.now()) < 0){
                        System.out.println("Must enter a future date.");
                        continue;
                    }
                }
                validInput = true;
            } catch (DateTimeParseException e){
                System.out.println("Invalid input. "+prompt);
            }
        }
        return dateAsString;
    }
    
    @Override
    public int readInt(String prompt){
        boolean validInput = false;
        int output = 0;
        System.out.println(prompt);
        while (!validInput){
            try{
               output = userInput.nextInt();
               validInput = true;
            } catch (InputMismatchException e){
                System.out.println("Invalid input. "+prompt);
                userInput.next();
            }
        }
        userInput.nextLine();
        return output;        
    }

    @Override
    public int readInt(String prompt, int min,int max){
        boolean validInput = false;
        int output=0;
        System.out.println(prompt);
        while (!validInput){
            try{
                output = userInput.nextInt();
                if (output<min || output>max){
                    System.out.println("Invalid input. "+prompt);
                    continue;
                }
                validInput = true;
            } catch (InputMismatchException e){
                System.out.println("Invalid input. "+prompt);
                userInput.next();
            }
        }
        userInput.nextLine();
        return output;                
    }
    
    @Override
    public double readDouble(String prompt){
        boolean validInput = false;
        double output = 0;
        System.out.println(prompt);
        while (!validInput){
            try{
               output = userInput.nextDouble();
               if (output == 0){
                   System.out.println("Cannot deposit zero. "+prompt);
                   continue;
               }
               validInput = true;
            } catch (InputMismatchException e){
                System.out.println("Invalid input. "+prompt);
                userInput.next();
            }
        }
        userInput.nextLine();
        return output;        
    }    
    
    @Override
    public double readDouble(String prompt, double min,double max){
        boolean validInput = false;
        double output=0;
        System.out.println(prompt);
        while (!validInput){
            try{
                output = userInput.nextDouble();
                if (output<min || output>max){
                    System.out.println("Invalid input. "+prompt);
                    continue;
                }
                validInput = true;
            } catch (InputMismatchException e){
                System.out.println("Invalid input. "+prompt);
                userInput.next();
            }
        }
        userInput.nextLine();
        return output;                
    }
    
    @Override
    public float readFloat(String prompt){
        boolean validInput = false;
        float output = 0;
        System.out.println(prompt);
        while (!validInput){
            try{
               output = userInput.nextFloat();
               validInput = true;
            } catch (InputMismatchException e){
                System.out.println("Invalid input. "+prompt);
                userInput.next();
            }
        }
        userInput.nextLine();
        return output;        
    }  
    
    @Override
    public float readFloat(String prompt, float min,float max){
        boolean validInput = false;
        float output=0;
        System.out.println(prompt);
        while (!validInput){
            try{
                output = userInput.nextFloat();
                if (output<min || output>max){
                    System.out.println("Invalid input. "+prompt);
                    continue;
                }
                validInput = true;
            } catch (InputMismatchException e){
                System.out.println("Invalid input. "+prompt);
                userInput.next();
            }
        }
        userInput.nextLine();
        return output;                
    }
    
    @Override
    public long readLong(String prompt){
        boolean validInput = false;
        long output = 0;
        System.out.println(prompt);
        while (!validInput){
            try{
               output = userInput.nextLong();
               validInput = true;
            } catch (InputMismatchException e){
                System.out.println("Invalid input. "+prompt);
                userInput.next();
            }
        }
        userInput.nextLine();
        return output;        
    }
    
    @Override
    public long readLong(String prompt, long min,long max){
        boolean validInput = false;
        long output=0;
        System.out.println(prompt);
        while (!validInput){
            try{
                output = userInput.nextLong();
                if (output<min || output>max){
                    System.out.println("Invalid input. "+prompt);
                    continue;
                }
                validInput = true;
            } catch (InputMismatchException e){
                System.out.println("Invalid input. "+prompt);
                userInput.next();
            }
        }
        userInput.nextLine();
        return output;                
    }   
    
}
