<?php

/*
 * Following code will get single product details
 * A product is identified by product id (pid)
 */

// array for JSON response
$response = array();

// include db connect class
require_once __DIR__ . '/db_config.php';

// Connecting to mysql database
$con = mysqli_connect(DB_SERVER, DB_USER, DB_PASSWORD,DB_DATABASE);

// check for post data
if (isset($_GET["pid"])) {
    $pid = $_GET['pid'];

    // get a book from books table
    $result = mysqli_query($con,"SELECT *FROM books WHERE pid = $pid");

    if (!empty($result)) {
        // check for empty result
        if (mysqli_num_rows($result) > 0) {

            $result = mysqli_fetch_array($result);

            $book = array();
            $book["pid"] = $row["pid"];
            $book["name"] = $row["name"];
            $book["author"] = $row["author"];
            $book["price"] = $row["price"];
            $book["linkAvatar"] = $row["linkAvatar"];
            $book["created_at"] = $row["created_at"];
            $book["updated_at"] = $row["updated_at"];
            $book["description"] = $row["description"];
            // success
            $response["success"] = 1;

            // user node
            $response["book"] = array();

            array_push($response["book"], $book);

            // echoing JSON response
            echo json_encode($response);
        } else {
            // no product found
            $response["success"] = 0;
            $response["message"] = "Xin lỗi, không tìm thấy sản phẩm";

            // echo no users JSON
            echo json_encode($response);
        }
    } else {
        // no product found
        $response["success"] = 0;
        $response["message"] = "Xin lỗi, không tìm thấy sản phẩm";

        // echo no users JSON
        echo json_encode($response);
    }
} else {
    // required field is missing
    $response["success"] = 0;
    $response["message"] = "Trường dữ liệu thiếu";

    // echoing JSON response
    echo json_encode($response);
}
?>
