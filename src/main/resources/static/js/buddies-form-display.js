function addAndShowBuddy(addressBookName) {
    let xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            document.getElementById("response-preamble").hidden = false;
            document.getElementById("add-buddies-table").innerHTML = convertToSingleRow(this.responseText);
        }
    }
    let form = document.getElementById("add-buddy-form");
    let params = new URLSearchParams();
    params.append("addressBookName", addressBookName);
    params.append("firstname", form.elements["firstname"].value);
    params.append("lastname", form.elements["lastname"].value);
    params.append("contactNumber", form.elements["contactNumber"].value);
    params.append("address", form.elements["address"].value);
    xhttp.open("GET", "/add?" + params.toString());
    xhttp.send();
    return false;
}

function removeBuddyOptions(addressBookName) {
    let xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            document.getElementById("id").innerHTML = convertToOptions(this.responseText);
        }
    }
    xhttp.open("GET", "/buddies?addressBookName=" + addressBookName);
    xhttp.send();
    return false;
}

function removeAndShowBuddy(addressBookName) {
    let xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            document.getElementById("response-preamble").hidden = false;
            document.getElementById("remove-buddies-table").innerHTML = convertToSingleRow(this.responseText);
        }
    }
    let form = document.getElementById("remove-buddy-form");
    let params = new URLSearchParams();
    params.append("addressBookName", addressBookName);
    params.append("id", form.elements["id"].value);
    xhttp.open("GET", "/remove?" + params.toString());
    xhttp.send();
    return false;
}

function showBuddies(addressBookName) {
    let xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            document.getElementById("buddies-table").innerHTML = convertToTable(this.responseText);
        }
    }
    xhttp.open("GET", "/buddies?addressBookName=" + addressBookName);
    xhttp.send();
    return false;
}

function convertToTable(jsonString) {
    let responseList = JSON.parse(jsonString);
    var table = "";
    var tableHeader = "<tr><th>Id</th><th>Firstname</th><th>Lastname</th><th>Contact</th><th>Address</th></tr>";

    table += "<table border='1'>" + tableHeader;
    for (var i = 0; i < responseList.length; i++) {
        table += "<tr>" +
            "<td>" + responseList[i].id + "</td>" +
            "<td>" + responseList[i].firstname + "</td>" +
            "<td>" + responseList[i].lastname + "</td>" +
            "<td>" + responseList[i].phoneNumber + "</td>" +
            "<td>" + responseList[i].address + "</td>" +
            "</tr>";
    }
    table += "</table>";
    console.log(table);
    return table;
}

function convertToSingleRow(jsonString) {
    let response = JSON.parse(jsonString);
    var table = "";
    var tableHeader = "<tr><th>Firstname</th><th>Lastname</th><th>Contact</th><th>Address</th></tr>";

    table += "<table border='1'>" + tableHeader;
    table += "<tr>" +
        "<td>" + response.firstname + "</td>" +
        "<td>" + response.lastname + "</td>" +
        "<td>" + response.phoneNumber + "</td>" +
        "<td>" + response.address + "</td>" +
        "</tr>";
    table += "</table>";
    console.log(table);
    return table;
}

function convertToOptions(jsonString) {
    let responseList = JSON.parse(jsonString);
    var options = "";

    for (var i = 0; i < responseList.length; i++) {
        options += "<option value=" + responseList[i].id + ">" +
            responseList[i].id + " | " + responseList[i].firstname + " | " + responseList[i].lastname + " | " + responseList[i].phoneNumber +
            "</option>"
    }
    return options;
}