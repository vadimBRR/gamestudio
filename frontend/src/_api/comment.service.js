import gsAxios from "."

export const fetchComments = (game) =>gsAxios.get('/comment/' + game);
export const addComment = (game,player,comment) =>gsAxios.post('/comment',{
  game: game,
  player: player,
  comment: comment,
  commentedOn: new Date()
});
