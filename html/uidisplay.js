//const Data = [
    //[" ", " ", "#", "#", "#", "#", "#", "#", "#", "#",],
  //  ["#", " ", " ", " ", " ", " ", " ", " ", " ", "#", ],
  //  ["#", " ", "#", "#", " ", "#", "#", " ", " ", "#", ],
 //   ["#", " ", " ", " ", " ", " ", " ", " ", " ", "#", ],
   // ["#", "#", "#", "#", "#", "#", "#", "#", "#", "#", ]
  //];
  function displayGrid(wordGrid) {

      var html = '<table>';
      wordGrid.forEach(function(row){
        html += '<tr>';

        row.forEach(function(cell){
          html +='<td>' + cell + '</td>';
        });

        html += '</tr>';
      });
      html += '</table>';
      console.log(html);
      return html;
}