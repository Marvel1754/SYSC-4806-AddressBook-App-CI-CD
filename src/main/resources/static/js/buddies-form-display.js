function addAndShowBuddy(addressBookName) {
    let xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if (this.readyState === 4 && this.status === 200) {
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
        if (this.readyState === 4 && this.status === 200) {
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
        if (this.readyState === 4 && this.status === 200) {
            document.getElementById("response-preamble").hidden = false;
            document.getElementById("remove-buddies-table").innerHTML = convertToSingleRow(this.responseText);
            removeBuddyOptions(addressBookName);
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
        if (this.readyState === 4 && this.status === 200) {
            document.getElementById("buddies-table").innerHTML = convertToTable(this.responseText);
        }
    }
    xhttp.open("GET", "/buddies?addressBookName=" + addressBookName);
    xhttp.send();
    return false;
}

function convertToTable(jsonString) {
    let responseList = JSON.parse(jsonString);
    let table = document.createElement("table");
    table.border = "1";

    let tableHeaderRow = document.createElement("tr");

    let tableHeaderId = document.createElement("th");
    tableHeaderId.textContent = "Id";
    let tableHeaderFirstname = document.createElement("th");
    tableHeaderFirstname.textContent = "Firstname";
    let tableHeaderLastname = document.createElement("th");
    tableHeaderLastname.textContent = "Lastname";
    let tableHeaderContact = document.createElement("th");
    tableHeaderContact.textContent = "Contact";
    let tableHeaderAddress = document.createElement("th");
    tableHeaderAddress.textContent = "Address";

    tableHeaderRow.append(tableHeaderId, tableHeaderFirstname, tableHeaderLastname, tableHeaderContact, tableHeaderAddress);

    table.appendChild(tableHeaderRow);

    let tableRow, tableRowId, tableRowFirstname, tableRowLastname, tableRowContact, tableRowAddress;
    for (var i = 0; i < responseList.length; i++) {
        tableRow = document.createElement("tr");

        tableRowId = document.createElement("td");
        tableRowId.textContent = responseList[i].id;
        tableRowFirstname = document.createElement("td");
        tableRowFirstname.textContent = responseList[i].firstname;
        tableRowLastname = document.createElement("td");
        tableRowLastname.textContent = responseList[i].lastname;
        tableRowContact = document.createElement("td");
        tableRowContact.textContent = responseList[i].phoneNumber;
        tableRowAddress = document.createElement("td");
        tableRowAddress.textContent = responseList[i].address;

        tableRow.append(tableRowId, tableRowFirstname, tableRowLastname, tableRowContact, tableRowAddress);

        table.appendChild(tableRow);
    }

    console.log(table.outerHTML);
    return table.outerHTML;
}

function convertToSingleRow(jsonString) {
    let response = JSON.parse(jsonString);
    let table = document.createElement("table");
    table.border = "1";

    let tableHeaderRow = document.createElement("tr");

    let tableHeaderFirstname = document.createElement("th");
    tableHeaderFirstname.textContent = "Firstname";
    let tableHeaderLastname = document.createElement("th");
    tableHeaderLastname.textContent = "Lastname";
    let tableHeaderContact = document.createElement("th");
    tableHeaderContact.textContent = "Contact";
    let tableHeaderAddress = document.createElement("th");
    tableHeaderAddress.textContent = "Address";

    tableHeaderRow.append(tableHeaderFirstname, tableHeaderLastname, tableHeaderContact, tableHeaderAddress);

    let singleRow = document.createElement("tr");

    let singleRowFirstname = document.createElement("td");
    singleRowFirstname.textContent = response.firstname;
    let singleRowLastname = document.createElement("td");
    singleRowLastname.textContent = response.lastname;
    let singleRowContact = document.createElement("td");
    singleRowContact.textContent = response.phoneNumber;
    let singleRowAddress = document.createElement("td");
    singleRowAddress.textContent = response.address;

    singleRow.append(singleRowFirstname, singleRowLastname, singleRowContact, singleRowAddress);

    table.appendChild(tableHeaderRow);
    table.appendChild(singleRow);

    console.log(table.outerHTML);
    return table.outerHTML;
}

function convertToOptions(jsonString) {
    let responseList = JSON.parse(jsonString);
    let options = document.createElement("select");

    let option;
    for (let i = 0; i < responseList.length; i++) {
        option = document.createElement("option");
        option.value = responseList[i].id;
        option.textContent = responseList[i].id + " | " + responseList[i].firstname + " | " + responseList[i].lastname + " | " + responseList[i].phoneNumber;
        options.appendChild(option);
    }
    return options.innerHTML;
}