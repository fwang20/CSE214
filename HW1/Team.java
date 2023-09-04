/**
* An abstract data type meant to serve as a representation a team of players.
*
*
* @author
*    Frank Wang., USB ID 115037306
*
*    Homework #1 for CSE 214, Spring 2023
*    Recitation 03
*
*    Feb 2nd, 2023
*/
public class Team{
  public static final int MAX_PLAYERS = 40;
  private Player[] players;

  /**
  * This is the constructor for a Team.
  * It creates a team object with a player array of size MAX_PLAYERS
  */
  public Team(){
    this.players = new Player[MAX_PLAYERS];
  }

  /**
  * This method returns a clone of this Team
  *
  * Precondition:
  * This Team is instantiated
  *
  * @return
  * A clone of this team (deep copy)
  */
  @Override
  public Object clone() {
    Team copy = new Team();
    int count = 0;
    if (this.size() != 0){
      for(Player p : this.players){
        if (p != null){
          copy.players[count] = (Player) p.clone();
          count++;
        }
      }
    }
    return copy;
  }

  /**
  * This method checks for equality on this and another team.
  *
  * @param obj
  * The object (Team) that is to be compared to this Team.
  *
  * Precondition:
  * This team object is already instantiated.
  *
  * @return
  * True if the inputted object refers to a team objects
  * with the same players in the same order as this Team
  * False otherwise
  */
  public boolean equals(Object obj) {
    boolean sameTeam = true;
    if (obj instanceof Team){
      Team check = (Team) obj;
      if (check.size() != this.size()) return false;
      for (int i = 0; i < check.size(); i++) {
        if (!check.players[i].getName().equals(this.players[i].getName()) ||
        check.players[i].getNumHits() != this.players[i].getNumHits() ||
        check.players[i].getNumErrors() != this.players[i].getNumErrors()){
          sameTeam = false;
        }
      }
    }
    else return false;
    return sameTeam;
  }

  /**
  * This method returns the number of players on the team
  *
  * Precondition:
  * The Team object has been instantiated
  *
  * @return
  * The number of the Players in the Team
  */
  public int size() {
    if (this.players == null) {
      return 0;
    }
    int count = 0;
    for (Object p : this.players) {
      if (p != null) {
        count++;
      }
    }
    return count;
  }

  /**
  * This method adds a player to the team at the given index, shifting all greater positions forward one.
  *
  * @param position
  * The position in the team that the player that is to be added is at.
  *
  * Precondition:
  * The team object has been instantiated
  * 1 ≤ position ≤ players_currently_in_team .
  * The number of Player objects in this Team is less than MAX_PLAYERS.
  *
  * Postcondition:
  * The new Player is now stored at the desired position in the Team.
  * All Players that were originally in positions greater than or equal
  * to position are forward one position.
  *
  * @throws IllegalArgumentException
  * when indicated position is not valid (such as no player being in the position)
  *
  * @throws FullTeamException
  * when the team this method is called on is already at MAX_PLAYERS
  */
  public void addPlayer(Player p, int position) throws IllegalArgumentException, FullTeamException{
    if (position > this.size() + 1 || position < 1) {
      throw new IllegalArgumentException("Invalid position for adding the new player.");
    }
    Team cloned = (Team) this.clone();  //stores a copy of the original team
    int originalSize = this.size();     //stores the size of the original team
    if (originalSize == MAX_PLAYERS) {
      throw new FullTeamException("Team is already full");
    }
    this.players[position - 1] = p;  //replaces the player into the wanted spot
    for (int i = position; i < originalSize + 1; i++) {
      this.players[i] = cloned.players[i-1]; //Move the players in the positions greater than or equal to array position over by 1.
    }
  }

  /**
  * This method removes the player from the team at the given index
  *
  * @param position
  * The position in the team that the player that is to be removed is at.
  *
  * Precondition:
  * The team object has been instantiated
  * 1 ≤ position ≤ players_currently_in_team .
  *
  * Postcondition:
  * The Player at the desired position in the Team has been removed.
  * All Players that were originally in positions greater than or equal to position are moved forward one position.
  *
  * @throws IllegalArgumentException
  * when indicated position is not valid (such as no player being in the position)
  */
  public void removePlayer(int position) throws IllegalArgumentException{
    if (position > this.size() || position < 0) {
      throw new IllegalArgumentException("No player at position " + position + " to remove.");
    }
    Team cloned = (Team) this.clone();  //stores a copy of the original team
    int originalSize = this.size();     //stores the size of the original team
    for (int i = position; i < originalSize; i++) {
      this.players[i - 1] = cloned.players[i];
    }
    this.players[originalSize - 1] = null;
  }

  /**
  * This method returns a reference to the Player at the inputted index on the team
  *
  * @param position
  * The index in the team that the player is in
  * (note that position is position on team, and not on the array)
  *
  * Precondition:
  * The Team object has been instantiated
  *
  * @return
  * A reference to the player in the given position
  *
  * @throws IllegalArgumentException
  * when the position given is not in the valid range.
  */
  public Player getPlayer(int position) throws IllegalArgumentException{
    if (position > this.size() || position < 1) {
      throw new IllegalArgumentException("Invalid position given to get a player.");
    }
    return this.players[position - 1];
  }

  /**
  * This method returns the reference of the player with the best amount
  * of hits or errors, depending on input
  *
  * @param stat
  * The stat to judge the leader player
  *
  * Precondition:
  * The team object has been instantiated
  *
  * @return
  * The player with the highest amount of hits or the lowest amount of errors
  * depending on input
  *
  * @throws IllegalArgumentException
  * when the inputted stat (param1) is not "hits" or "errors"
  */
  public Player getLeader(String stat) throws IllegalArgumentException{
    Player bestPlayer = this.players[0];
    int teamSize = this.size();
    if (stat.equals("hits")) {
      for (int i = 1; i < this.size(); i++) {
        if (this.players[i].getNumHits() > bestPlayer.getNumHits())
        bestPlayer = this.players[i];
      }
    }
    else if (stat.equals("errors")) {
      for (int i = 0; i < this.size(); i++) {
        if (this.players[i].getNumErrors() < bestPlayer.getNumErrors())
        bestPlayer = this.players[i];
      }
    }
    else throw new IllegalArgumentException("No such statistic.");
    return bestPlayer;
  }

  /**
  * This method prints out the String representation
  * of the Team method
  *
  * Precondition:
  * Team object has been instantiated
  *
  * Postcondition:
  * A neatly formatted table of each Player in the Team
  * on its own line with its position number has been displayed to the user.
  *
  * @throws IllegalArgumentException
  * when either of the numbers is negative
  */
  public void printAllPlayers(){
    System.out.println(this.toString());
  }

  /**
  * This method returns the String representation of a team
  * in the form of a neatly formatted table.
  *
  * Precondition:
  * Team object has been instantiated
  *
  * @return
  * Returns the string representation of the Team object.
  */
  public String toString() {
    String heading = String.format("%-10s%-20s%-12s%6s\n", "Player#", "Name", "Hits", "Errors");
    heading = heading + "-------------------------------------------------";
    String table = heading;
    for (int i = 0; i < this.size(); i++) {
      table = table + String.format("\n%-10d%-10s", i + 1, this.players[i].toString());
    }
    return table;
  }
}
