package sk.tuke.gamestudio.service;
import sk.tuke.gamestudio.entity.Rating;
import java.sql.*;


public class RatingServiceJDBC implements RatingService {
    public static final String URL = "jdbc:postgresql://localhost/gamestudio";
    public static final String USER = "postgres";
    public static final String PASSWORD = "random12342";
    public static final String SELECT = "SELECT game, player, rating, ratedOn FROM rating WHERE game = ? AND player = ?";
    public static final String AVG_GET = "SELECT AVG(rating) FROM rating WHERE game = ?";
    public static final String DELETE = "DELETE FROM rating";
    public static final String INSERT = "INSERT INTO rating (game, player, rating, ratedOn) VALUES (?, ?, ?, ?) ON CONFLICT (game, player) DO UPDATE SET rating = EXCLUDED.rating, ratedOn = EXCLUDED.ratedOn";

    @Override
    public void setRating(Rating rating) {
        if(rating.getRating()<1 || rating.getRating()>5) throw new RatingException("It is possible to set a rating value from 1 to 5");;

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(INSERT)
        ) {
            statement.setString(1, rating.getGame());
            statement.setString(2, rating.getPlayer());
            statement.setInt(3, rating.getRating());
            statement.setTimestamp(4, new Timestamp(rating.getRatedOn().getTime()));
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RatingException("Problem inserting rating", e);
        }
    }

    @Override
    public int getAverageRating(String game) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(AVG_GET);
        ) {
            statement.setString(1, game);

            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
                return 0;
            }
        } catch (SQLException e) {
            throw new RatingException("Problem selecting average rating", e);
        }
    }

    @Override
    public int getRating(String game, String player) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(SELECT);
        ) {
            statement.setString(1, game);
            statement.setString(2, player);

            ResultSet rs = statement.executeQuery();
            if(rs.next()) return rs.getInt("rating");
            else return 0;
        } catch (SQLException e) {
            throw new RatingException("Problem getting rating for game: \" + game + \" and player: \" + player", e);
        }
    }

    @Override
    public void reset() {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement();
        ) {
            statement.executeUpdate(DELETE);
        } catch (SQLException e) {
            throw new ScoreException("Problem deleting rating", e);
        }
    }
}
