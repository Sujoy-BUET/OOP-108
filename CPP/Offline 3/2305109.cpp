#include<iostream>
#include<algorithm>
#include<cmath>
using namespace std;

int lcm(int a,int b){
    return a / __gcd(a, b) * b;
}

class Fraction{
    int numerator,denominator;
    public:
    int get_numerator(){
        return numerator;
    }
    int get_denominator(){
        return denominator;
    }
    void set_numerator(int n){
        numerator = n;
    }
    void set_denominator(int d){
        denominator = d;
    }
    Fraction(){
        numerator = 0;
        denominator = 1;
    }
    Fraction(int n){
        numerator = n;
        denominator = 1;
    }
    Fraction(int a,int b){
        if(b==0){
            cerr << "Error: Denominator cannot be zero.\n";
            exit(EXIT_FAILURE); 
        }
        int g = __gcd(a,b);
        a/=g,b/=g;
        if(b<0){
            a = -a;
            b = -b;
        }
        numerator = a,denominator = b;
    }
    Fraction operator +(const Fraction &f) const{
        int d = lcm(denominator,f.denominator);
        int n = (d/denominator)*numerator + (d/f.denominator)*f.numerator;
        return Fraction(n,d);
    }
    Fraction operator +(float fl) const {
        Fraction f = convertFloatToFraction(fl);
        return *this+f;
    }
    friend Fraction operator +(float fl,const Fraction &f){
        return f+fl;
    }
    Fraction operator -(const Fraction &f) const {
        int d = lcm(denominator,f.denominator);
        int n = (d/denominator)*numerator - (d/f.denominator)*f.numerator;
        return Fraction(n,d);
    }
    Fraction operator -(float fl) const {
        Fraction f = convertFloatToFraction(fl);
        return (*this)-f;
    }
    friend Fraction operator -(float fl,const Fraction &f){
        return (f-fl)*-1;
    }
    Fraction operator *(const Fraction &f) const {
        int n = numerator*f.numerator,d= denominator*f.denominator;
        return Fraction(n,d);
    }
    Fraction operator *(float fl) const {
        Fraction f = convertFloatToFraction(fl);
        return *this*f;
    }
    friend Fraction operator *(float fl,const Fraction &f){
        return (f*fl);
    }
    Fraction operator /(const Fraction &f) const {
        return (*this)*Fraction(f.denominator,f.numerator);
    }
    Fraction operator /(float fl) const {
        Fraction f = Fraction::convertFloatToFraction(fl);
        return (*this)/f;
    }
    friend Fraction operator /(float fl,const Fraction &f){
        return Fraction(f.denominator,f.numerator)*fl;
    }

    Fraction &operator =(const Fraction &f){
        if(this!=&f){
            numerator = f.numerator;
            denominator = f.denominator;
            reduce();
        }
        return *this;
    }
    Fraction &operator +=(const Fraction &f){
        int d = lcm(denominator,f.denominator);
        int n = (d/denominator)*numerator + (d/f.denominator)*f.numerator;
        numerator = n;
        denominator = d;
        reduce();
        return *this;
    }    
    Fraction &operator +=(float fl){
        Fraction f = convertFloatToFraction(fl)+*this;
        numerator = f.numerator;
        denominator = f.denominator;
        return *this;
    }
    Fraction &operator -=(const Fraction &f){
        int d = lcm(denominator,f.denominator);
        int n = (d/denominator)*numerator - (d/f.denominator)*f.numerator;
        numerator = n,denominator = d;
        reduce();
        return *this;
    }
    Fraction &operator -=(float fl){
        Fraction f1 = convertFloatToFraction(fl);
        Fraction f = (*this)-f1;
        numerator = f.numerator;
        denominator = f.denominator;
        return *this;
    }
    Fraction &operator *=(const Fraction &f){
        numerator = numerator*f.numerator,denominator = denominator*f.denominator;
        reduce();
        return *this;
    }
    Fraction &operator *=(float fl){
        Fraction f = convertFloatToFraction(fl);
        numerator*=f.numerator;
        denominator *=f.denominator;
        reduce();
        return *this;
    }

