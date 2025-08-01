#include <bits/stdc++.h>
using namespace std;

class Book
{
    string title;
    string author;
    int ratingCount;
    double avgRating;

public:
    Book()
    {
        title = "Default";
        author = "Anonymous";
        ratingCount = 0;
        avgRating = 0;
    }
    Book(string title, string author)
    {
        this->title = title;
        this->author = author;
        ratingCount = 0;
        avgRating = 0;
    }
    string get_title()
    {
        return title;
    }
    string get_author()
    {
        return author;
    }
    void addRating(int rating)
    {
        //cout << ratingCount << " ";
        ratingCount++;
        avgRating = avgRating * (ratingCount-1) + rating;
        avgRating = avgRating / ratingCount;
        //cout << avgRating << "\n";
    }
    double getAverageRating()
    {
        return avgRating;
    }
    void display()
    {
        cout << "Book: " << title << " by " << author << ", Average Rating: " << avgRating << "\n";
    }
};

class Library
{
    int maxBooks;
    Book *books;
    int totalBooks;

public:
    Library(int maxBooks)
    {
        this->maxBooks = maxBooks;
        books = new Book[maxBooks];
        totalBooks = 0;
    }
    Library(int maxBooks, Book *books, int totalBooks)
    {
        this->maxBooks = maxBooks;
        this->books = new Book[maxBooks];
        for (int i = 0; i < totalBooks; i++)
        {
            this->books[i] = books[i];
        }
        this->totalBooks = totalBooks;
    }
    Library(const Library &ob)
    {
        maxBooks = ob.maxBooks;
        books = new Book[ob.maxBooks];
        for (int i = 0; i < ob.totalBooks; i++)
        {
            books[i] = ob.books[i];
        }
        totalBooks = ob.totalBooks;
    }
    void addBook(Book b)
    {
        if (totalBooks == maxBooks)
        {
            cout << "Library if full. Cannot add more books\n";
            return;
        }
        books[totalBooks++] = b;
        cout << "Book added: " << b.get_title() << " by " << b.get_author() << "\n";
    }
    void removeBook(Book b)
    {
        for (int i = 0; i < totalBooks; i++)
        {
            if (b.get_title() == books[i].get_title())
            {
                totalBooks--;
                for (int j = i; j < totalBooks; j++)
                {
                    books[j] = books[j + 1];
                }
                cout << "Book removed: " << b.get_title() << " by " << b.get_author() << "\n";
                return;
            }
        }
    }
    void rateBook(string title, string author, int rating)
    {
        for (int i = 0; i < totalBooks; i++)
        {
            if (books[i].get_author() == author && books[i].get_title() == title)
            {
                //cout << ">>>>>>>>>>>>>>>>>>>>>>> " << rating << " " << books[i].get_title() << " " << books[i].getAverageRating() << "\n";
                books[i].addRating(rating);
                return;
            }
        }
    }
    void showBooks()
    {
        for (int i = 0; i < totalBooks; i++)
        {
            books[i].display();
        }
    }
    Book bestRatedBook()
    {
        int max_rating_index = -1;
        double max_rating = INT_MIN;
        for (int i = 0; i < totalBooks; i++)
        {
            if (books[i].getAverageRating() > max_rating)
            {
                max_rating = books[i].getAverageRating();
                max_rating_index = i;
            }
        }
        return books[max_rating_index];
    }
    ~Library()
    {
        delete[] books;
    }
};

int main()
{
    Library lib1(5);
    Book b1("The Great Gatsby", "F. Scott Fitzgerald");
    Book b2("To Kill a Mockingbird", "Harper Lee");
    Book b3("1984", "George Orwell");
    Book b4("War and Peace", "Leo Tolstoy");
    Book b5("The Kite Runner", "Khaled Hosseini");
    Book b6("A Tale of Two Cities", "Charles Dickens");
    lib1.addBook(b1);
    lib1.addBook(b2);
    lib1.addBook(b3);
    lib1.addBook(b4);
    lib1.addBook(b5);
    lib1.addBook(b6);
    lib1.rateBook("The Great Gatsby", "F. Scott Fitzgerald", 5);
    lib1.rateBook("The Great Gatsby", "F. Scott Fitzgerald", 4);
    lib1.rateBook("To Kill a Mockingbird", "Harper Lee", 5);
    lib1.rateBook("1984", "George Orwell", 4);
    lib1.rateBook("1984", "George Orwell", 5);
    lib1.rateBook("1984", "George Orwell", 5);
    lib1.rateBook("War and Peace", "Leo Tolstoy", 3);
    lib1.rateBook("War and Peace", "Leo Tolstoy", 4);
    lib1.rateBook("The Kite Runner", "Khaled Hosseini", 5);
    lib1.rateBook("The Kite Runner", "Khaled Hosseini", 4);
    lib1.rateBook("The Kite Runner", "Khaled Hosseini", 5);
    cout << "\nLibrary 1 Books and Ratings:\n";
    lib1.showBooks();
    cout << "\nBest Rated Book in Library 1:\n";
    lib1.bestRatedBook().display();
    cout << endl;
    Library lib2(lib1);
    lib2.removeBook(b2);
    lib2.addBook(b6);
    cout << "\nLibrary 2 Books and Ratings:\n";
    lib2.showBooks();
    cout << "\nBest Rated Book in Library 1:\n";
    lib1.bestRatedBook().display();
    cout << "\nBest Rated Book in Library 2:\n";
    lib2.bestRatedBook().display();
    return 0;
}
