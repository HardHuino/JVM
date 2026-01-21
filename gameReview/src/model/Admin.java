package model;

import java.lang.reflect.Member;

public class Admin extends Tester {
    // Constructeur
    public Administrator(String pseudo);

    // Méthodes de modération
    public void deleteReview(Review review);
    public void blockMember(Member member);
    public void unblockMember(Member member);
    public void promoteMember(Member member, UserType newType);

    // Méthodes de consultation avancée
    public Map<Member, Integer> getMembersWithReviewRatings();
    public List<Review> getReportedReviews();
}
