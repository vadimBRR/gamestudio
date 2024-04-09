package sk.tuke.gamestudio.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import sk.tuke.gamestudio.entity.Rating;

@Transactional
public class RatingServiceJPA implements RatingService{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void setRating(Rating rating) throws RatingException {
        if (rating.getRating() < 1 || rating.getRating() > 5) {
            throw new RatingException("Rating should be between 1 and 5");
        }
        String queryString = "SELECT c FROM Rating c where game = \'" + rating.getGame() + "\' and player = \'" + rating.getPlayer() + "\'";
        Query query = entityManager.createQuery(queryString, Rating.class);
        try {
            Rating existing_rating = (Rating) query.getSingleResult();
            existing_rating.setRating(rating.getRating());
            existing_rating.setRatedOn(rating.getRatedOn());
//            entityManager.merge(existing_rating);

        } catch (NoResultException e) {
            entityManager.persist(rating);
        }

    }

    @Override
    public int getAverageRating(String game) throws RatingException {
        String queryString = "SELECT AVG(r.rating) FROM Rating r WHERE game = \'" + game + "\'";
        Query query = entityManager.createQuery(queryString);
        Double result =(Double) query.getSingleResult();
        if(result!=null) return result.intValue();
        else return 0;
    }

    @Override
    public int getRating(String game, String player) throws RatingException {
        String queryString = "SELECT c FROM Rating c where game = \'" + game + "\' and player = \'" + player + "\'";
        Query query = entityManager.createQuery(queryString, Rating.class);
        try {
            Rating existing_rating = (Rating) query.getSingleResult();
            return existing_rating != null ? existing_rating.getRating() : 0;
        } catch (NoResultException e) {
            return 0;
        }

    }

    @Override
    public void reset() {
         entityManager.createNativeQuery("delete from rating").executeUpdate();
    }
}
