package com.mayi.data.entity;

/**
 * @author: liwenfei.
 * data: 2018/7/14 13:52.
 */
public class ArticleEntity {
    private Data data;

    public Data getData() {
        return data;
    }

    public static class Data{
        private String author;
        private String title;
        private String digest;
        private String content;
        private String wc;
        private Date date;

        public String getAuthor() {
            return author;
        }

        public String getTitle() {
            return title;
        }

        public String getDigest() {
            return digest;
        }

        public String getContent() {
            return content;
        }

        public String getWc() {
            return wc;
        }

        public Date getDate() {
            return date;
        }
    }

    private static class Date {
        private String curr;
        private String prev;
        private String next;

        public String getCurr() {
            return curr;
        }

        public String getPrev() {
            return prev;
        }

        public String getNext() {
            return next;
        }
    }
}
