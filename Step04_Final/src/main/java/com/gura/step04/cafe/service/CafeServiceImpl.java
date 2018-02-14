package com.gura.step04.cafe.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.gura.step04.cafe.dao.CafeCommentDao;
import com.gura.step04.cafe.dao.CafeDao;
import com.gura.step04.cafe.dto.CafeCommentDto;
import com.gura.step04.cafe.dto.CafeDto;

@Service
public class CafeServiceImpl implements CafeService{
	/*
	 *  하나의 서비스에서 여러개의 Dao 에 의존할수도 있다.
	 */
	@Autowired
	private CafeDao cafeDao;
	
	@Autowired
	private CafeCommentDao commentDao;
	
	//한 페이지에 나타낼 로우의 갯수
	private static final int PAGE_ROW_COUNT=3;
	//하단 디스플레이 페이지 갯수
	private static final int PAGE_DISPLAY_COUNT=5;
	
	
	@Override
	public void insert(CafeDto dto) {
		cafeDao.insert(dto);
	}

	@Override
	public void update(CafeDto dto) {
		cafeDao.update(dto);
	}

	@Override
	public void delete(int num) {
		cafeDao.delete(num);
	}

	@Override
	public ModelAndView list(HttpServletRequest request) {
		/*
		 *  request 에 검색 keyword 가 전달 될수도 있고 안될수도 있다.
		 *  1. 전달 안되는 경우 : home.jsp 에서 목록보기를 누른경우
		 *  2. 전달 되는 경우 : list.jsp 에서 검색어를 입력후 form 전송한
		 *     경우. 
		 *  3. 전달 되는 경우 : 이미 검색을 한 상태에서 페이지 링크를 
		 *     누른경우
		 */
		//검색과 관련된 파라미터를 읽어와 본다.
		String keyword=request.getParameter("keyword");
		String condition=request.getParameter("condition");
		
		//글정보를 담을 ModelAndView 객체 
		ModelAndView mView=new ModelAndView();
		
		//CafeDto 객체를 생성해서
		CafeDto dto=new CafeDto();
		if(keyword != null){ //검색어가 전달된 경우
			if(condition.equals("titlecontent")){ //제목+내용 검색
				dto.setTitle(keyword);
				dto.setContent(keyword);
			}else if(condition.equals("title")){//제목 검색
				dto.setTitle(keyword);
			}else if(condition.equals("writer")){//작성자 검색
				dto.setWriter(keyword);
			}
			
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
		int totalRow = cafeDao.getCount(dto);
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
		List<CafeDto> list=cafeDao.getList(dto);
		
		mView.addObject("list", list);
		mView.addObject("pageNum", pageNum);
		mView.addObject("startPageNum", startPageNum);
		mView.addObject("endPageNum", endPageNum);
		mView.addObject("totalPageCount", totalPageCount);
		
		return mView;
	}

	@Override
	public ModelAndView detail(HttpServletRequest request) {
		//검색과 관련된 파라미터를 읽어와 본다.
		String keyword=request.getParameter("keyword");
		String condition=request.getParameter("condition");
		
		ModelAndView mView=new ModelAndView();
		
		//검색 조건을 알려 주기 위한 메세지 
		String msg=null;
		
		//CafeDto 객체를 생성해서
		CafeDto dto=new CafeDto();
		if(keyword != null){ //검색어가 전달된 경우
			if(condition.equals("titlecontent")){ //제목+내용 검색
				dto.setTitle(keyword);
				dto.setContent(keyword);
				msg="키워드 : "+keyword+" 에 대한 제목 내용 검색결과";
			}else if(condition.equals("title")){//제목 검색
				dto.setTitle(keyword);
				msg="키워드 : "+keyword+" 에 대한 제목 검색결과";
			}else if(condition.equals("writer")){//작성자 검색
				dto.setWriter(keyword);
				msg="키워드 : "+keyword+" 에 대한 작성자 검색결과";
			}
			
			mView.addObject("condition", condition);
			mView.addObject("keyword", keyword);
			//어떤 검색인지 메세지를 담는다.
			mView.addObject("msg", msg);
		}
		
		//1. 파라미터로 전달되는 글번호를 읽어온다.
		int num=Integer.parseInt(request.getParameter("num"));
		dto.setNum(num); //글번호도 dto 에 담는다.
		
		//2. 글의 조회수를 1 올린다.
		cafeDao.increaseViewCount(num);
		
		//3. 글번호에 해당되는 글정보를 얻어온다.
		CafeDto resultDto=cafeDao.getData(dto);	
		mView.addObject("dto", resultDto);
		//4. 글번호에 해당되는 덧글 목록 읽어오기 
		List<CafeCommentDto> commentList=commentDao.getList(num);
		mView.addObject("commentList", commentList);
		//5. 로그인 했는지 여부도 담기
		boolean isLogin=false;
		if(request.getSession().getAttribute("id") != null){
			isLogin=true;
		}
		mView.addObject("isLogin", isLogin);
				
		return mView;
	}

	@Override
	public ModelAndView detail(int num) {
		//글번호를 이용해서 글정보를 얻어와서 
		CafeDto dto=cafeDao.getData(num);
		//ModelAndView 객체에 담아서 
		ModelAndView mView=new ModelAndView();
		mView.addObject("dto", dto);
		//리턴해 준다.
		return mView;
	}
	
	@Override
	public void commentInsert(HttpServletRequest request) {
		//1. 파라미터로 전달되는 덧글 정보 읽어오기
		String writer=request.getParameter("writer");
		int ref_group=Integer.parseInt
				(request.getParameter("ref_group"));
		String target_id=request.getParameter("target_id");
		String content=request.getParameter("content");
		//덧글 내에서의 그룹번호를 읽어온다. 
		//null 이면 원글에 대한 덧글이고 아니면 덧글에 대한 덧글이다.
		String comment_group=request.getParameter("comment_group");
		//저장할 덧글 번호를 미리 읽어온다.
		int seq=commentDao.getSequence();
		
		//2. 새 덧글 정보를 dto 에 담고
		CafeCommentDto dto=new CafeCommentDto();
		dto.setNum(seq);
		dto.setWriter(writer);
		dto.setTarget_id(target_id);
		dto.setContent(content);
		dto.setRef_group(ref_group);
		if(comment_group==null){//원글에 대한 덧글인 경우
			//덧글의 그룹번호를 덧글의 글번호와 같게 설정한다.
			dto.setComment_group(seq);
		}else{//덧글의 덧글인 경우 
			//파라미터로 넘어온 덧글의 그룹번호를 넣어준다.
			dto.setComment_group(Integer.parseInt(comment_group));
		}
		//3. DB 에 저장하고
		commentDao.insert(dto);		
	}
}





















