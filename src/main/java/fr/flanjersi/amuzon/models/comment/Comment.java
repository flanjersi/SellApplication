package fr.flanjersi.amuzon.models.comment;

/**
 * Commentary of a product
 */
public class Comment {

    /**
     * Id of the comment
     */
    private int id;

    /**
     * The comment string
     */
    private String comment;

    /**
     * Date when comment was posted
     */
    private String date;

    /**
     * User rating of product
     */
    private int rating;

    /**
     * number of vote who considere comment useful
     */
    private int usefulVote;

    /**
     * number of vote who considere comment not useful
     */
    private int notUsefulVote;

    public Comment() { }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getUsefulVote() {
        return usefulVote;
    }

    public void setUsefulVote(int usefulVote) {
        this.usefulVote = usefulVote;
    }

    public int getNotUsefulVote() {
        return notUsefulVote;
    }

    public void setNotUsefulVote(int notUsefulVote) {
        this.notUsefulVote = notUsefulVote;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
