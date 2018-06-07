function searchBooks(sortStr) {

    $.ajax({
        url: 'http://localhost:8080/books/sort/' + sortStr,
        type: 'get',
        data: {},
        crossDomain: true,
        dataType: 'json',
        success: function (data) {
            $('#foundBooksTable').find("tr:gt(0)").remove();
            var isFound = false;
            data.forEach(function (item, i, arr) {
                isFound = true;
                console.info(i);
                $('#foundBooksTable tr:last').after('<tr>' +
                    '<th style="border: 1px solid black;">' + (i + 1) + ' </th>' +
                    '<th style="border: 1px solid black;"> ' + item['bookName'] + ', ' + item['author'] + ', ' + item['issueYear'] + ' </th>' +
                    '<th style="border: 1px solid black;"><a href="http://www.math.odu.edu/~jhh/Volume-1.PDF">download</a></th>' +
                    '</tr>');
            });
            if (!isFound) {
                alert("No books found !");
            }
        }
    });

};

$(document).ready(function () {

    /*$("#searchInput").keyup(function(){
     var sortStr = $(this).val();
     searchBooks(sortStr);
     });
     */
    $("#searchButton").click(function () {
        var sortStr = $("#searchInput").val();

        searchBooks(sortStr);

    });


});
