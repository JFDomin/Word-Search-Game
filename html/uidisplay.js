
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
          const buttonId = 'button-'+ rowIndex+','+colIndex;
          html += '<button id="'+buttonId+'"class="puzzleCell" data-x="' + colIndex + '" data-y="' + rowIndex + '" onclick=gridSelection(' + rowIndex + ',' + colIndex + ') ">';
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
const messageContainer = document.getElementById('message-container');
//sending message
function sendMessage() {
  const input_element = document.getElementById("chat-input");
  const message = document.getElementById("chat-input").value;
  console.log(nickname, ": ",message);

  if (message !== "") {
    U = new UserEvent;
    U.nickname = nickname;
    U.button = "chatMsg";
    U.msg = message;

  
    connection.send(JSON.stringify(U));
    console.log("sending message:",message);
    messageContainer.textContent += message + '\n';
    messageContainer.scrollTop = messageContainer.scrollHeight;
    displayChatMessage(nickname,message);
    input_element.value = ""; //resets input box to show placeholder after message sent
}

}

document.getElementById("send-button").addEventListener("click",sendMessage);
document.getElementById('chat-input').addEventListener('keydown',function(event){
  if(event.key === 'Enter') {
    sendMessage();
  }
})



//displaying message
function displayChatMessage(sender, message) {
  const chatMessages = document.getElementById("message-container");
  const message_element = document.createElement("div");
  message_element.classList.add('message')
  message_element.textContent = '${sender}: ${message}';
  chatMessages.appendChild(message_element);
}

function toggleChat(){
    const chatContainer = document.getElementById("chat");
      if (chatContainer.style.display === "block") {
        chatContainer.style.display = "none"; 
      } else {
        displayChatMessage();
        chatContainer.style.display = "block"; 
      }
     }
    
    function showChat() {
    toggleChat();
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
    U = new UserEvent;
    U.GameId = gameid;
    U.button = "GameOver";
    connection.send(JSON.stringify(U));
  }
}, 1000);
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
var gameid;
var color;
 class UserEvent {
 GameId; // the game ID on the server
 PlayerIdx; // either PLAYER1 PLAYER2 PLYAER 3 or PLAYER4
 button; //what button they pressed 
 nickname; //the nickname entered if it is join button
 ready;
 selectedCells; //the int [][] coordinates of the first and last letter selected
 Color;
}
let nickname = "";
let error = document.getElementById("error");

function handleMissingNickname(){
    console.log("player missing nickname")
    error.textContent = "Please enter a nickname."
    error.style.color = "red"
    return
}
function handleChange(event) {
}
function handleNickEnter(){
    const nickname = document.getElementById("input-data").value
    const joinButton = document.getElementById("join-button");
    // const readyButton = document.getElementById("ready-btn");
    // const startButton = document.getElementById("start-btn");
    console.log('nickname', nickname);
    console.log(gameid);
    if (!nickname) {
        handleMissingNickname();
    }
    else {
        U = new UserEvent;
        buttonType = "join";
        U.nickname = nickname;
        U.button = buttonType;
        U.GameId = gameid;
        connection.send(JSON.stringify(U));
        console.log(JSON.stringify(U));

        error.textContent = "";
        console.log("player joined: ", nickname);
        joinButton.disabled = true;
    }

}
function readyPlayer(){
    const nickname = document.getElementById("input-data").value
    // logic is not complete yet
    if (!nickname) {
        handleMissingNickname();
    }
    else {
        buttonType = "readyUp";
        U.button = buttonType;
        U.GameId = gameid;
        U.nickname = nickname;
        connection.send(JSON.stringify(U));
        console.log(JSON.stringify(U));
        error.textContent = ""
        console.log("player ready: ", nickname);
    }

}
function startGame(){
    const nickname = document.getElementById("input-data").value;
    if (!nickname) {
        handleMissingNickname();
    }
    else {
        console.log("game started by: ", nickname);
        buttonType = "startGame";
        U.button = buttonType;
        U.GameId = gameid;
        connection.send(JSON.stringify(U));
        console.log(JSON.stringify(U));
    }
}
var connection = null;
var serverUrl = "ws://" + window.location.hostname +":"+ (parseInt(location.port) + 100);
document.getElementById("topMessage").innerHTML = "?";
// Create the connection with the server
connection = new WebSocket(serverUrl);

