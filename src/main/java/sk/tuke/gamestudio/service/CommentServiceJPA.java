package sk.tuke.gamestudio.service;

import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import sk.tuke.gamestudio.entity.Comment;

import java.util.List;

@Transactional
public class CommentServiceJPA implements CommentService{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void addComment(Comment comment) throws CommentException {
        entityManager.persist(comment);

    }

    @Override
    public List<Comment> getComments(String game) throws CommentException {
        String queryString = "SELECT c FROM Comment c where game = \'" + game + "\'";
        Query query = entityManager.createQuery(queryString, Comment.class);
        return query.getResultList();
    }

    @Override
    public void reset() {
//        entityManager.createNamedQuery("Score.resetScores").executeUpdate();
        // alebo:
         entityManager.createNativeQuery("delete from comment").executeUpdate();

    }
}
