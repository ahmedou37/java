import java.util.Random;
import java.util.Scanner;

public class TicTac {
    static   boolean  player1Turn =true;
     protected int g;
     protected int e;
     protected int a;
     protected int r;
     protected int w;
    Scanner scanner = new Scanner(System.in);
    Random random=new Random();
    protected String o;
    protected String x;
    protected  String s="    ";
    private String choix;
    protected String[][] matrice = new String[3][3];
    public  void ask(){
     do{ 
      System.out.println("choose between x and o:");
      choix=scanner.next();
      if (choix.equals("x")) {
           o="  x "; 
           x="  o ";
      }else if(choix.equals("o")){
           o="  o ";
           x="  x "; 
      }else {
        System.out.println("enter valide letter");
      }}while(!choix.equals("x")&&!choix.equals("o"));
    }
    
    
    public void remplir(){
      for (int i = 0; i < 3; i++) {
          for (int j = 0; j < 3; j++) {
               matrice[i][j]=s;
          }
      }
    }

    public void display(){
      System.out.println(matrice[0][0]+" |"+matrice[0][1]+" |"+matrice[0][2]+"\n"+matrice[1][0]+" |"+matrice[1][1]+" |"+matrice[1][2]+"\n"+matrice[2][0]+" |"+matrice[2][1]+" |"+matrice[2][2]);
    }
    

    public synchronized  void position1()throws InterruptedException{
        while (!player1Turn) { 
            wait();
        }
        System.out.println("player 1 role : ");
        double f;
        try {      
            System.out.println("choose le position :");
             f=scanner.nextDouble();
            e=(int) f;
            if (0<=f&&f<3&&(f-e)>=0&&(10*(f-e))<3) {
                  g =(int) (10*(f-e));
            }else{
                 System.out.println("enter nombre between 0 and 2");
                 return;
            }
            if (matrice[e][g]==s) {
                matrice[e][g]=o;
            }else{
                 System.out.println("already taken");
                 return;
            }
           } catch (Exception s) {
               System.out.println("ere invalide "+s);
           }
        do { 
            r=random.nextInt(3);
             w=random.nextInt(3);    
         } while (!(matrice[r][w]==s));
             matrice[r][w]=x;
             display();
             player1Turn=false;
             notify();
 } 
 
    public synchronized  void position2()throws InterruptedException{
      player1Turn=false;
      notify();
      while (player1Turn) { 
          wait();
      }
        System.out.println("player 2 role : ");
        double f;
        try {      
            System.out.println("choose le position :");
             f=scanner.nextDouble();
            e=(int) f;
            if (0<=f&&f<3&&(f-e)>=0&&(10*(f-e))<3) {
                  g =(int) (10*(f-e));
            }else{
                 System.out.println("enter nombre between 0 and 2");
                 return;
            }
            if (matrice[e][g]==s) {
                matrice[e][g]=o;
            }else{
                 System.out.println("already taken");
                 return;
            }
           } catch (Exception s) {
               System.out.println("ere invalide "+s);
           }
        do { 
            r=random.nextInt(3);
             w=random.nextInt(3);    
         } while (!(matrice[r][w]==s));
             matrice[r][w]=x;
             display();
             player1Turn=true;
             notify();
    } 

   
    private boolean ab = false;  
    private boolean b = false;
    
    public boolean fin() {
        for (int i = 0; i < 3; i++) {
            if (matrice[i][0].equals(o) && matrice[i][1].equals(o) && matrice[i][2].equals(o)) {
                ab = true;  
                return true;        
            } else if(matrice[i][0].equals(x) && matrice[i][1].equals(x) && matrice[i][2].equals(x)) {
                b = true;
                return true;
            }
        }
        
        for (int j = 0; j < 3; j++) {
            if (matrice[0][j].equals(o) && matrice[1][j].equals(o) && matrice[2][j].equals(o)) {
                ab = true;  
                return true;
            } else if (matrice[0][j].equals(x) && matrice[1][j].equals(x) && matrice[2][j].equals(x)) {
                b = true;
                return true;
            }
        }
        
        if (matrice[0][0].equals(o) && matrice[1][1].equals(o) && matrice[2][2].equals(o)) {
            ab = true;  
            return true;
        } else if (matrice[0][0].equals(x) && matrice[1][1].equals(x) && matrice[2][2].equals(x)) {
            b = true;
            return true;
        } 
        
        if (matrice[0][2].equals(o) && matrice[1][1].equals(o) && matrice[2][0].equals(o)) {
            ab = true; 
            return true;
        } else if (matrice[0][2].equals(x) && matrice[1][1].equals(x) && matrice[2][0].equals(x)) {
            b = true;
            return true;
        }
        return false;
    }
     
    public void decision() {
        if (ab) {  
            System.out.println("You win!");    
        } else if(b) {
            System.out.println("You lose!");
        }
    }
}
