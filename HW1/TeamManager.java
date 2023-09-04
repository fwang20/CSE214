/**
* A class for testing Player.java and Team.java.
* Creates a menu for manipulating at max five teams and their players.
*
* @author
*    Frank Wang., USB ID 115037306
*
*    Homework #1 for CSE 214, Spring 2023
*    Recitation 03
*
*    Feb 2nd, 2023
*/
import java.util.*;

public class TeamManager {
  public static final int MAX_TEAMS = 5;

  public static void main(String[] args) {
    Team[] teams = new Team[MAX_TEAMS];
    int selectedNum = 0;
    for (int i = 0; i < MAX_TEAMS; i++) {
      teams[i] = new Team();
    }
    Scanner sc = new Scanner(System.in);
    String choice;
    System.out.println("Welcome to TeamManager!");
    while (true){
      System.out.println("\nTeam " + (selectedNum + 1) + " is currently selected.");
      printMainMenu();
      System.out.print("Select a menu option: ");
      choice = sc.nextLine();

      switch(choice.trim().toUpperCase()) {
        case "A":
        System.out.print("Enter the player's name: ");
        String name = sc.nextLine();
        System.out.print("Enter the number of hits: ");
        int hits = sc.nextInt();
        System.out.print("Enter the number of errors: ");
        int errors = sc.nextInt();
        System.out.print("Enter the position: ");
        int insertPosition = sc.nextInt();
        sc.nextLine();
        Player tempPlayer = new Player();
        tempPlayer.setName(name);
        try {
          tempPlayer.setNumHits(hits);
          tempPlayer.setNumErrors(errors);
          teams[selectedNum].addPlayer(tempPlayer, insertPosition);
        }
        catch (FullTeamException | IllegalArgumentException ex){
          System.out.println(ex.getMessage() + "\n");
          break;
        }
        System.out.print("Player added: " + name + " - " + hits + " hits, " + errors + " errors");
        System.out.println();
        break;

        case "G":
        System.out.println();
        System.out.print("Enter the position: ");
        int position = sc.nextInt();
        sc.nextLine();
        try{
          System.out.println(teams[selectedNum].getPlayer(position).getName() + " - " + teams[selectedNum].getPlayer(position).getNumHits() + " hits, " + teams[selectedNum].getPlayer(position).getNumErrors() + " errors");
          System.out.println();
        }
        catch (Exception ex) {
          System.out.println(ex.getMessage() + "\n");
        }
        break;

        case "L":
        System.out.print("\nEnter the stat: ");
        String leaderChoice = sc.nextLine();
        if (leaderChoice.equals("errors") || leaderChoice.equals("hits")){
          try {
            Player leader = teams[selectedNum].getLeader(leaderChoice);
            System.out.println("Leader in " + leaderChoice + ": " + leader.getName() + " - " + leader.getNumHits() + " hits, " + leader.getNumErrors() + " errors");
            System.out.println();
          }
          catch (Exception ex) {
            System.out.println(ex.getMessage() + "/n");
            break;
          }
          break;
        }
        System.out.println("No such statistic.");
        System.out.println();
        break;


        case "R":
        System.out.print("\nEnter the position: ");
        int removeIndex = sc.nextInt();
        sc.nextLine();
        try {
          String removedName = teams[selectedNum].getPlayer(removeIndex).getName();
          teams[selectedNum].removePlayer(removeIndex);
          System.out.println("Player Removed at position "+ (removeIndex) + "\n");
          System.out.println(removedName + " has been removed from the team.\n");
        }
        catch(Exception ex){
          System.out.println("No player at position " + removeIndex + " to remove\n"); //manually printed because otherwise it would print
        }
        break;


        case "P":
        System.out.print("\nSelect team index: ");
        int printNum = sc.nextInt();
        sc.nextLine();
        System.out.println();
        teams[printNum - 1].printAllPlayers();
        break;


        case "S":
        System.out.println("There are " + teams[selectedNum].size() + " player(s) in the current Team.");
        break;

        case "T":
        System.out.println();
        System.out.print("Enter team index to select: ");
        int selectedIndex = sc.nextInt();
        sc.nextLine();
        if (selectedIndex > MAX_TEAMS){
          System.out.println("\nInvalid index for team.\n");
          break;
        }
        selectedNum = selectedIndex - 1;
        System.out.println();
        System.out.println("Team " + selectedIndex + " has been selected.\n");
        break;


        case "C":
        System.out.print("\nSelect a team to clone from: ");
        int cloneFrom = sc.nextInt();
        System.out.print("Select a team to clone to: ");
        int cloneTo = sc.nextInt();
        sc.nextLine();
        try {
          Team copy = (Team) teams[cloneFrom - 1].clone();
          teams[cloneTo - 1] = copy;
        }
        catch (Exception ex){
          System.out.println("Inputted team numbers must be between 1 and " + MAX_TEAMS + "\n");
        }
        System.out.println("\nTeam " + cloneFrom + " has been copied to team " + cloneTo + "\n");
        break;


        case "E":
        System.out.print("\nSelect first team index: ");
        int firstIndex = sc.nextInt();
        System.out.print("Select second team index: ");
        int secondIndex = sc.nextInt();
        sc.nextLine();
        try {
          if (teams[firstIndex - 1].equals(teams[secondIndex - 1])){
            System.out.println("\nThese teams are equal.");
          }
          else System.out.println("\nThese teams are not equal.\n");
        }
        catch (Exception ex){
          System.out.println("Inputted team numbers must be between 1 and " + MAX_TEAMS + "\n");
        }
        break;


        case "U":
        System.out.print("\nEnter the player to update: ");
        String updateName = sc.nextLine();
        int teamIndex = -1;
        System.out.print("Enter stat to update: ");
        String updateStat = sc.nextLine();
        if (updateStat.equals("errors") || updateStat.equals("hits")){
          System.out.print("Enter the new number of " + updateStat + ": ");
          int newStat = sc.nextInt();
          sc.nextLine();
          for (int i = 0; i< teams[selectedNum].size();i++) {
            if (teams[selectedNum].getPlayer(i + 1).getName().equals(updateName)) {
              teamIndex = i + 1;
            }
          }
          if (teamIndex == -1){
            System.out.println("\nPlayer not found.\n");
            break;
          }
          try {
            if (updateStat.equals("hits")) teams[selectedNum].getPlayer(teamIndex).setNumErrors(newStat);
            if (updateStat.equals("errors")) teams[selectedNum].getPlayer(teamIndex).setNumErrors(newStat);
            System.out.println();
            System.out.println("Updated " + updateName + " " + updateStat);
            break;
          }
          catch (Exception ex){
            System.out.println("Invalid update error: " + ex.getMessage());
          }
        }
        System.out.println("\nNo such statistic.\n");
        break;


        case "Q":
        System.out.println("\nProgram terminating normally...");
        System.exit(0);
        break;

        default:
        System.out.println("\nInvalid entry for the menu. Please enter an appropriate choice.");
        break;
      }
    }
  }

  private static void printMainMenu() {
    System.out.println("\n\nPlease select an option:");
    System.out.println("A)  Add Player.");
    System.out.println("G)  Get Player stats.");
    System.out.println("L)  Get leader in a stat.");
    System.out.println("R)  Remove a player.");
    System.out.println("P)  Print all players.");
    System.out.println("S)  Size of team.");
    System.out.println("T)  Select team.");
    System.out.println("C)  Clone team.");
    System.out.println("E)  Team equals.");
    System.out.println("U)  Update stat.");
    System.out.println("Q)  Quit.\n");
  }
}
