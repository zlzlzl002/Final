package com.gura.step04.file.service;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.gura.step04.file.dao.FileDao;
import com.gura.step04.file.dto.FileDto;

@Service
public class FileServiceImpl implements FileService{
	
	//의존 객체
	@Autowired
	private FileDao fileDao;
	
	//한 페이지에 나타낼 로우의 갯수
	private static final int PAGE_ROW_COUNT=3;
	//하단 디스플레이 페이지 갯수
	private static final int PAGE_DISPLAY_COUNT=5;
	
	@Override
	public void insert(HttpServletRequest request, FileDto dto) {
		//파일을 저장할 폴더의 절대 경로를 얻어온다.
		String realPath=request.getSession()
				.getServletContext().getRealPath("/upload");
		//콘솔에 경로 출력해보기
		System.out.println(realPath);
		
		//MultipartFile 객체의 참조값 얻어오기
		//FileDto 에 담긴 MultipartFile 객체의 참조값을 얻어온다.
		MultipartFile mFile=dto.getFile();
		//원본 파일명
		String orgFileName=mFile.getOriginalFilename();
		//파일 사이즈
		long fileSize=mFile.getSize();
		//저장할 파일의 상세 경로
		String filePath=realPath+File.separator;
		//디렉토리를 만들 파일 객체 생성
		File file=new File(filePath);
		if(!file.exists()){//디렉토리가 존재하지 않는다면
			file.mkdir();//디렉토리를 만든다.
		}
		//파일 시스템에 저장할 파일명을 만든다. (겹치치 않게)
		String saveFileName=System.currentTimeMillis()+orgFileName;
		try{
			//upload 폴더에 파일을 저장한다.
			mFile.transferTo(new File(filePath+saveFileName));
		}catch(Exception e){
			e.printStackTrace();
		}
		//FileDto 객체에 추가 정보를 담는다.
		dto.setOrgFileName(orgFileName);
		dto.setSaveFileName(saveFileName);
		dto.setFileSize(fileSize);
		
		//세션에서 작성자 정보를 얻어온다.
		String id=(String)request.getSession().getAttribute("id");
		//FileDto 객체에 작성자 정보를 담는다.
		dto.setWriter(id);
		
		//FileDao 객체를 이용해서 DB 에 저장하기
		fileDao.insert(dto);		
	}

	@Override
	public ModelAndView list(HttpServletRequest request) {
		//검색과 관련된 파라미터를 읽어와 본다.
		String keyword=request.getParameter("keyword");
		String condition=request.getParameter("condition");
		
		//ModelAndView 객체 생성 (request 에 담을 내용을 여기에 담는다.)
		ModelAndView mView=new ModelAndView();
		
		//FileDto 객체를 생성해서
		FileDto dto=new FileDto();
		if(keyword != null){ //검색어가 전달된 경우
			if(condition.equals("titlecontent")){ //제목+파일명 검색
				dto.setTitle(keyword);
				dto.setOrgFileName(keyword);
			}else if(condition.equals("title")){//제목 검색
				dto.setTitle(keyword);
			}else if(condition.equals("writer")){//작성자 검색
				dto.setWriter(keyword);
			}
			// list.jsp 뷰페이지에서 필요한 내용을 mView 에 담는다.
			mView.addObject("condition", condition);
			mView.addObject("keyword", keyword);
		}
		
		
		//보여줄 페이지의 번호
		int pageNum=1;
		//보여줄 페이지의 번호가 파라미터로 전달되는지 읽어온다.
		String strPageNum=request.getParameter("pageNum");
		if(strPageNum != null){//페이지 번호가 파라미터로 넘어온다면
			//페이지 번호를 설정한다.
			pageNum=Integer.parseInt(strPageNum);
		}
		//보여줄 페이지 데이터의 시작 ResultSet row 번호
		int startRowNum=1+(pageNum-1)*PAGE_ROW_COUNT;
		//보여줄 페이지 데이터의 끝 ResultSet row 번호
		int endRowNum=pageNum*PAGE_ROW_COUNT;
		//전체 row 의 갯수를 DB 에서 얻어온다.
		int totalRow = fileDao.getCount(dto);
		//전체 페이지의 갯수 구하기
		int totalPageCount=
				(int)Math.ceil(totalRow/(double)PAGE_ROW_COUNT);
		//시작 페이지 번호
		int startPageNum=
			1+((pageNum-1)/PAGE_DISPLAY_COUNT)*PAGE_DISPLAY_COUNT;
		//끝 페이지 번호
		int endPageNum=startPageNum+PAGE_DISPLAY_COUNT-1;
		//끝 페이지 번호가 잘못된 값이라면 
		if(totalPageCount < endPageNum){
			endPageNum=totalPageCount; //보정해준다. 
		}
		
		dto.setStartRowNum(startRowNum);
		dto.setEndRowNum(endRowNum);
		
		//1. 글목록을 불러온다.
		List<FileDto> list=fileDao.getList(dto);
		//2. request 에 담는다.
		mView.addObject("list", list);
		// 현재 페이지 번호 
		mView.addObject("pageNum", pageNum);
		mView.addObject("startPageNum", startPageNum);
		mView.addObject("endPageNum", endPageNum);
		// 전체 페이지의 갯수
		mView.addObject("totalPageCount", totalPageCount);
		
		//리턴한다.
		return mView;
	}

	@Override
	public void delete(HttpServletRequest request, int num) {
		//삭제할 파일의 정보를 얻어온다.
		FileDto dto=fileDao.getData(num);
		//1. 파일 시스템에서 물리적인 삭제
		String path=
			request.getServletContext().getRealPath("/upload")+
			File.separator+dto.getSaveFileName();
		try{
			new File(path).delete();
		}catch(Exception e){}
		//2. DB 에서 파일 정보 삭제
		fileDao.delete(num);		
	}

	@Override
	public ModelAndView getData(int num) {
		//Dao 를 이용해서 파일 정보를 얻어와서 
		FileDto dto=fileDao.getData(num);
		//ModelAndView 객체에 담아서 
		ModelAndView mView=new ModelAndView();
		mView.addObject("dto", dto);
		//리턴한다. 
		return mView;
	}

}



















