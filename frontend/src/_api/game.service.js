import gsAxios from "."

export const fetchField = () =>gsAxios.get('/game/field' );

export const fetchNewGame = (field_size,mode,tile,useSuperPowers) => {
  const url = `/game/new?field_size=${field_size}&mode=${mode}&tile=${tile}&is_abilities=${useSuperPowers}`;
  return gsAxios.get(url);
};
export const move = (direction) => {
  const url = `/game/move?direction=${direction}`;
  return gsAxios.get(url);
};
export const UseAbitilites = (number) => {
  const url = `/game/abilities?number=${number}`;
  return gsAxios.get(url);
};
export const UseAbitilitesWithCord = (number, row, col) => {
  const url = `/game/abilities?number=${number}&row=${row}&col=${col}`;
  return gsAxios.get(url);
};
export const fetchStatus = () => {
  const url = `/game/status`;
  return gsAxios.get(url);
};

