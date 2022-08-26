<?php

/*
 * Following code will delete a product from table
 * A product is identified by product id (pid)
 */

// array for JSON response
$response = array();

// check for required fields
if (isset($_POST['pid'])) {
    $pid = $_POST['pid'];

    // include db connect class
	require_once __DIR__ . '/db_config.php';

	// Connecting to mysql database
	$con = mysqli_connect(DB_SERVER, DB_USER, DB_PASSWORD,DB_DATABASE);

    // mysql update row with matched pid
    $result = mysqli_query($con,"DELETE FROM books WHERE pid = $pid");

    // check if row deleted or not
    if (mysqli_affected_rows($con) > 0) {
        // successfully updated
        $response["success"] = 1;
        $response["message"] = "Xóa sản phẩm thành công";

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
    // required field is missing
    $response["success"] = 0;
    $response["message"] = "Trường dữ liệu thiếu. Xóa thất bại";

    // echoing JSON response
    echo json_encode($response);
}
?>