    Fraction &operator /=(const Fraction &f){
        if(f.numerator==0){
            cerr << "Division failed\n";
            exit(EXIT_FAILURE);
        }
        numerator = numerator*f.denominator;
        denominator = denominator*f.numerator;
        reduce();
        return *this;
    }
    Fraction &operator /=(float fl){
        if(fl==0){
            cerr << "Division failed\n";
            exit(EXIT_FAILURE);
        }
        Fraction f = convertFloatToFraction(fl);
        numerator *= f.denominator;
        denominator *= f.numerator;
        reduce();
        return *this;
    }
    static Fraction convertFloatToFraction(float fl, int precision = 6) {
        bool negative = (fl < 0);
        if (negative) fl = -fl;
        int intPart = static_cast<int>(fl);
        double fractionalPart = fl - intPart;
        int den = 1;
        for (int i = 0; i < precision; i++) {
            den *= 10;
        }
        int num = static_cast<int>(fractionalPart * den + 0.5); 
        Fraction result(intPart * den + num, den);
        if (negative) result.numerator = -result.numerator;
        
        return result;
    }
    friend istream &operator>>(istream &in, Fraction &f) {
        char slash;
        in >> f.numerator >> slash >> f.denominator;
        if (slash != '/' || f.denominator == 0) {
            cerr << "Invalid fraction format or zero denominator\n";
            exit(EXIT_FAILURE);
        }
        f.reduce();
        return in;
    }
    void reduce(){
        int g = __gcd(numerator,denominator);
        numerator/=g;
        denominator/=g;
    }
    friend ostream &operator <<(ostream &out,const Fraction &f){
        out << "(" << f.numerator << "/" << f.denominator << ")";
        return out; 
    }
    
};

class FractionVector{
    Fraction *fractions;
    int size;
    public:
    FractionVector(){
        fractions = NULL;
        size = 0;
    }
    FractionVector(int n){
        size = n;
        fractions = new Fraction[n];
    }
    FractionVector(const FractionVector &other){
        size = other.size;
        fractions = new Fraction[size];
        for (int i = 0; i < size; i++) {
            fractions[i] = other.fractions[i];
        }
    }
    Fraction &operator[](int i){
        if (i < 0 || i >= size) {
            cerr << "Error: Array index out of bounds\n";
            exit(EXIT_FAILURE);
        }
        return fractions[i];
    }
    FractionVector operator +(FractionVector &f){
        if(size!=f.size){
            FractionVector nl;
            cout << "Addition of vector failed. Size not equal" << "\n";
            return nl;
        }
        FractionVector curr(f.size);
        for(int i=0;i<size;i++){
            curr.fractions[i] = fractions[i]+f.fractions[i];    
        }
        return curr;
    }

    FractionVector operator -(FractionVector &f){
        if(size!=f.size){
            FractionVector nl;
            cout << "Subtraction of vector failed. Size not equal" << "\n";
            return nl;
        }
        FractionVector curr(f.size);
        for(int i=0;i<size;i++){
            curr.fractions[i] = fractions[i]-f.fractions[i];    
        }
        return curr;
    }
    FractionVector operator *(Fraction &f){
        FractionVector fv(size);
        for(int i=0;i<size;i++){
            fv.fractions[i] = fractions[i] *f;
        }
        return fv;
    }
    friend FractionVector operator *(Fraction &fr,FractionVector &fv){
        return fv*fr;
    }
    FractionVector operator /(Fraction &f){
        FractionVector fv(size);
        for(int i=0;i<size;i++){
            fv.fractions[i] = fractions[i]/f;
        }
        return fv;
    }
    Fraction operator *(FractionVector &fv){
        Fraction f;
        if(size!=fv.size){
            cout << "Dot product failed\n";
            return f;
        }
        for(int i=0;i<size;i++){
            f += fractions[i]*fv.fractions[i];
        }
        return f;
    }
    float value(){
        Fraction ans;
        for(int i=0;i<size;i++){
            ans += fractions[i]*fractions[i];
        }
        if (ans.get_denominator() == 0) {
            cerr << "Error: Division by zero in value calculation.\n";
            exit(EXIT_FAILURE);
        }
        return sqrt(ans.get_numerator() * 1.0 / ans.get_denominator());
    }

    FractionVector& operator=(const FractionVector& other) {
        if (this != &other) {
            delete[] fractions;
            size = other.size;
            fractions = new Fraction[size];
            for (int i = 0; i < size; ++i) {
                fractions[i] = other.fractions[i];
            }
        }
        return *this;
    }

    
    friend ostream &operator <<(ostream &out,FractionVector &f){
        for(int i=0;i<f.size;i++){
            out << f[i] << " ";
        }
        return out;
    }
    ~FractionVector(){
        if (fractions) delete[] fractions;
    }
};

