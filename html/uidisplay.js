//const Data = [
    //[" ", " ", "#", "#", "#", "#", "#", "#", "#", "#",],
  //  ["#", " ", " ", " ", " ", " ", " ", " ", " ", "#", ],
  //  ["#", " ", "#", "#", " ", "#", "#", " ", " ", "#", ],
 //   ["#", " ", " ", " ", " ", " ", " ", " ", " ", "#", ],
   // ["#", "#", "#", "#", "#", "#", "#", "#", "#", "#", ]
  //];
  function displayGrid(wordGrid) {

      var html = '<table style = "width: 100%;">';
      wordGrid.forEach(function(row){
        html += '<tr>';

        row.forEach(function(cell){
          html +='<td style = "border: 1px solid black;text-align: center;">' + cell + '</td>';
        });

        html += '</tr>';
      });
      html += '</table>';
      return html;
}