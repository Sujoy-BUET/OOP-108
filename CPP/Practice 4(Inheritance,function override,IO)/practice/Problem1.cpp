#include <iostream>
#include <string>
#include <iomanip>
#include <fstream>  // for file handling
#include <vector>
#include <sstream>

using namespace std;

//**************** ****************************************************************** **********************/
//**************** 'Cricketer' class represents a general cricketer. Do not change!!! **********************/
//****************   You can not add any other properties or methods in any classes   **********************/
//**************** ****************************************************************** **********************/
class Cricketer {
protected:
    int matchPlayed;
    string name;
    int age;

public:
    // constructor to initialize name, age, and matches played
    Cricketer(string s = "Unknown", int a = 0, int m = 0) : name(s), age(a), matchPlayed(m) {}

    string getName() { return name; }
    int getMatchPlayed() { return matchPlayed; }
    int getAge() { return age; }
    void setName(string n) { name = n; }
    void setAge(int a) { age = a; }
    void setMatchPlayed(int m) { matchPlayed = m; }

    // Prints basic cricketer info
    virtual void printInfo(ofstream& out) {
        out << "Name: " << name << ", Age: " << age << ", Matches Played: " << matchPlayed << endl;
    }
    
    virtual ~Cricketer() {}
};

//**************** ****************************************************************** **********************/
//**************** ****************************************************************** **********************/

// 'Batsman' class represents a batsman
// Inherit 'Cricketer' class to implement 'Batsman'
class Batsman : public virtual Cricketer {
protected:
    int runScored;
    int ballsFaced;

public:
    // Implement Constructor to initialize batting details along with cricketer data
    // Take Name, Age, Matches played, Runs, Balls 
    Batsman(string n = "Unknown", int a = 0, int m = 0, int runs = 0, int balls = 0) 
        : Cricketer(n, a, m), runScored(runs), ballsFaced(balls) {}

    // Compute batting average: runs/matches
    double computeBattingAverage() {
        if (matchPlayed == 0) return 0.0;
        return (double)runScored / matchPlayed;
    }

    // Compute strike rate: (runs/balls faced) * 100
    double computeStrikeRate() {
        if (ballsFaced == 0) return 0.0;
        return ((double)runScored / ballsFaced) * 100;
    }

    // Getter & Setter for total runs
    int getRunScored() { return runScored; }
    void setRunScored(int runs) { runScored = runs; }
    
    // Getter & Setter for balls faced
    int getBallsFaced() { return ballsFaced; }
    void setBallsFaced(int balls) { ballsFaced = balls; }

    // Print batsman-specific stats; you have to use the base class's method here.
    void printInfo(ofstream& out) {
        Cricketer::printInfo(out);
        out << "Runs Scored: " << runScored << ", Balls Faced: " << ballsFaced;
        out << ", Batting Average: " << fixed << setprecision(2) << computeBattingAverage();
        out << ", Strike Rate: " << fixed << setprecision(2) << computeStrikeRate() << endl;
    }
};

// 'Bowler' class represents a bowler
// Inherit 'Cricketer' class to implement 'Bowler'
class Bowler : public virtual Cricketer {
protected:
    int wicketsTaken;
    int runsConceded;
    double oversBowled;

public:
    // Implement Constructor to initialize bowling details along with cricketer data
    // Take Name, Age, Matches played, Wickets, RunsConceded, Overs
    Bowler(string n = "Unknown", int a = 0, int m = 0, int wickets = 0, int runsConc = 0, double overs = 0.0)
        : Cricketer(n, a, m), wicketsTaken(wickets), runsConceded(runsConc), oversBowled(overs) {}

    // Compute average wickets per match
    double computeWicketAverage() {
        if (matchPlayed == 0) return 0.0;
        return (double)wicketsTaken / matchPlayed;
    }

    // Compute economy rate: runs conceded per over
    double computeEconomy() {
        if (oversBowled == 0) return 0.0;
        return (double)runsConceded / oversBowled;
    }

    // Getter & Setter for wickets
    int getWicketsTaken() { return wicketsTaken; }
    void setWicketsTaken(int wickets) { wicketsTaken = wickets; }
    
    // Getter & Setter for runs conceded
    int getRunsConceded() { return runsConceded; }
    void setRunsConceded(int runs) { runsConceded = runs; }
    
