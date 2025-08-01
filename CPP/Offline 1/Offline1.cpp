#include<iostream>
#include<cstdlib>
using namespace std;

class Fraction{
    int numerator;
    int denominator;
    int Gcd(int a,int b){
        if(b==0) return a;
        return Gcd(b,a%b);
    }
    public:
    void set_numerator(int a){
        numerator = a;
    }
    void set_denominator(int a){
        denominator = a;
    }
    int get_numerator(){
        return numerator;
    }
    int get_denominator(){
        return denominator;
    }
    Fraction(){
        numerator = 0;
        denominator = 1;
    }
    Fraction(int n){
        numerator = n;
        denominator = 1;
    }
    Fraction(int n,int d){
        int gcd = Gcd(n,d);
        n/=gcd;
        d/=gcd;
        numerator = n;
        denominator = d;
    }
    Fraction add(Fraction &f){
        int denom = (f.denominator*this->denominator)/Gcd(f.denominator,this->denominator);
        int numer = f.numerator*(denom/f.denominator)+numerator*(denom/denominator);
        return Fraction(numer,denom);
    }
    Fraction add(int n){
        Fraction f(n,1);
        return add(f);
    }
    Fraction sub(Fraction &f){
        int denom = (f.denominator*this->denominator)/Gcd(f.denominator,this->denominator);
        int numer = numerator*(denom/denominator)-f.numerator*(denom/f.denominator);
        return Fraction(numer,denom);
    }
    Fraction sub(int n){
        Fraction f(n,1);
        return sub(f);
    }
    Fraction mul(Fraction &f){
        int numer = this->numerator * f.numerator;
        int denom = this->denominator * f.denominator;
        return Fraction(numer,denom);
    }
    Fraction mul(int n){
        int numer = n*numerator;
        return Fraction(numer,denominator);
    }
    Fraction div(Fraction &f){
        if(f.numerator==0){
            cout << "Can't divide by 0\n";
            return *this;

        }else{
            Fraction F(f.denominator,f.numerator);
            return mul(F);
        }
    }
    Fraction div(int n){
        if(n==0){
            cout << "Can't divide by 0\n";
            return *this;
        }else{
            int denom = n*denominator;
            return Fraction(numerator,denom);
        }
    }
    bool operator ==(Fraction &f){
        if(numerator!=f.numerator) return false;
        if(denominator!=f.denominator) return false;
        return true;
    }
    ~Fraction(){
    }
    void print(){
        cout << numerator << "/" << denominator << "\n";
    }
};

class FractionCollection{
    Fraction *fractions;
    int maxlength;
    int length;
    public:
    FractionCollection(){
        fractions = (Fraction *)malloc(10*sizeof(Fraction));
        maxlength = 10;
        length = 0;
    }
    FractionCollection(int n){
        fractions = (Fraction *)malloc(n*sizeof(Fraction));
        maxlength = n;
        length = 0;
    }
    ~FractionCollection(){
        free(fractions);
    }
    void insert(Fraction f){
        if(length<maxlength){
            fractions[length] = f;
            length++;
        }else{
            Fraction *fractions2 = (Fraction *)malloc((maxlength+1)*sizeof(Fraction));
            for(int i=0;i<length;i++){
                fractions2[i] = fractions[i];
            }
            free(fractions);
            fractions = fractions2;
            maxlength++;
            length++;
        }
    }
    void insert(int pos,Fraction f){
        if(length<maxlength){
            for(int i=length;i>pos;i--){
                fractions[i] = fractions[i-1];
            }
            fractions[pos] = f;
            length++;
        }else{
            Fraction *fractions2 = (Fraction *)malloc((maxlength+1)*sizeof(Fraction));
            for(int i=0;i<pos;i++){
                fractions2[i] = fractions[i];
            }
            fractions2[pos] = f;
            for(int i=pos+1;i<=length;i++){
                fractions2[i] = fractions[i-1];
            }
            free(fractions);
            fractions = fractions2;
            maxlength++;
            length++;
        }
    }
    void remove(){
        length--;
    }
    void remove(Fraction f){
        for(int i=0;i<length;i++){
            if(fractions[i]==f){
                length--;
                for(int j=i;j<length;j++){
                    fractions[j] = fractions[j+1];
                }
                return;
            }
        }
    }
    void remove(int pos){
        length--;
        for(int i=pos;i<length;i++){\
            fractions[i] = fractions[i+1];
        }
    }
    Fraction getmax(){
        Fraction maxx;
        double frac=0;
        for(int i=0;i<length;i++){
            int f = fractions[i].get_numerator()*1.0/fractions[i].get_denominator();
            if(f>frac){
                frac = f;
                maxx = fractions[i];
            }
        }
        return maxx;
    }

