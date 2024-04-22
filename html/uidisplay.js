//const Data = [
    //[" ", " ", "#", "#", "#", "#", "#", "#", "#", "#",],
  //  ["#", " ", " ", " ", " ", " ", " ", " ", " ", "#", ],
  //  ["#", " ", "#", "#", " ", "#", "#", " ", " ", "#", ],
 //   ["#", " ", " ", " ", " ", " ", " ", " ", " ", "#", ],
   // ["#", "#", "#", "#", "#", "#", "#", "#", "#", "#", ]
  //];
  const playerColors = ['#1eff00', '#ff0000', '#0000ff', '#ffff00'];
  function setPlayerColors(playerColors) {v
    document.documentElement.style.setProperty('--player1-color', playerColors[0]);
    document.documentElement.style.setProperty('--player2-color', playerColors[1]);
    document.documentElement.style.setProperty('--player3-color', playerColors[2]);
    document.documentElement.style.setProperty('--player4-color', playerColors[3]);
}
  function displayGrid(wordGrid) {
    var html = '<table style="width: 100%;">';
    wordGrid.forEach(function(row, rowIndex) {
        html += '<tr>';
        row.forEach(function(cell, colIndex) {
           // html += '<td style="border: 1px solid black;text-align: center;">';
            //html += cell;
            html += '</td>';
            html += '<td>';
            //<button class="puzzleCell" onclick="toggleSelected(this)" data-x="0" data-y="0">A</button>
            html += '<button class="puzzleCell" data-x="' + colIndex + '" data-y="' + rowIndex + '">';
            html += cell;
            //html += '</button>';
            html += '</td>';
        });
        html += '</tr>';
    });
    html += '</table>';
    
    return html;
}
function toggleCell(cell) {
  cell.classList.toggle('selected'); // Toggle the 'selected' class
  const color = cell.classList.contains('selected') ? getPlayerColor() : ''; // Get player color if selected
  cell.style.backgroundColor = color; // Set background color
}
function getPlayerColor() {
  // Add logic to determine the player's color based on their index or other criteria
  return '#ff0000'; // Default color if no specific logic is implemented
}
document.addEventListener('click', function(event) {
  if (event.target.classList.contains('puzzleCell')) {
      event.target.classList.toggle('selected');
  }
});



function setPlayerColor(color) {
  const playerDiv = document.createElement("div");
  playerDiv.className = "player";
  playerDiv.style.backgroundColor = color;
  document.getElementById("playerContainer").appendChild(playerDiv);
}
//function toggleSelected(button) {
 // Prevent selection if the game hasn't started
  //if (button.classList.contains("selected")) return; // Prevent re-selecting a letter

  // Toggle selected class and player-specific color class for the button
 // button.classList.toggle("selected");

  // Implement logic for what happens after a letter is selected by the player
//}

function page2(){
document.getElementById("page1").style.display = "none";
document.getElementById("page2").style.display = "block";
document.getElementById("page2").innerHTML = displayGrid();
}

//connect chat to websocket

const connection = new WebSocket("ws://localhost:9116");

connection.onopen = function () {
  console.log(" chat connection established.");
};

connection.onmessage = function (event) {
  const data = JSON.parse(event.data);
  if (data.type === "chat") {
    displayChatMessage(data.sender,data.message);
  } else {
    console.log("recieved message: ", data);
  }
};

connection.onerror = function (error) {
  console.error("websocket error: ",error);
};
//sending message
function sendMessage() {
  const input_element = document.getElementById("chat-input");
  const message = input_element.ariaValueMax.trim();

  if (message !== "") {
    connection.send(JSON,stringify({
      type: "chat",
      sender: "Me",
      message: message
    }));
    input_element.value = "";
  }
}

//displaying message
function displayChatMessage(sender, message) {
  const chatMessages = document.getElementById("chat-input");
  const message_element = document.createElement("div");
  message_element.textContent = sender + ": " + message;
  chatMessages.appendChild(message_element);
}