<?php
//http://localhost:8686/lab5/create_product.php?name=nam&price=10&created_at=2020-07-28%2008:13:56&description=mo ta

/*
 * Following code will create a new product row
 * All product details are read from HTTP Post Request
 */

// array for JSON response
$response = array();

// check for required fields
if (isset($_POST['name']) && isset($_POST['author']) && isset($_POST['price']) 
    && isset($_POST['description']) && isset($_POST['linkAvatar'])) {

    $name = $_POST['name'];
    $author = $_POST['author'];
    $price = $_POST['price'];
	$description = $_POST['description'];
    $linkAvatar = $_POST['linkAvatar'];

    // include db connect class
	require_once __DIR__ . '/db_config.php';

	// Connecting to mysql database
	$con = mysqli_connect(DB_SERVER, DB_USER, DB_PASSWORD,DB_DATABASE);

    // mysql inserting a new row
    $result = mysqli_query($con,"INSERT INTO books(name, author, price, description, linkAvatar) VALUES('$name', '$author', '$price', '$description', '$linkAvatar')");

    // check if row inserted or not
    if ($result) {
        // successfully inserted into database
        $response["success"] = 1;
        $response["message"] = "Thêm sản phẩm thành công";

        // echoing JSON response
        echo json_encode($response);
    } else {
        // failed to insert row
        $response["success"] = 0;
        $response["message"] = "Thất bại, thêm vào database lỗi";

        // echoing JSON response
        echo json_encode($response);
    }
} else {
    // required field is missing
    $response["success"] = 0;
    $response["message"] = "Trường dữ liệu thiếu. Thêm thất bại";

    // echoing JSON response
    echo json_encode($response);
}
?>
