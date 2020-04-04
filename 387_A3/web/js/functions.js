
function deleteBook(){
    var bookID = prompt("Please enter the ID of the book to be deleted");
    if(bookID !== null){
        var link = link = document.getElementByID("delete");
        link.href ="DeleteServlet?deleteBy=1&bookID=" + bookID;
    }
}

function deleteAll(){
    var sure = confirm("Are you sure you want to delete all books?");
    if (sure) {
        var link = document.getElementById("deleteAll");
        link.href = "DeleteServlet?deleteBy=0";
    }
}

function addBook(){
    var sure = confirm("Would you like to add a new book?");
    if (sure) {
        var link = document.getElementById("add");
        var title = prompt("Title:");
        var desc = prompt("Description:");
        var isbn = prompt("ISBN");
        var first = prompt("Author first name:");
        var last = prompt("Author last name:");
        var pub = prompt("Publisher name:");
        var pubAdd = prompt("Publisher Address:");
        link.href = "UpdateServlet?func=0&title="+title+"&desc="+desc+"&isbn="+isbn+"&first="+first+"&last="+last+"&pub="+pub+"&pubAdd="+pubAdd;
    }   
}

function updateBook(){
    var id = prompt("Which book would you like to update?");   
    if (id > 0) {
        var link = document.getElementById("update");
        var title = prompt("Title:");
        var desc = prompt("Description:");
        var isbn = prompt("ISBN");
        var first = prompt("Author first name:");
        var last = prompt("Author last name:");
        var pub = prompt("Publisher name:");
        var pubAdd = prompt("Publisher Address:");
        link.href = "UpdateServlet?func=1&id="+id+"&title="+title+"&desc="+desc+"&isbn="+isbn+"&first="+first+"&last="+last+"&pub="+pub+"&pubAdd="+pubAdd;
    }   
}

function viewBook(){
    var isbn = prompt("Which book would you like to view (ISBN)?");
    var view = prompt("Would you like to be able to update the book?");
    if (view === "yes") {view = 1;} else {view = 0;};
    if(isbn !== null) {
        var link = document.getElementById("view");
        link.href = "UpdateServlet?func=2&isbn="+isbn+"&update="+view;
    }
}

function getBook() {
    var id = prompt("Which book would you like to get a JSON for (ID)?");
    if (id !== null){
        var link = document.getElementById("getBook");
        link.href = "ServiceServlet?id="+id;
    }
}

function getAllBooks() {
    var sure = confirm("Would you like to request JSON of all books?");
    if (sure) {
        var link = document.getElementById("getAll");
        link.href = "ServiceServlet?id=0";
    }
}