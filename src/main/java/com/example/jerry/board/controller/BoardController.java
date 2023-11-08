/*
 ┌───────────────────────────────────────────────────────────────────┐
 │ Copyright (c) 2023년 8월 16일 JerryDEV All rights reserved.        │
 └───────────────────────────────────────────────────────────────────┘
 */

/*
작성자 : Min Woo Song
작성일 : 2023-08-16
작성시간 : 오전 3:46
작성용도 : Bulletin board web page client request processing controller file
*/

package com.example.jerry.board.controller;

import com.example.jerry.board.domain.BoardVo;
import com.example.jerry.board.domain.FileVo;
import com.example.jerry.board.domain.ViewPageVo;
import com.example.jerry.board.service.BoardService;
import com.example.jerry.comment.service.CommentService;
import com.example.jerry.commons.annotation.LogException;
import com.example.jerry.user.domain.UserVo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.File;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping(value = "/board/*")
public class BoardController {

    private final BoardService boardService;
    private final CommentService commentService;

    @Autowired
    public BoardController(BoardService boardService, CommentService commentService) {
        this.boardService = boardService;
        this.commentService = commentService;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String lustPage(@RequestParam(value = "category_no", defaultValue = "0") int category_no, Model model, @RequestParam(value = "search_category_no", defaultValue = "0") int search_category_no, @RequestParam(value = "keyword", defaultValue = "") String keyword, @RequestParam(value = "pageNum", defaultValue = "1") int pageNum) {

        ArrayList<HashMap<String, Object>> dataList = boardService.getBoardList(category_no, search_category_no, keyword, pageNum);
        int count = boardService.getBoardCount(category_no, search_category_no, keyword);

        int totalPageCount = (int) Math.ceil(count / 10.0);

        // 1 2 3 4 5 , 6 7 8 9 10
        int startPage = ((pageNum - 1) / 5) * 5 + 1;
        int endPage = ((pageNum - 1) / 5 + 1) * (5);
        if (endPage > totalPageCount) {
            endPage = totalPageCount;
        }

        model.addAttribute("count", count);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("currentPage", pageNum);
        model.addAttribute("totalPageCount", totalPageCount);
        model.addAttribute("category_no", category_no);
        model.addAttribute("dataList", dataList);
        model.addAttribute("list", boardService.getBoardSearchCategoryList());
        model.addAttribute("search_category_no", search_category_no);
        model.addAttribute("keyword", keyword);


        return "board/list";
    }

    @GetMapping(value = "/write")
    public String writePosting(@RequestParam(value = "category_no", defaultValue = "0") int category_no, @ModelAttribute("boardVo") BoardVo param, Model model) {
        model.addAttribute("category_no", category_no);
        model.addAttribute("list", boardService.getCategoryList());
        return "board/write";
    }

    @PostMapping(value = "read")
    public String detailsPosting(@RequestParam(value = "board_no", defaultValue = "0") int board_no, Model model, HttpServletRequest request) {

        /* 조회수 증가 */
        List<ViewPageVo> viewPageVo = boardService.getViewPageList(board_no);
        if (boardService.isSelectByViewByBoardNo(board_no)) {
            if (!boardService.isSelectByLockupIp(request.getRemoteAddr())) {
                ViewPageVo param = new ViewPageVo();
                param.setBoard_no(board_no);
                param.setLockup_ip(request.getRemoteAddr());

                boardService.insertViewPage(param);
                boardService.increaseReadCount(board_no);
            }
        } else {
            ViewPageVo param = new ViewPageVo();
            param.setBoard_no(board_no);
            param.setLockup_ip(request.getRemoteAddr());

            boardService.insertViewPage(param);
            boardService.increaseReadCount(board_no);
        }

        for (ViewPageVo param : viewPageVo) {
            if (param != null) {
                if (boardService.isSelectByLockupIp(request.getRemoteAddr())) {
                    if (param.getLockup_ip().equals(request.getRemoteAddr())) {
                        Date writeDate = new Date(System.currentTimeMillis()); // 현재 서버 시간
                        Date tagetDate = new Date(param.getView_inquiry_time().getTime() + 1000 * 60 * 60 * 24); // 조회한 조회 일자

                        if (writeDate.after(tagetDate)) {
                            boardService.increaseReadCount(board_no);
                            boardService.updateViewPage(param);
                        }
                    }
                }
            }
        }
        List<Map<String, Object>> fileList = boardService.selectFileList(board_no);

        model.addAttribute("data", boardService.getBoard(board_no));
        model.addAttribute("dataList", commentService.getCommentList(board_no));
        model.addAttribute("fileList", fileList);

        return "board/read";

    }

    // 게시글 작성
    @PostMapping(value = "writePostingProcess")
    @LogException
    public String writePostingProcess(@Valid BoardVo param, BindingResult result, HttpSession session
            , MultipartFile[] uploadFiles) {
        if (result.hasErrors()) {
            return "board/write";
        }

        ArrayList<FileVo> fileVoList = new ArrayList<FileVo>();

        String uploadFolder = "C:/uploadFolder/";

        if (uploadFiles != null) {
            for (MultipartFile uploadFile : uploadFiles) {
                if (uploadFile.isEmpty()) {
                    continue;
                }

                // 날짜별 폴더 생성... 2022/01/19/
                Date today = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd/");
                String folderPath = sdf.format(today);

                File todayFolder = new File(uploadFolder + folderPath);

                if (!todayFolder.exists()) {
                    todayFolder.mkdirs();
                }

                // 중복되지 않게 저장해야된다...!!...
                // 방법 : 랜덤 + 시간
                String fileName = "";
                UUID uuid = UUID.randomUUID();
                fileName += uuid.toString();

                long currentTime = System.currentTimeMillis();
                fileName += "_" + currentTime;

                // 확장자 추가...
                String originalFileName = uploadFile.getOriginalFilename();
                String ext = originalFileName.substring(originalFileName.lastIndexOf("."));
                fileName += ext;

                try {
                    uploadFile.transferTo(new File(uploadFolder + folderPath + fileName));
                } catch (Exception e) {
                    e.printStackTrace();
                }

                FileVo fileVo = new FileVo();
                fileVo.setOrg_file_name(originalFileName);
                fileVo.setStored_file_name(fileName);
                fileVoList.add(fileVo);
            }
        }

        System.out.println(fileVoList.size());
        System.out.println(uploadFiles);

        UserVo sessionUser = (UserVo) session.getAttribute("sessionUser");
        param.setUser_no(sessionUser.getUser_no());
        boardService.insertWrite(param, fileVoList);

        return "redirect:./list";
    }

    @PostMapping(value = "edit")
    public String modifyPosting(@RequestParam(value = "board_no", defaultValue = "0") int boardNo, Model model, @ModelAttribute("boardVo") BoardVo boardVo) {

        model.addAttribute("data", boardService.getBoard(boardNo));
        model.addAttribute("list", boardService.getCategoryList());

        return "board/edit";
    }

    @RequestMapping(value = "/fileDown")
    public void fileDown(@RequestParam Map<String, Object> map, HttpServletResponse response) throws Exception {
        Map<String, Object> resultMap = boardService.selectFileInfo(map);
        String storedFileName = (String) resultMap.get("STORED_FILE_NAME");
        String originalFileName = (String) resultMap.get("ORG_FILE_NAME");
        Timestamp time = (Timestamp) resultMap.get("UPLOAD_WRITE_DATE");

        Date today = new Date(time.getTime());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd/");
        String folderPath = sdf.format(today);

        // 파일을 저장했던 위치에서 첨부파일을 읽어 byte[]형식으로 변환한다.
        byte fileByte[] = org.apache.commons.io.FileUtils
                .readFileToByteArray(new File("C://uploadFolder//" + folderPath + "//" + storedFileName));

        response.setContentType("application/octet-stream");
        response.setContentLength(fileByte.length);
        response.setHeader("Content-Disposition",
                "attachment; fileName=\"" + URLEncoder.encode(originalFileName, "UTF-8") + "\";");
        response.getOutputStream().write(fileByte);
        response.getOutputStream().flush();
        response.getOutputStream().close();
    }

}
