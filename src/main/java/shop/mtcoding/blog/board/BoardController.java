package shop.mtcoding.blog.board;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class BoardController {

    private final BoardNativeRepository boardNativeRepository;
    private final BoardRepository boardRepository;

    @GetMapping("/" )
    public String index(HttpServletRequest request) {
        List<Board> boardList = boardNativeRepository.findAll();
        request.setAttribute("boardList", boardList);
        return "index";
    }

    @GetMapping("/board/save-form")
    public String saveForm() {
        return "board/save-form";
    }

    @PostMapping("/board/save")
    public String save(BoardRequest.SaveDTO reqDTO){

        boardRepository.save(reqDTO);
        return "redirect:/";
    }

    //게시글 상세보기
    @GetMapping("/board/{id}")
    public String detail(@PathVariable int id, HttpServletRequest request){
        request.setAttribute("board", boardRepository.detail(id));
        return "board/detail" ;
    }

    //게시글 삭제하기
    @PostMapping("/board/{id}/delete")
    public String delete(@PathVariable int id){
        boardRepository.deleteById(id);
        return "redirect:/";
    }

    //게시글 수정하기
    @GetMapping("/board/{id}/update-form")
    public String updateForm(@PathVariable int id, HttpServletRequest request){
        request.setAttribute("board", boardRepository.detail(id));
        return "board/update-form";
    }

    @PostMapping("/board/{id}/update")
    public String update(@PathVariable int id, BoardRequest.UpdateDTO reqDTO){
        boardRepository.update(reqDTO, id);
        return "redirect:/board/" + id;
    }

}
