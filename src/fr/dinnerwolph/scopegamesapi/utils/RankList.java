package fr.dinnerwolph.scopegamesapi.utils;

public enum RankList {
    ADMINISTRATEUR(13),
    RESPDEV(12),
    DEV(11),
    RESPMODO(10),
    MODO(9),
    BUILDER(8),
    HELPER(7),
    YOUTUBER(6),
    SCOPERS(5),
    HERO(4),
    VIPPLUS(3),
    VIP(2),
    MEMBER(1);
    
    private int rankId;

    private RankList(Integer value) {
        this.rankId = value;
    }

    public Integer getRankId() {
        return this.rankId;
    }
}