package com.su25.swp391.utils;

/**
 * Utility class for text processing
 */
public class TextUtils {

    /**
     * Remove HTML tags from text
     * 
     * @param html HTML string
     * @return Plain text
     */
    public static String stripHtml(String html) {
        if (html == null || html.isEmpty()) {
            return "";
        }
        return html.replaceAll("<[^>]*>", "").trim();
    }

    /**
     * Truncate text to specified length with ellipsis
     * 
     * @param text      Text to truncate
     * @param maxLength Maximum length
     * @return Truncated text with ellipsis if needed
     */
    public static String truncate(String text, int maxLength) {
        if (text == null || text.length() <= maxLength) {
            return text;
        }
        return text.substring(0, maxLength) + "...";
    }

    /**
     * Strip HTML and truncate text
     * 
     * @param html      HTML string
     * @param maxLength Maximum length
     * @return Clean, truncated text
     */
    public static String getPreviewText(String html, int maxLength) {
        String cleanText = stripHtml(html);
        return truncate(cleanText, maxLength);
    }
}
