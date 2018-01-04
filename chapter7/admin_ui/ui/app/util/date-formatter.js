
export function formatDate(date) {
  if (date == null) {
    return '';
  }

  return titleCase(date.dayOfWeek) + ' ' + date.dayOfMonth + ' ' + titleCase(date.month).substr(0,3) + ' ' + date.year;
}

function titleCase(string) {
  return string.substr(0,1) + string.toLowerCase().substr(1);
}