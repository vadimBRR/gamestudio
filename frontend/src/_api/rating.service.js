import gsAxios from "."

export const fetchAvgRating = (game) =>gsAxios.get('/rating/' + game);
export const fetchRatingByPlayer = (game,player) =>gsAxios.get('/rating/' + game + '/' + player);

export const addRating = (game,player,rating) =>gsAxios.post('/rating',{
  game: game,
  player: player,
  rating: rating,
  ratedOn: new Date()
});
