package br.com.erudio.mapper.custom;

import org.springframework.stereotype.Service;

import br.com.erudio.data.vo.v1.BookVO;
import br.com.erudio.model.Book;

@Service
public class BookMapper {
	public BookVO convertEntityToVo(Book entity) {
		BookVO vo = new BookVO();
		vo.setKey(entity.getId());
		vo.setAuthor(entity.getAuthor());
		vo.setPrice(entity.getPrice());
		vo.setLaunchDate(entity.getLaunchDate());
		return vo;
	}
	
	public Book convertVoToEntity(BookVO vo) {
		Book entity = new Book();
		entity.setId(vo.getKey());
		entity.setAuthor(vo.getAuthor());
		entity.setPrice(vo.getPrice());
		entity.setLaunchDate(vo.getLaunchDate());
		return entity;
	}
}
