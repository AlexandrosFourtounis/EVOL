
$(document).ready(function ()
{
    isLoggedIn();
});


function isLoggedIn() {
    var xhr = new XMLHttpRequest();
    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {

            $("#ajaxContent").html("Welcome again" + xhr.responseText);

        } else if (xhr.status !== 200) {
            $("#choices").load("buttons.html");
        }
    };
    xhr.open('GET', 'Login');
    xhr.send();
}

function loginPOST() {
    var xhr = new XMLHttpRequest();
    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {

            $("#ajaxContent").html("Successful Login");
            const responseData = JSON.parse(xhr.responseText);

        } else if (xhr.status !== 200) {
            $("#error").html("Wrong Credentials");
            ('Request failed. Returned status of ' + xhr.status);
        }
    };
    var data = $('#loginForm').serialize();
    xhr.open('POST', 'Login');
    xhr.setRequestHeader
            ('Content-type', 'application/x-www-form-urlencoded');
    xhr.send(data);
}

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


//mitsos
function registerNewUser() {
    var xhr = new XMLHttpRequest();

    xhr.onload = function () {
        if (xhr.readyState === 4) {
            if (xhr.status === 200) {
                $("#ajaxContent").html("Registration successful");
            } else if (xhr.status === 409) {
                $("#ajaxContent").html("User already exists");
            } else {
                $("#ajaxContent").html("Error occurred during registration");
            }
        }
    };

    var formData = $('#registerForm').serialize();
    xhr.open('POST', 'RegisterUser'); // Adjust the endpoint accordingly
    xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    xhr.send(formData);
}

function getAvailableBicycles() {
    var xhr = new XMLHttpRequest();
    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            $("#ajaxContent").html(createTableFromJSONbicycle(JSON.parse(xhr.responseText)));
        } else if (xhr.status !== 200) {
            $("#ajaxContent").html("Error occurred while fetching available bicycles");
        }
    };

    xhr.open('GET', 'GetAvailableBicycles');
    xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    xhr.send();
}

function createTableFromJSONbicycle(data) {
    var html = "<table><tr><th>Category</th><th>Value</th></tr>";

    for (var i = 0; i < data.length; i++) {
        var bicycle = data[i];

        html += "<tr><td>Vehicle ID</td><td>" + bicycle.vehicle_id + "</td></tr>";
        html += "<tr><td>Special Number</td><td>" + bicycle.special_number + "</td></tr>";
    }

    html += "</table>";
    return html;
}

// Add this function to fetch available electric scooters
function getAvailableElectricScooters() {
    var xhr = new XMLHttpRequest();
    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            $("#ajaxContent").html(createTableFromJSONbicycle(JSON.parse(xhr.responseText)));
        } else if (xhr.status !== 200) {
            $("#electricScooterContent").html("Error retrieving available electric scooters");
        }
    };

    xhr.open('GET', 'GetAvailableElectricScooters');
    xhr.send();
}