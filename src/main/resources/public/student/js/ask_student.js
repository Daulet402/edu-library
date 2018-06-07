 
function submitBookRequest(author, name, publicationDate, studentId) {

var jsonData = "{\"author\":\""+author+"\", \"name\":\""+name+"\", \"publicationDate\":"+publicationDate+", \"studentId\": "+studentId+" }";

$.ajax({
    url: 'http://localhost:8080/books/request/new',
    type: 'post',
    data: jsonData,
    headers: {
        "Content-Type": 'application/json'
    },   
    success: function (data) {
        alert("Book Request was added");
    }
});
};

$( document ).ready(function() {
 $(".last").click(function() {
 	var author = $("#bookAuthor").val();
 	var name = $("#bookName").val();
 	var publicationDate = $("#bookPublicationDate").val();
 	if(!author || !name || !publicationDate){
 		alert("Input all fileds");
 		return;
 	}
     submitBookRequest(author, name, "\"" + publicationDate + "T00:00:00\"", null);
 });
});
