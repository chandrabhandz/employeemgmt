import mgmt.EmployeeManagement;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        EmployeeManagement employeeManagement = new EmployeeManagement();
        try{

            Scanner sc = new Scanner(System.in);
            Integer input ;
            do{
                System.out.println("1. Insert Employee Data.");
                System.out.println("2. Show Single Record.");
                System.out.println("3. Show All Records");
                System.out.println("4. Exit");
                input = sc.nextInt();
                switch (input){
                    case 1:{
                        //Insert record
                        employeeManagement.insertData();
                        break;
                    }
                    case 2:{
                        //Show single record
                        employeeManagement.showSingleData();
                        break;
                    }
                    case 3:{
                        // Show all records
                        employeeManagement.showAllData();
                    }
                    default:
                        break;

                }
            }while(input !=4);


        }catch(Exception e){

        }
    }

}
