import java.util.*;
public class NeoViewer{
  public static void main(String[] args){
    Scanner sc = new Scanner(System.in);
    NeoDataBase database = new NeoDataBase();
    System.out.println("Welcome to NEO Viewer!\n");
    while(true){
      printMenu();
      System.out.println("Select a menu option: ");
      String choice = sc.nextLine();
      switch(choice.trim().toUpperCase()){
        case "A":
        System.out.println("\nEnter the page to load: ");
        int page = sc.nextInt();
        sc.nextLine();
        String url = database.buildQueryURL(page);
        try{
          database.addAll(url);
          System.out.println("\nPage loaded successfully!");
        }
        catch(Exception ex){
          System.out.println(ex.getMessage());
          break;
        }
        break;

        case "S":
        outerWhile:
        while(true){
          System.out.println("\nR) Sort by referenceID");
          System.out.println("D) Sort by diameter");
          System.out.println("A) Sort by approach date");
          System.out.println("M) Sort by miss distance\n");
          System.out.println("\nSelect a menu option:");
          String choice2 = sc.nextLine();
          switch(choice2.trim().toUpperCase()){
            case "R":
            database.sort(new ReferenceIDComparator());
            System.out.println("Table sorted on reference ID.\n");
            break outerWhile;
            case "D":
            database.sort(new DiameterComparator());
            System.out.println("Table sorted on diameter.\n");
            break outerWhile;
            case "A":
            database.sort(new ApproachDateComparator());
            System.out.println("Table sorted on approach date.\n");
            break outerWhile;
            case "M":
            database.sort(new MissDistanceComparator());
            System.out.println("Table sorted on miss distance.\n");
            break outerWhile;
            default:
            System.out.println("Please enter an appropriate choice!\n");
            break outerWhile;
          }
        }
        break;

        case "P":
        System.out.println();
        database.printTable();
        System.out.println();
        break;

        case "Q":
        System.out.println("Program terminating normally...");
        System.exit(0);

        default:
        System.out.println("Please enter an appropriate option\n");
        break;
      }
    }

  }
  public static void printMenu(){
    System.out.println("Option Menu");
    System.out.println("  A) Add a page to the database");
    System.out.println("  S) Sort the database");
    System.out.println("  P) Print the database as a table.");
    System.out.println("  Q) Quit");
    System.out.println();
  }
}
