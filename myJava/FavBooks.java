public enum FavBooks {

    // OBJECTS CAN HAVE VALUES ASSOCIATED WITH THEN
    DiaryOfAWimpyKid(9, 2007 ),
    Fablehaven(10, 2006),
    TheMerlinSaga(11, 1996),
    PercyJackson(8, 2005),
    TimmyFailure(7, 2013);


    // DECLARE OBJECT VARIABLE AS FINAL
    final int ranking;
    final int datePublished;

    // Method to call variables 
    FavBooks(int ranking, int datePublished) {
        this.ranking = ranking;
        this.datePublished = datePublished;
        
    }
}

/* 
    //! Print out Book Details:
    System.out.println(
        "Name of Book: " + FavBooks.DiaryOfAWimpyKid
        + "\nFirst Published on: " + FavBooks.DiaryOfAWimpyKid.datePublished
        + "\nRanking: " + FavBooks.DiaryOfAWimpyKid.ranking
    );
*/