connection.onopen = function (evt) {
    console.log("open - Connection established");
    document.getElementById("topMessage").innerHTML = " "

}
connection.onclose = function (evt) {
    console.log("close");
    document.getElementById("topMessage").innerHTML = "Server Offline"
}
connection.onmessage = function (evt) {
    const joinButton = document.getElementById("join-button");
    var msg;
    msg = evt.data;
    console.log("Message received: " + msg);
    const obj = JSON.parse(msg);
    
    if('NotUnique' === obj ){
        joinButton.disabled = false;
        error.textContent = "Name not Unique";
        error.style.color = "red";
        return;
    }
    else if('CantStart' === obj){
        error.textContent = "Not Enough Players Ready";
        error.style.color = "red";
        return;
    }
    if (obj.type == "wordGrid"){
        //console.log(obj.data);
        //displayGrid(obj.data);
        const tableContainer = document.getElementById('table-container');
        tableContainer.innerHTML = displayGrid(obj.data);
        document.getElementById('table-container').style.display = 'none';
    }
    if(obj.type === "chat") {
        displayChatMessage(obj.sender,obj.message);
        return;
    } 
    if('YouAre' in obj){
        if(obj.YouAre == "PLAYER1"){
            idx = 1;
            color = playerColors[idx];
        }
        else if (obj.YouAre == "PLAYER2"){
            idx = 2;
            color = playerColors[idx]
        }
        else if(obj.YouAre == "PLAYER3"){
            idx = 3;
            color = playerColors[idx];
        }
        else{
            idx = 4;
            color = playerColors[0];
        }
        gameid = obj.GameId;
        console.log(msg);
    }
            //pay attention to only this game 
    if(gameid === obj.GameId){
        if ('playerList' in obj && Array.isArray(obj.playerList)) {
        console.log("got player list");
        //get the gameid out of the message specifically to update if gameid == to the msg gameid
        //this seperates the display of each game
        updatePlayerList(obj.playerList);
        }
        else if ('ActualGameStart' in obj){
            document.getElementById('Lobby').style.display = 'none';
            document.getElementsByClassName('lobby').style.display ='none';
            document.getElementsByClassName('waiting-players').style.display ='none';
            document.getElementById('table-container').style.display = 'block';
            document.getElementById('WaitingPlayers').style.display = 'none';
            document.getElementById('timer').style.display = 'block';
            countdownTimer(600); // set to 10 minutes
        }
        else if('awardWord' in obj){
            const coords = JSON.parse(obj.selectedCells);
            const color = obj.playerColor;
            const orientation = obj.orientation;
            console.log(orientation);
            if(orientation === 'horizontal'){
                highlightHorizontal(coords,color);
            } 
            else if(orientation === 'vertical'){
                highlightVertical(coords,color);
            }
            else if(orientation === 'DiagDown'){
                highlightDiagDown(coords,color);
            }
            else if(orientation === 'DiagUp'){
                highlightDiagUp(coords, color);
            }
            else if(orientation === 'reverse'){
                highlightReverse(coords,color);
            }                                                                                                                                                                                                                                                                                                
        }
    }
}

function highlightHorizontal(coords, color){
    const startRow= coords[0][0];
    const endRow = coords[1][0];
    const startCol = coords[0][1];
    const endCol = coords[1][1];
    const grid = document.getElementById('table-container');
    for(let i = startRow; i <= endRow; i++){
        for(let j = startCol; j <= endCol; j++){
            const buttonId = 'button-'+ i +','+j;
            const button = document.getElementById(buttonId);
            button.style.backgroundColor = color;
        }
    }
}
function highlightVertical(coords, color){
    const startRow= coords[0][0];
    const endRow = coords[1][0];
    const startCol = coords[0][1];
    const endCol = coords[1][1];
    //column stays the same for vertical
    for(let j = startRow; j <= endRow; j++){
        const buttonId = 'button-'+ j +','+startCol;
        const button = document.getElementById(buttonId);
        button.style.backgroundColor = color;
    }
}
function highlightDiagDown(coords, color){
    const startRow= coords[0][0];
    const endRow = coords[1][0];
    let startCol = coords[0][1];
    const endCol = coords[1][1];
    const grid = document.getElementById('table-container');
    for(let i = startRow; i <= endRow; i++){
        const buttonId = 'button-'+i+','+startCol;
        const button = document.getElementById(buttonId);
        button.style.backgroundColor = color;
        startCol--;
    }
}
function highlightDiagUp(coords, color){
    const startRow= coords[0][0];
    const endRow = coords[1][0];
    let startCol = coords[0][1];
    const endCol = coords[1][1];
    const grid = document.getElementById('table-container');
    for(let i = startRow; i >= endRow; i--){
        const buttonId = 'button-'+i+','+startCol;
        const button = document.getElementById(buttonId);
        button.style.backgroundColor = color;
        startCol++;
    }
}
function highlightReverse(coords, color){
    const startRow= coords[0][0];
    const endRow = coords[1][0];
    const startCol = coords[0][1];
    const endCol = coords[1][1];
    for(let j = startCol; j >= endCol; j--){
        const buttonId = 'button-'+ startRow +','+j;
        const button = document.getElementById(buttonId);
        button.style.backgroundColor = color;
    }
}
function updatePlayerList(playerList){
    const playerContainer = document.getElementById("playerContainer");
    const waitingText = document.getElementById("waiting-text");
    const readyButton = document.getElementById("ready-btn");
    const startButton = document.getElementById("start-btn");
    
    playerContainer.innerHTML = ""; // Clear existing player list
    
    playerList.forEach(function(player) {
        const playerDiv = document.createElement("div");
        playerDiv.className = "player";
        playerDiv.textContent = player.nickname;
        if (player.isReady) {
        playerDiv.style.color = 'green';
        }
        playerContainer.appendChild(playerDiv);
        const playerIndex = player.playerID;
        //color = playerColors[playerIndex];
        console.log(playerIndex);
        console.log(color);
        //playerDiv.style.backgroundColor = playerColor;
    });
    
    if (playerList.length === 1) {
        waitingText.textContent = "Waiting for another player...";
        readyButton.disabled = false; // Disable ready button when there's only one player
        startButton.disabled = true; // Disable start button when there's only one player
    } else {
        waitingText.textContent = "Waiting for Players to ready up";
        readyButton.disabled = false;
        startButton.disabled = false;
    }
    
}
let selectedCells = [];
function gridSelection(row, col){
const nickname = document.getElementById("input-data").value
const buttonIndex = [row,col];
if(selectedCells.includes(buttonIndex)){
    console.log("same cell selected");
    selectedCells = [];
}
else{
    selectedCells.push(buttonIndex);
    console.log("Button pressed", buttonIndex, " by", nickname);
}

if(selectedCells.length == 2 ){
    console.log("two buttons pressed, send data here to check if word");
    console.log(selectedCells);
    // clear selected cells after
    U = new UserEvent;
    U.nickname = nickname;
    U.GameId = gameid;
    U.button = "selectedCells";
    U.Color = color;
    U.selectedCells = selectedCells;
    connection.send(JSON.stringify(U));
    console.log(JSON.stringify(U));
    selectedCells = [];
}


};