import gsAxios from "."

export const fetchScores = (game) =>gsAxios.get('/score/' + game);
export const addScore = (game,player,points) =>gsAxios.post('/score',{
  game: game,
  player: player,
  points: points,
  playedOn: new Date()
});
