export const formatDate = (date) => {
  let dateString = date.toISOString();
  dateString = dateString.slice(0, dateString.length - 1) + "+00:00";
  return dateString;
}

const months = [
  'Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'
];

export function formatDateToString(dateString) {
  const date = new Date(dateString);
  
  const month = months[date.getMonth()];
  const day = String(date.getDate()).padStart(2, '0');
  const year = date.getFullYear();
  const hours = String(date.getHours()).padStart(2, '0');
  const minutes = String(date.getMinutes()).padStart(2, '0');
  
  return `${month} ${day}, ${year} ${hours}:${minutes}`;
}


