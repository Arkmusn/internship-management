package io.arkmusn.internship.util;

/**
 * @author Arkmusn
 *         create 2017/11/19
 */

public abstract class StringUtils extends org.springframework.util.StringUtils {
    /**
     * 字符串关键词化
     *
     * @param string 字符串
     * @param prefix 是否添加前缀
     * @param suffix 是否添加后缀
     * @return 关键词化后的字符串
     */
    public static String keyword(String string, boolean prefix, boolean suffix) {
        StringBuilder builder = new StringBuilder(string);
        if (prefix)
            builder.insert(0, "%");
        if (suffix)
            builder.append("%");
        return builder.toString();
    }

    public static String keyword(String string) {
        return StringUtils.keyword(string, true, true);
    }

    public static String keywordWithSuffix(String string) {
        return StringUtils.keyword(string, false, true);
    }
}
