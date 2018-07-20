package practise.mayi.com.domain.entity;

import java.io.Serializable;

/**
 * @author: liwenfei.
 * data: 2018/7/14 13:58.
 */
public class Article implements Serializable{
    private String author;
    private String title;
    private String digest;
    private String content;
    private String wc;
    private Date   date;

    public Article(String author,String title,String digest,String content,String wc,Date date){
        this.author = author;
        this.title = title;
        this.digest = digest;
        this.content = content;
        this.wc = wc;
        this.date = date;
    }

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

    public static class Date implements Serializable{
        private String curr;
        private String prev;
        private String next;

        public Date(String curr,String prev,String next){
            this.curr = curr;
            this.prev = prev;
            this.next = next;
        }

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
