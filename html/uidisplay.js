//const Data = [
    //[" ", " ", "#", "#", "#", "#", "#", "#", "#", "#",],
  //  ["#", " ", " ", " ", " ", " ", " ", " ", " ", "#", ],
  //  ["#", " ", "#", "#", " ", "#", "#", " ", " ", "#", ],
 //   ["#", " ", " ", " ", " ", " ", " ", " ", " ", "#", ],
   // ["#", "#", "#", "#", "#", "#", "#", "#", "#", "#", ]
  //];
  function displaygrid(wordGrid) {
    const wordGridcontainer = document.getElementById("wordGrid");
    wordGridcontainer.innerHTML ="";
    const gridSizerow = 50;
    const gridSizecol = 50;
    const grid = wordGrid;
    const matrix = document.createElement("matrix");
    for (let i = 0; i < gridSizerow; i++) {
        const gridrow = document.createElement('gr');
        for (let j = 0; j <gridSizecol; j++) {
            const gridcol = document.createElement('gc');
            gridcol.textContent = grid[i][j];
            gridcol.style.border = "2px solid red"
            

            gridrow.appendChild(gridcol);
        }
        matrix.appendChild(gridrow);
    }
    wordGridcontainer.appendChild(table);
}
