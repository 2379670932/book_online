package top.soft.bookonline.dao;

import top.soft.bookonline.entity.Book;

import java.util.List;

public interface BookDao {

    /**
     * 查询所有图书
     *
     * @return List<Book>
     */
    List<Book> selectAll();


    /**
     * 根据id查询图书
     *
     * @param id
     * @return Book
     */
    Book getBookById(int id);

}

