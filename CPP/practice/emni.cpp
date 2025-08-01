#include<iostream>
#include<fstream>
#include<sstream>
using namespace std;

int main(){
    ifstream inputFile("input1.txt");
    ofstream outputFile("Omg.txt");
    string s;
    while(getline(inputFile,s)){
        istringstream iss;
        string type,name;
        int match;
        iss >> type >> name >> match;
        if(type=="Allrounder"){
            outputFile << type << " " << name << " " << match;
        }else if(type == "Bowler"){
            outputFile << type << " " << name << " " << match;
        }else if(type == "Batsman"){
            outputFile << type << " " << name << " " << match;
        }
    }

}