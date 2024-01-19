/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function initDB() {
    var xhr = new XMLHttpRequest();
    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            alert("Database initialization successful");
        } else if (xhr.status !== 200) {
            alert("Error occurred during database initialization");
        }
    };

    xhr.open('GET', 'InitDB');
    xhr.send();
}


function deleteDB() {
    var xhr = new XMLHttpRequest();
    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            $("#ajaxContent").html("Successful Deletion");
        } else if (xhr.status !== 200) {
            $("#ajaxContent").html("Error Occured");
        }
    };

    xhr.open('GET', 'DeleteDB');
    xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    xhr.send();
}


function createTableFromJSONcar(data) {
    var html = "<table><tr><th>Category</th><th>Value</th></tr>";

    for (var i = 0; i < data.length; i++) {
        var car = data[i];

        html += "<tr><td>Vehicle ID</td><td>" + car.vehicle_id + "</td></tr>";
        html += "<tr><td>Registration Number</td><td>" + car.registration_number + "</td></tr>";
        html += "<tr><td>Type</td><td>" + car.type + "</td></tr>";
        html += "<tr><td>Number of Passengers</td><td>" + car.number_of_passengers + "</td></tr>";
    }

    html += "</table>";
    return html;
}


function getAvailableCars() {
    var xhr = new XMLHttpRequest();
    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            $("#ajaxContent").html(createTableFromJSONcar(JSON.parse(xhr.responseText)));
        } else if (xhr.status !== 200) {
            $("#ajaxContent").html("Error fetching available cars");
        }
    };

    xhr.open('GET', 'GetAvailableCars');
    xhr.send();
}
