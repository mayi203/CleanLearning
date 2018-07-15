package practise.mayi.com.domain.repository;

import io.reactivex.Observable;
import practise.mayi.com.domain.entity.Article;

/**
 * @author: liwenfei.
 * data: 2018/7/14 14:02.
 */
public interface IGetArticleRepository {
    Observable<Article> getArticle();
    Observable<Article> getArticleForDate(String date);
}
