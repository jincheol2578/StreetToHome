package egovframework.sth.domain.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import egovframework.sth.domain.board.domain.BoardDTO;
import egovframework.sth.domain.board.mapper.BoardMapper;
import egovframework.sth.global.common.FileUtils;
@Service
public class BoardService {
	
	@Autowired
	private BoardMapper mapper;
	@Autowired
	FileUtils futils;
	
	public BoardDTO selBoardView(BoardDTO param) {
		return mapper.selBoardView(param);
	}
	
	
	public List<BoardDTO> boardList(BoardDTO dto){
		return mapper.boardList(dto);
	}
	
	public int insBoard(BoardDTO dto) {
		return mapper.insBoard(dto);
	}
	public int patimgUpload(MultipartFile[] imgs, int b_no) {
		if(imgs.length>5 || imgs.length ==0) {
			return 0;
		}
		String folder= "/img/board/b_"+b_no;
		try {
			for(int i=0; i<imgs.length; i++) {
				MultipartFile file = imgs[i];
				String fileNm = futils.saveFile(file, folder);
				if(file == null) {
					return 0;
				}
				if(i==0) {
					BoardDTO dto = new BoardDTO();
					dto.setB_img(fileNm);
					dto.setB_no(b_no);
					mapper.updpatImg(dto);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		
		return 1;
	}
	
}