class FractionMatrix{
    FractionVector *rows;
    FractionVector *columns;
    int rowCount,columnCount;
    public:
    FractionMatrix(int r,int c){
        this->rowCount = r;
        this->columnCount = c;
        rows = new FractionVector[rowCount];
        for(int i=0;i<rowCount;i++){
            rows[i] = FractionVector(c);
        }
        columns = new FractionVector[columnCount];
        for(int i=0;i<columnCount;i++){
            columns[i] = FractionVector(r);
        }
    }
    FractionMatrix(FractionMatrix &f){
        this->rowCount = f.rowCount;
        this->columnCount = f.columnCount;
        rows = new FractionVector[f.rowCount];
        columns = new FractionVector[f.columnCount];
        for(int i=0;i<this->rowCount;i++){
            for(int j=0;j<this->columnCount;j++){
                rows[i][j].set_numerator(f[i][j].get_numerator());
                rows[i][j].set_denominator(f[i][j].get_denominator());
            }
        }
        construct_columns();
    }
    FractionVector &operator[](int i){
        if (i < 0 || i >= rowCount) {
            cerr << "Error: Matrix row index out of bounds\n";
            exit(EXIT_FAILURE);
        }
        return rows[i];
    }
    void construct_columns(){
        for(int i=0;i<rowCount;i++){
            for(int j=0;j<columnCount;j++){
                columns[j][i] = rows[i][j];
            }
        }
    }
    FractionVector &getColumn(int i){
        if(i>=columnCount){
            cout << "Out of bound error\n";
            exit(EXIT_FAILURE);
        }
        return columns[i];
    }
    FractionMatrix operator +(FractionMatrix &a){
        if(rowCount!=a.rowCount or columnCount!=a.columnCount){
            cout << "Matrix addition failed" << endl;
            exit(EXIT_FAILURE);
        }
        FractionMatrix ans(rowCount,columnCount);
        for(int i=0;i<rowCount;i++){
            for(int j=0;j<columnCount;j++){
                ans[i][j] = rows[i][j] + a[i][j];
            }
        }
        ans.construct_columns();
        return ans;
    }
    FractionMatrix operator -(FractionMatrix &a){
        if(rowCount!=a.rowCount or columnCount!=a.columnCount){
            cout << "Matrix subtraction failed" << endl;
            exit(EXIT_FAILURE);
        }
        FractionMatrix ans(rowCount,columnCount);
        for(int i=0;i<rowCount;i++){
            for(int j=0;j<columnCount;j++){
                ans[i][j] = rows[i][j] - a[i][j];
            }
        }
        ans.construct_columns();
        return ans;
    }    
    FractionMatrix operator *(Fraction &f) {
        FractionMatrix ans(rowCount,columnCount);
        for(int i=0;i<rowCount;i++){
            for(int j=0;j<columnCount;j++){
                ans[i][j] = (*this)[i][j]*f;
            }
        }
        return ans;
    }
    FractionMatrix operator /(Fraction &f){
        FractionMatrix ans(rowCount,columnCount);
        for(int i=0;i<rowCount;i++){
            for(int j=0;j<columnCount;j++){
                ans[i][j] = (*this)[i][j]/f;
            }
        }
        return ans;
    }
    friend FractionMatrix operator *(Fraction &f,FractionMatrix &m){
        return m*f;
    }
    FractionMatrix operator *(FractionMatrix &A){
        if(columnCount!=A.rowCount){
            cout << "Matrix dimention mismatch. Multiplication failed\n";
            exit(EXIT_FAILURE);
        }
        int m = rowCount,n=columnCount,p=A.columnCount;
        FractionMatrix ans(m,p);
        for(int i=0;i<m;i++){
            for(int j=0;j<p;j++)
                ans[i][j] = (*this)[i]*A.getColumn(j);
        }
        return ans;
    }
    FractionMatrix operator %(FractionMatrix &a){
        if(rowCount!=a.rowCount or columnCount!=a.columnCount){
            cout << "Hadamard Multiplication failed" << endl;
            exit(EXIT_FAILURE);
        }
        FractionMatrix ans(rowCount,columnCount);
        for(int i=0;i<rowCount;i++){
            for(int j=0;j<columnCount;j++){
                ans[i][j] = rows[i][j]*a[i][j];
            }
        }
        ans.construct_columns();
        return ans;
    }
    FractionMatrix transpose(FractionMatrix &f){
        FractionMatrix ans(f.columnCount,f.rowCount);
        for(int i=0;i<f.rowCount;i++){
            for(int j=0;j<f.columnCount;j++){
                ans[j][i] = f[i][j];
            }
        }
        return ans;
    }

    friend ostream &operator <<(ostream &out,FractionMatrix &matrix);

    ~FractionMatrix(){
        delete[] rows;
        delete[] columns;
    }
};
ostream &operator <<(ostream &out,FractionMatrix &matrix){
    for(int i=0;i<matrix.rowCount;i++){
        out << matrix[i] << "\n";
    }
    return out;
}

