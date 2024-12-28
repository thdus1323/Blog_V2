package shop.mtcoding.blog.board;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
public class BoardRepository {
    private final EntityManager em;

    @Transactional
    public void save(BoardRequest.SaveDTO reqDTO){
        em.persist(reqDTO.toEntity());

    }

    public Board detail(int id){
        return em.find(Board.class, id);
    }

    @Transactional
    public void deleteById(int id){
        Query query = em.createQuery("delete from Board b where b.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Transactional
    public void update(BoardRequest.UpdateDTO reqDTO ,int id){
        Board board = detail(id);
        board.update(reqDTO);
    }


}
