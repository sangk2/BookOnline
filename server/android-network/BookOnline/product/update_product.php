
<?php

/*
 * Following code will update a product information
 * A product is identified by product id (pid)
 */

// array for JSON response
$response = array();

// check for required fields
if (isset($_POST['pid']) && isset($_POST['name']) && isset($_POST['author']) && isset($_POST['price']) && isset($_POST['description']) && isset($_POST['linkAvatar'])) {

    $pid = $_POST['pid'];
    $name = $_POST['name'];
    $author = $_POST['author'];
    $price = $_POST['price'];
    $description = $_POST['description'];
    $linkAvatar = $_POST['linkAvatar'];

	// include db connect class
	require_once __DIR__ . '/db_config.php';

	// Connecting to mysql database
	$con = mysqli_connect(DB_SERVER, DB_USER, DB_PASSWORD,DB_DATABASE);

    // mysql update row with matched pid
    $result = mysqli_query($con,"UPDATE books SET name = '$name', author = '$author', price = '$price', description = '$description', linkAvatar = '$linkAvatar', updated_at= current_timestamp() WHERE pid = $pid");

    // check if row inserted or not
    if ($result) {
        // successfully updated
        $response["success"] = 1;
        $response["message"] = "Cập nhật sản phẩm thành công";

        // echoing JSON response
        echo json_encode($response);
    } else {

    }
} else {
    // required field is missing
    $response["success"] = 0;
    $response["message"] = "Trường dữ liệu thiếu. Cập nhật thất bại";

    // echoing JSON response
    echo json_encode($response);
}
?>
