package services;

import models.*;
import exceptions.*;
import java.util.*;

public class manageUsers {
    // Attributs
    private Map<Integer, Member> membersById;      // ID -> Member
    private Map<String, Member> membersByPseudo;   // Pseudo -> Member
    private User currentUser;

    // Constructeur
    public manageUsers() {
        this.membersById = new HashMap<>();
        this.membersByPseudo = new HashMap<>();
        this.currentUser = new Guest();  // Par défaut : invité
    }

    // ========== Méthodes de connexion ==========

    public void login(String pseudo) throws UserNotFoundException, BlockedUserException {
        if (!pseudoExists(pseudo)) {
            throw new UserNotFoundException(pseudo);
        }

        Member member = membersByPseudo.get(pseudo);

        if (member.isBlocked()) {
            throw new BlockedUserException(pseudo);
        }
        this.currentUser = member;
    }

    public void logout() {
        this.currentUser = new Guest();
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public boolean isLoggedIn() {
        return currentUser instanceof Member;
    }

    public boolean isGuest() {
        return currentUser instanceof Guest;
    }

    // ========== Méthodes d'inscription ==========

    public Player registerPlayer(String pseudo) throws DuplicatePseudoException {
        if (pseudoExists(pseudo)) {
            throw new DuplicatePseudoException(pseudo);
        }

        Player player = new Player(pseudo);
        membersById.put(player.getId(), player);
        membersByPseudo.put(pseudo, player);

        return player;
    }

    public void unregisterPlayer(String pseudo) throws UserNotFoundException {
        if (!pseudoExists(pseudo)) {
            throw new UserNotFoundException(pseudo);
        }

        Member member = membersByPseudo.get(pseudo);

        // Si c'est l'utilisateur connecté, le déconnecter
        if (currentUser.getId() == member.getId()) {
            logout();
        }

        membersById.remove(member.getId());
        membersByPseudo.remove(pseudo);
    }

    // ========== Méthodes de promotion ==========

    public Tester promoteToTester(String pseudo) throws UserNotFoundException, InvalidPromotionException {
        if (!pseudoExists(pseudo)) {
            throw new UserNotFoundException(pseudo);
        }

        Member oldMember = membersByPseudo.get(pseudo);

        if (!(oldMember instanceof Player)) {
            throw new InvalidPromotionException("Seul un Player peut être promu Tester");
        }

        if (oldMember instanceof Tester) {
            throw new InvalidPromotionException(pseudo + " est déjà un Tester ou Admin");
        }

        // Créer un nouveau Tester avec les données du Player
        Tester tester = new Tester(pseudo);
        transferMemberData((Player) oldMember, tester);

        // Remplacer dans les maps
        membersById.remove(oldMember.getId());
        membersById.put(tester.getId(), tester);
        membersByPseudo.put(pseudo, tester);

        // Si c'était l'utilisateur connecté, mettre à jour
        if (currentUser.getId() == oldMember.getId()) {
            currentUser = tester;
        }

        return tester;
    }

    public Admin promoteToAdministrator(String pseudo) throws UserNotFoundException, InvalidPromotionException {
        if (!pseudoExists(pseudo)) {
            throw new UserNotFoundException(pseudo);
        }

        Member oldMember = membersByPseudo.get(pseudo);

        if (!(oldMember instanceof Tester)) {
            throw new InvalidPromotionException("Seul un Tester peut être promu Admin");
        }

        if (oldMember instanceof Admin) {
            throw new InvalidPromotionException(pseudo + " est déjà un Admin");
        }

        // Créer un nouveau Admin avec les données du Tester
        Admin admin = new Admin(pseudo);
        transferMemberData((Tester) oldMember, admin);

        // Remplacer dans les maps
        membersById.remove(oldMember.getId());
        membersById.put(admin.getId(), admin);
        membersByPseudo.put(pseudo, admin);

        // Si c'était l'utilisateur connecté, mettre à jour
        if (currentUser.getId() == oldMember.getId()) {
            currentUser = admin;
        }

        return admin;
    }

    // Transfert des données lors d'une promotion
    private void transferMemberData(Player source, Player target) {
        // Transférer les jeux et temps de jeu
        for (Map.Entry<Game, Integer> entry : source.getGamePlaytime().entrySet()) {
            target.addGame(entry.getKey());
            target.addPlayTime(entry.getKey(), entry.getValue());
        }

        // Transférer les jetons
        target.addTokens(source.getAvailableTokens());

        // Transférer les jetons placés
        for (Map.Entry<Game, Integer> entry : source.getPlacedTokensMap().entrySet()) {
            try {
                target.placeTokens(entry.getKey(), entry.getValue());
            } catch (InsufficientTokensException e) {
                // Ne devrait pas arriver car on a transféré les jetons
            }
        }
    }

    private void transferMemberData(Tester source, Tester target) {
        // D'abord transférer les données de Player
        transferMemberData((Player) source, (Player) target);

        // Les tests et reviews signalées sont gérés par les services
        // et seront automatiquement associés au nouveau compte
    }

    // ========== Méthodes de blocage ==========

    public void blockMember(String pseudo) throws UserNotFoundException, UnauthorizedActionException {
        if (!(currentUser instanceof Admin)) {
            throw new UnauthorizedActionException("Seul un Admin peut bloquer des membres");
        }

        if (!pseudoExists(pseudo)) {
            throw new UserNotFoundException(pseudo);
        }

        Member member = membersByPseudo.get(pseudo);

        if (member instanceof Admin) {
            throw new UnauthorizedActionException("Impossible de bloquer un Admin");
        }

        member.block();
    }

    public void unblockMember(String pseudo) throws UserNotFoundException, UnauthorizedActionException {
        if (!(currentUser instanceof Admin)) {
            throw new UnauthorizedActionException("Seul un Admin peut débloquer des membres");
        }

        if (!pseudoExists(pseudo)) {
            throw new UserNotFoundException(pseudo);
        }

        Member member = membersByPseudo.get(pseudo);
        member.unblock();
    }

    // ========== Méthodes de consultation ==========

    public Member getMember(String pseudo) throws UserNotFoundException {
        if (!pseudoExists(pseudo)) {
            throw new UserNotFoundException(pseudo);
        }
        return membersByPseudo.get(pseudo);
    }

    public Member getMemberById(int id) throws UserNotFoundException {
        Member member = membersById.get(id);
        if (member == null) {
            throw new UserNotFoundException("ID: " + id);
        }
        return member;
    }

    public boolean pseudoExists(String pseudo) {
        return membersByPseudo.containsKey(pseudo);
    }

    public List<Member> getAllMembers() {
        return new ArrayList<>(membersById.values());
    }

    public void displayMemberInfo(String pseudo, boolean adminMode) throws UserNotFoundException {
        Member member = getMember(pseudo);

        System.out.println("=== Informations sur " + pseudo + " ===");
        System.out.println("Type de profil: " + member.getType());
        System.out.println("Jetons disponibles: " + member.getAvailableTokens());
        System.out.println("Nombre de jeux possédés: " + member.getGamePlaytime().size());
        System.out.println("Temps de jeu total: " + member.getTotalPlayTime() + "h");

        if (member instanceof Player) {
            Player player = (Player) member;
            System.out.println("Nombre d'évaluations: " + player.getReviewCount());

            if (adminMode) {
                System.out.println("\n--- Évaluations reçues ---");
                System.out.println("Positives (👍): " + player.getPositiveRatingsCount());
                System.out.println("Neutres: " + player.getNeutralRatingsCount());
                System.out.println("Négatives (👎): " + player.getNegativeRatingsCount());
            }
        }

        if (member instanceof Tester) {
            Tester tester = (Tester) member;
            System.out.println("Nombre de tests: " + tester.getTestCount());
        }

        if (adminMode || member == currentUser) {
            System.out.println("\n--- Jeux possédés (par temps de jeu décroissant) ---");
            Map<Game, Integer> sortedGames = member.getOwnedGamesSortedByPlayTime();
            int count = 0;
            for (Map.Entry<Game, Integer> entry : sortedGames.entrySet()) {
                System.out.println((++count) + ". " + entry.getKey().getName() +
                        " - " + entry.getValue() + "h");
                if (!adminMode && count >= 10) break;  // Limiter l'affichage
            }
        }
    }
}