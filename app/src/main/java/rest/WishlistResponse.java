package rest;

/**
 * Created by morion on 2017-02-09.
 */

public class WishlistResponse {

    private Article[] wishlist_article;
    public String error;
    public String error_msg;

    public Article[] getWishlist_article() {
        return wishlist_article;
    }

    public void setWishlist_article(Article[] wishlist_article) {
        this.wishlist_article = wishlist_article;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getError_msg() {
        return error_msg;
    }

    public void setError_msg(String error_msg) {
        this.error_msg = error_msg;
    }
}
