package ru.bse71.learnup.spring.rest.util;

/**
 * Created by bse71
 * Date: 02.09.2021
 * Time: 2:43
 */

public class PostUtils {

    private static final String DEFAULT_TEXT = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.";
    private static final String DEFAULT_COMMENT = "Хорошая статья. Даже конкурсы нормальные";

    public static String generateText() {
        return DEFAULT_TEXT;
    }

    public static String generateCommentText() {
        return DEFAULT_COMMENT;
    }
}
