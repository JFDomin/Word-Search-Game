
const playerColors = ['#1eff00', '#ff0000', '#0000ff', '#ffff00'];
function setPlayerColors(playerColors) {
  document.documentElement.style.setProperty('--player1-color', playerColors[0]);
  document.documentElement.style.setProperty('--player2-color', playerColors[1]);
  document.documentElement.style.setProperty('--player3-color', playerColors[2]);
  document.documentElement.style.setProperty('--player4-color', playerColors[3]);
}
function displayGrid(wordGrid) {
  var html = '<table style="width: 75%;">';
  wordGrid.forEach(function(row, rowIndex) {
      html += '<tr>';
      row.forEach(function(cell, colIndex) {
         // html += '<td style="border: 1px solid black;text-align: center;">';
          //html += cell;
          html += '</td>';
          html += '<td>';
          //<button class="puzzleCell" onclick="toggleSelected(this)" data-x="0" data-y="0">A</button>
          html += '<button class="puzzleCell" data-x="' + colIndex + '" data-y="' + rowIndex + '" onclick=gridSelection(' + rowIndex + ',' + colIndex + ') ">';
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

//not needed, just keeping for reference
/*
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
}; */

document.getElementById("send-button").addEventListener("click",sendMessage);
//sending message
function sendMessage() {
  const input_element = document.getElementById("chat-input");


  if (input_element !== "") {
    const U = new UserEvent;
    U.GameID = gameid;
    buttonType = "send";
    U.buttonType = buttonType;
    U.nickname = nickname;
    const chatMessage = {
    type: "chat",
    sender: nickname,
    message: input_element
  };
  connection.send(JSON.stringify(chatMessage));
  console.log("sending message:",input_element)
  displayChatMessage(nickname, input_element);
  input_element.value = "";
}
}

//displaying message
function displayChatMessage(sender, message) {
  const chatMessages = document.getElementById("message-container");
  const message_element = document.createElement("div");
  message_element.textContent = '${sender}: ${message}';
  chatMessages.appendChild(message_element);
  console.log("displaying message:",message)
}

function countdownTimer(duration) {
var timer = duration; 
var minutes;
var seconds;
var timerInterval;

function stopTimer() {
  clearInterval(timerInterval);
  document.getElementById('timer').textContent = "";
}

timerInterval = setInterval(function() {
  minutes = parseInt(timer / 60, 10);
  seconds = parseInt(timer % 60, 10);

  minutes = minutes < 10 ? "0" + minutes : minutes;
  seconds = seconds < 10 ? "0" + seconds : seconds;

  document.getElementById("timer").textContent = minutes + ":" + seconds;

  if (--timer < 0) {
    stopTimer();
    document.getElementById("timer").textContent = "Game Over!";
  }
}, 1000);
}
//displaying message
function displayChatMessage(sender, message) {
const chatMessages = document.getElementById("chat-input");
const message_element = document.createElement("div");
message_element.textContent = sender + ": " + message;
chatMessages.appendChild(message_element);
}
// Function to display the leaderboard
function displayLeaderboard() {
const leaderboardContainer = document.getElementById("leaderboard-container");
leaderboardContainer.style.display = "block";

const leaderboardBody = document.getElementById("leaderboard-body");
leaderboardBody.innerHTML = ""; 


const allScores = yourLeaderboardInstance.getAllScores();

const sortedScores = Object.entries(allScores).sort((a, b) => b[1] - a[1]);

sortedScores.forEach(([name, score], index) => {
    const row = leaderboardBody.insertRow();
    const rankCell = row.insertCell(0); // Insert cell for rank
    const nameCell = row.insertCell(1); // Insert cell for name
    const scoreCell = row.insertCell(2); // Insert cell for score
    rankCell.textContent = index + 1; // Set rank
    nameCell.textContent = player.name; // Set name
    scoreCell.textContent = player.score; // Set score
});
}

function toggleLeaderboard() {
const leaderboardContainer = document.getElementById("leaderboard-container");
if (leaderboardContainer.style.display === "block") {
    leaderboardContainer.style.display = "none"; 
} else {
    displayLeaderboard(); 
    leaderboardContainer.style.display = "block"; 
}
}


function showLeaderboard() {
toggleLeaderboard();
}
