 function getAllBookRequest() {

 $.ajax({
     url: 'http://localhost:8080/administrator/books/request/all',
    type: 'get',
    data: { },
    crossDomain: true,
    dataType: 'json',
    success: function (data) {
        data.forEach(function(item, i, arr) {
            var createdTime = new Date(item['createdOn']);
            var publicationDate = new Date(item['publicationDate']);
            var createdTimeOut = createdTime.getDate() + '.' + (createdTime.getMonth() + 1) + '.' + createdTime.getFullYear() + ' ' + createdTime.getHours() + ':' + createdTime.getMinutes();
            var publicationDateOut = publicationDate.getDate() + '.' + (publicationDate.getMonth() + 1) + '.' + publicationDate.getFullYear();

            $('#notificationsTable tr:last').after('<tr>' +
                '<th style=\"border: 1px solid black;\"> ' + item['studentId'] + ' </th>' +
                '<th style="border: 1px solid black;"> ' + item['name'] + ', ' + item['author'] + ', ' + publicationDateOut + ' </th>' +
                '<th style="border: 1px solid black;"> ' + createdTimeOut + '</th>' +
                '</tr>');
        });

        /*$('#notificationsTable').after(
         '<div class="container-fluid footer" style="position: absolute;bottom: 0;width: 100%">' +
         '<div class="container footer">' +
         '<p>© 2016 Suleyman Demirel University.</p>' +
         '</div>' +
         '</div>'
         );*/
    }
});
};


$( document ).ready(function() {
getAllBookRequest();
    console.log("ok");
});