    Fraction getmin(){
        Fraction minn;
        double frac=1000000000.0;
        for(int i=0;i<length;i++){
            double f = (fractions[i].get_numerator()*1.0)/fractions[i].get_denominator();
            if(f<frac){
                frac = f;
                minn = fractions[i];
            }
        }
        return minn;
    }
    Fraction add(int start, int end){
        Fraction f;
        for(int i=start;i<=end;i++){
            f=f.add(fractions[i]);
        }
        return f;
    }
    Fraction mul(int start, int end){
        Fraction f(1,1);
        for(int i=start;i<=end;i++){
            f = f.mul(fractions[i]);
        }
        return f;
    }
    Fraction sub(int pos1, int pos2){
        Fraction f = fractions[pos1];
        return f.sub(fractions[pos2]);
    }
    Fraction div(int pos1, int pos2){
        Fraction f = fractions[pos1];
        return f.div(fractions[pos2]);
    }
    void print(){
        cout << "\nFRACTIONS\n-----------------------------------\n";
        for(int i=0;i<length;i++){
            cout << "Fraction " << i << ": "; fractions[i].print();
        }
        cout << "Max: "; 
        getmax().print();
        cout << "Min: "; 
        getmin().print();
        cout << "Summation: "; 
        add(0,length-1).print();
        cout << "Multiplication: "; 
        mul(0,length-1).print();
        // cout << "Sub(Pos0,Pos1): "; sub(0,1).print();
        // cout << "Div(Pos0,Pos1): "; div(0,1).print();
    }
};

int main(){
    //create Fraction with numerator, denominator
    Fraction a(5,2),b(7,2),c(9,2),d(28,5);
    cout<<"Fraction"<<endl;
    cout<<"-------------------------------"<<endl;
    cout<<"A: ";
    a.print();
    cout<<"B: ";
    b.print();
    cout<<endl;
    cout<<"Add(a,b): ";
    a.add(b).print();
    cout<<"Add(a,2): ";
    a.add(2).print();
    cout<<"Sub(a,b) ";
    a.sub(b).print();
    cout<<"Sub(a,2) ";
    a.sub(2).print();
    cout<<"Mul(a,b): ";
    a.mul(b).print();
    cout<<"Mul(a,2): ";
    a.mul(2).print();
    cout<<"Div(a,b): ";
    a.div(b).print();
    cout<<"Div(a,2): ";
    a.div(2).print();
    cout<<"Div(a,0): ";
    a.div(0).print();
    //Collection of Fractions
    Fraction e,f(5),g(10);
    FractionCollection fc(10);
    fc.insert(a);
    fc.insert(b);
    fc.insert(c);
    fc.print();
    cout<<"Sub(Pos0, Pos1): ";
    fc.sub(0,1).print(); //subtracts the fraction at pos1 from fraction at pos0
    cout<<"Div(Pos0, Pos1): ";
    fc.div(0,1).print(); //divides the fraction at pos0 by the fraction at pos1
    fc.remove(1); //removed 'b'
    fc.print();
    fc.remove(a);
    fc.print();
    fc.insert(d);
    fc.insert(0,e); //insert at pos0
    fc.insert(f);
    fc.insert(g);
    fc.print();
    fc.remove(); //removed the last fraction
    fc.print(); //notice the output
    return 0;
}