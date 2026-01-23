package models;

import java.util.*;
import services.manageUsers;

public class Admin extends Tester {

    // Constructeur
    public Admin(String pseudo) {
        super(pseudo);
        this.type = UserType.ADMIN;  // Mise à jour du type
    }

    // Méthodes de modération
    public void deleteReview(Review review) {
        if (review != null) {
            review.delete();
        }
    }
    public void blockMember(Member member) {
        if (member != null && !(member instanceof Admin)) {}
        member.block();
    }
    public void unblockMember(Member member) {
        if (member != null) {
            member.unblock();
        }
    }
    public void promoteMember(Member member, UserType newType) {
        if (member != null && !(member instanceof Admin)) {
            member.
        }
    }

    // Méthodes de consultation avancée
    public Map<Member, Integer> getMembersWithReviewRatings();
    public List<Review> getReportedReviews();
}
