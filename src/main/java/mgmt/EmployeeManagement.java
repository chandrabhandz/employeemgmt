package mgmt;

import db.MongoDAO;
import org.bson.Document;
import org.json.JSONObject;

import java.util.List;
import java.util.Scanner;

public class EmployeeManagement {
    final MongoDAO mongoDAO = new MongoDAO();
    public void insertData(){
        try{
            System.out.println("Enter json string for employee like = {'name':'test'}");
            Scanner sc = new Scanner(System.in);
            String jsonStr = sc.nextLine();
            JSONObject json = new JSONObject(jsonStr);
            String name = json.getString("name");
            Document document = new Document();
            document.append("name",name);
            mongoDAO.create(document);
        }catch(Exception ex){

        }

    }
    public void showSingleData(){
        try{
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter Employee name which record you wants to get.");
            String name = sc.nextLine();
            Document document = new Document();
            document.append("name",name);
            List<Document> documents = mongoDAO.read(document);
            dataFormatter();
            System.out.print("Id");
            System.out.println("\t\t\t\t\t\t\t\tName");
            for(Document document1 : documents){
                System.out.println("| "+document1.get("_id")+" |\t"+document1.get("name"));
            }
            System.out.println();
        }catch(Exception ex){

        }
    }
    public void showAllData(){
        try{
            List<Document> documents = mongoDAO.readAll();
            dataFormatter();
            System.out.print("Id");
            System.out.println("\t\t\t\t\t\t\t\tName");
            for(Document document1 : documents){
                System.out.println("| "+document1.get("_id")+" |\t"+document1.get("name"));
            }
            System.out.println();
        }catch(Exception ex){

        }
    }
    public void dataFormatter(){
        for(int i=0;i<3;i++){
            if(i==0){
                System.out.println("#####################################################");
            }
            if(i==1){
                System.out.println("+                 Employee Data                     +");
                for(int j=0;j<1;j++){
                    System.out.println("+                                                   +");
                }
            }
            if(i==2){
                System.out.println("#####################################################");
            }
        }
    }
}
