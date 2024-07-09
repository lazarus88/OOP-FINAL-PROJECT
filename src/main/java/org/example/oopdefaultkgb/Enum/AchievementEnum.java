package org.example.oopdefaultkgb.Enum;

public enum AchievementEnum {
    Amateur_Author,
    Prolific_Author,
    Prodigious_Author,
    Quiz_Machine,
    I_Am_The_Greatest,
    Practice_Makes_Perfect;

    public static String intToString(int index) {
        for (AchievementEnum achievementEnum : AchievementEnum.values())
            if (index == achievementEnum.ordinal())
                return achievementEnum.name();
        return "";
    }
}