    // Getter & Setter for overs bowled
    double getOversBowled() { return oversBowled; }
    void setOversBowled(double overs) { oversBowled = overs; }

    // Print bowler-specific stats; you have to use the base class's method here.
    void printInfo(ofstream& out) {
        Cricketer::printInfo(out);
        out << "Wickets Taken: " << wicketsTaken << ", Runs Conceded: " << runsConceded;
        out << ", Overs Bowled: " << fixed << setprecision(1) << oversBowled;
        out << ", Wicket Average: " << fixed << setprecision(2) << computeWicketAverage();
        out << ", Economy Rate: " << fixed << setprecision(2) << computeEconomy() << endl;
    }
};

// The following class represents an allrounder (inherits from both Batsman and Bowler)
class Allrounder : public Batsman, public Bowler {
public:
    // Implement Constructor to initialize all data for an allrounder (batsman and bowler)
    Allrounder(string n = "Unknown", int a = 0, int m = 0, int runs = 0, int balls = 0, 
               int wickets = 0, int runsConc = 0, double overs = 0.0)
        : Cricketer(n, a, m), Batsman(n, a, m, runs, balls), Bowler(n, a, m, wickets, runsConc, overs) {}

    // Print both batting and bowling statistics; you have to use the base class's method here.
    void printInfo(ofstream& out) {
        Cricketer::printInfo(out);
        out << "Runs Scored: " << runScored << ", Balls Faced: " << ballsFaced;
        out << ", Batting Average: " << fixed << setprecision(2) << computeBattingAverage();
        out << ", Strike Rate: " << fixed << setprecision(2) << computeStrikeRate() << endl;
        out << "Wickets Taken: " << wicketsTaken << ", Runs Conceded: " << runsConceded;
        out << ", Overs Bowled: " << fixed << setprecision(1) << oversBowled;
        out << ", Wicket Average: " << fixed << setprecision(2) << computeWicketAverage();
        out << ", Economy Rate: " << fixed << setprecision(2) << computeEconomy() << endl;
    }
};

// input file style
// TYPE Name Age Matches Runs Balls Wickets RunsConceded Overs

int main() {
    // You have to take input from "input1.txt" and you have to generate an "output.txt" file like "Demo_output.txt"
    // First take the players information from "input.txt" and create necessary player objects 
    // Then save them in an array of players
    // Finally, show the players' information 
    // NOTE: Players can be of any type 
    
    ifstream inputFile("input1.txt");
    ofstream outputFile("output.txt");
    
    if (!inputFile.is_open()) {
        cout << "Error: Could not open input1.txt" << endl;
        return 1;
    }
    
    if (!outputFile.is_open()) {
        cout << "Error: Could not create output.txt" << endl;
        return 1;
    }
    
    vector<Cricketer*> players;
    string line;
    
    while (getline(inputFile, line)) {
        if (line.empty()) continue;
        istringstream iss(line);
        string type, name;
        int age, matches, runs, balls, wickets, runsConceded;
        double overs;
        
        iss >> type >> name >> age >> matches;
        
        if (type == "Batsman") {
            iss >> runs >> balls;
            players.push_back(new Batsman(name, age, matches, runs, balls));
        }
        else if (type == "Bowler") {
            iss >> wickets >> runsConceded >> overs;
            players.push_back(new Bowler(name, age, matches, wickets, runsConceded, overs));
        }
        else if (type == "Allrounder") {
            iss >> runs >> balls >> wickets >> runsConceded >> overs;
            players.push_back(new Allrounder(name, age, matches, runs, balls, wickets, runsConceded, overs));
        }
    }
    
    // Print player information to output file
    outputFile << "Cricket Team Statistics:" << endl;
    outputFile << "========================" << endl << endl;
    
    for (int i = 0; i < players.size(); i++) {
        outputFile << "Player " << (i + 1) << ":" << endl;
        players[i]->printInfo(outputFile);
        outputFile << endl;
    }
    
    // Clean up memory
    for (Cricketer* player : players) {
        delete player;
    }
    
    inputFile.close();
    outputFile.close();
    
    cout << "Player statistics have been written to output.txt" << endl;
    
    return 0;
}