int main() {
    // FRACTION Class
    Fraction f1(1, 2), f2(3, 4);
    cout << "f1: " << f1 << ", f2: " << f2 << "\n";
    cout << "f1 + f2 = " << f1 + f2 << "\n";
    cout << "f1 - f2 = " << f1 - f2 << "\n";
    cout << "f1 * f2 = " << f1 * f2 << "\n";
    cout << "f1 / f2 = " << f1 / f2 << "\n";

    float floatVal = 1.25;
    cout << "Float : " << floatVal << "\n";
    cout << "f1 + float = " << f1 + floatVal << "\n";
    cout << "float + f1 = " << floatVal + f1 << "\n";
    cout << "f1 - float = " << f1 - floatVal << "\n";
    cout << "float - f1 = " << floatVal - f1 << "\n";
    cout << "f1 * float = " << f1 * floatVal << "\n";
    cout << "float * f1 = " << floatVal * f1 << "\n";
    cout << "f1 / float = " << f1 / floatVal << "\n";
    cout << "float / f1 = " << floatVal / f1 << "\n";

    // FRACTION VECTOR Class
    FractionVector v1(3), v2(3);
    v1[0] = Fraction(1, 2); v1[1] = Fraction(2, 3); v1[2] = Fraction(3, 4);
    v2[0] = Fraction(1, 3); v2[1] = Fraction(1, 4); v2[2] = Fraction(1, 2);

    cout << "Vector v1: " << v1 << "\n";
    cout << "Vector v2: " << v2 << "\n";
    FractionVector v3 = v1+v2;
    cout << "v1 + v2 = " << v3 << "\n";
    v3 = v1-v2;
    cout << "v1 - v2 = " << v3 << "\n";
    Fraction scalar(2, 5);
    cout << "Scalar: " << scalar << "\n";
    v3 = v1*scalar;
    cout << "v1 * scalar = " << v3 << "\n";
    v3 = scalar * v1;
    cout << "scalar * v1 = " << v3 << "\n";
    v3 = v1/scalar;
    cout << "v1 / scalar = " << v3 << "\n";
    cout << "Dot product v1 * v2 = " << (v1 * v2) << "\n";
    cout << "Magnitude of v1 = " << v1.value() << "\n";

    // FRACTION MATRIX Class
    FractionMatrix mat1(2,2),mat2(2,2);
    mat1[0][0] = Fraction(1, 2); mat1[0][1] = Fraction(1, 3);
    mat1[1][0] = Fraction(2, 5); mat1[1][1] = Fraction(3, 4);

    mat2[0][0] = Fraction(1, 3); mat2[0][1] = Fraction(1, 6);
    mat2[1][0] = Fraction(3, 5); mat2[1][1] = Fraction(2, 7);

    mat1.construct_columns();
    mat2.construct_columns();
    
    // int r1,c1,r2,c2;
    // cout << "Give dimention of matrix 1: ";
    // cin >> r1 >> c1;
    // cout << "Give matrix 1(a/b form) : ";
    // for(int i=0;i<r1;i++){
    //     for(int j=0;j<c1;j++){
    //         cin >> mat1[i][j];
    //     }
    // }
    // cout << "Give dimention of matrix 1: ";
    // cin >> r2 >> c2;
    // cout << "Give matrix 1(a/b form) : ";
    // for(int i=0;i<r2;i++){
    //     for(int j=0;j<c2;j++){
    //         cin >> mat2[i][j];
    //     }
    // }

    cout << "Matrix mat1:\n" << mat1 << "\n";
    cout << "Matrix mat2:\n" << mat2 << "\n";
    FractionMatrix mat3 = mat1+mat2;
    cout << "mat1 + mat2:\n" << mat3 << "\n";
    mat3 = mat1 - mat2;
    cout << "mat1 - mat2:\n" << mat3 << "\n";
    mat3 = mat1*scalar;
    cout << "mat1 * scalar:\n" << mat3 << "\n";
    mat3 = scalar*mat1;
    cout << "scalar * mat1:\n" << mat3 << "\n";
    mat3 = mat1/scalar;
    cout << "mat1 / scalar:\n" << mat3 << "\n";
    mat3 = mat1%mat2;
    cout << "mat1 % mat2 (Hadamard):\n" << mat3 << "\n";

    FractionMatrix mat4(2, 3), mat5(3, 2);
    mat4[0][0] = Fraction(1); mat4[0][1] = Fraction(2); mat4[0][2] = Fraction(3);
    mat4[1][0] = Fraction(4); mat4[1][1] = Fraction(5); mat4[1][2] = Fraction(6);
    mat5[0][0] = Fraction(7); mat5[0][1] = Fraction(8);
    mat5[1][0] = Fraction(9); mat5[1][1] = Fraction(10);
    mat5[2][0] = Fraction(11); mat5[2][1] = Fraction(12);

    mat4.construct_columns();
    mat5.construct_columns();
    FractionMatrix mat6 = mat4*mat5;
    cout << "mat4 * mat5:\n" << mat6 << "\n";

    FractionMatrix transposed = mat1.transpose(mat1);
    cout << "Transpose of mat1:\n" << transposed << "\n";

    return 0;
}

