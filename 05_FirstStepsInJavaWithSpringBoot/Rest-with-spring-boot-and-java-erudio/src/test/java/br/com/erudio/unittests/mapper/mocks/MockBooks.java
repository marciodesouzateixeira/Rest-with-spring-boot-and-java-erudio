package br.com.erudio.unittests.mapper.mocks;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.erudio.data.vo.v1.BookVO;
import br.com.erudio.model.Book;

public class MockBooks {


    public Book mockEntity() {
        return mockEntity(0);
    }
    
    public BookVO mockVO() {
        return mockVO(0);
    }
    
    public List<Book> mockEntityList() {
        List<Book> books = new ArrayList<Book>();
        for (int i = 0; i < 14; i++) {
        	books.add(mockEntity(i));
        }
        return books;
    }

    public List<BookVO> mockVOList() {
        List<BookVO> books = new ArrayList<>();
        for (int i = 0; i < 14; i++) {
            books.add(mockVO(i));
        }
        return books;
    }
    
    public Book mockEntity(Integer number) {
    	Book book = new Book();
//        Instant start = Instant.ofEpochMilli(0); // 1970-01-01T00:00:00Z
//        Instant end = Instant.now(); // Instante atual
//        long randomEpochMilli = ThreadLocalRandom.current().nextLong(start.toEpochMilli(), end.toEpochMilli());
    	
    	book.setId(number.longValue());
    	book.setAuthor("Some Author " + number);
//    	book.setLaunchDate(new Date(randomEpochMilli));
    	book.setLaunchDate(new Date());
    	book.setPrice(10 * Double.valueOf(number));
    	book.setTitle("Some Title " + number);
        return book;
    }

    public BookVO mockVO(Integer number) {
    	BookVO book = new BookVO();
        
        book.setKey(number.longValue());
        book.setAuthor("Some Author " + number);
    	book.setLaunchDate(new Date());
    	book.setPrice(10 * Double.valueOf(number));
    	book.setTitle("Some Title " + number);
        return book;
    }

}
