/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package book;

/**
 *
 * @author Justyna
 */
public class Person  {
    private String surname;
    private String firstname;
    private String phone;
    private String email;
    
    public Person (String surname, String firstname, String phone, String email){
        this.surname=surname;
        this.firstname=firstname;
        this.phone=phone;
        this.email=email;
    }
    
    public void set(String surname, String firstname, String phone, String email){
        this.surname=surname;
        this.firstname=firstname;
        this.phone=phone;
        this.email=email;
    }
    public String getSurname(){
        return surname;
    }
    public String getFirstname(){
        return firstname;
    }
    public String getPhone(){
        return phone;
    }
    public String getEmail(){
        return email;
    }
           
}
