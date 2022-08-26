<?php

/*
 * Following code will list all the books
 */

// array for JSON response
$response = array();

// include db connect class
require_once __DIR__ . '/db_config.php';

// Connecting to mysql database
$con = mysqli_connect(DB_SERVER, DB_USER, DB_PASSWORD,DB_DATABASE);


// get all books from books table
$result = mysqli_query($con,"SELECT * FROM books");

// check for empty result
if (mysqli_num_rows($result) > 0) {
    // looping through all results
    // books node
    $response["books"] = array();

    while ($row = mysqli_fetch_array($result)) {
        // temp user array
        $book = array();
        $book["pid"] = $row["pid"];
        $book["name"] = $row["name"];
        $book["author"] = $row["author"];
        $book["price"] = $row["price"];
        $book["linkAvatar"] = $row["linkAvatar"];
        $book["created_at"] = $row["created_at"];
        $book["updated_at"] = $row["updated_at"];
		$book["description"] = $row["description"];

        // push single book into final response array
        array_push($response["books"], $book);
    }
    // success
    $response["success"] = 1;

    // echoing JSON response
    echo json_encode($response);
} else {
    // no books found
    $response["success"] = 0;
    $response["message"] = "Xin lỗi, không tìm thấy sản phẩm";

    // echo no users JSON
    echo json_encode($response);
}
?>
