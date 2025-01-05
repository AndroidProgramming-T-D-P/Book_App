<?php
include "connect.php";

$category_name = $_POST['category_name'];

//check data
$query = 'SELECT * FROM `categories` WHERE `category_name` = "'.$category_name.'"';
$data = mysqli_query($conn, $query);
$result = array();

while ($row = mysqli_fetch_assoc($data)) {
    $result[] = ($row);
    //code o day
}

if (!empty($result)) {
    $arr = [
        'success' => true,
        'message' => "Thanh cong",
        'result' => $result
    ];
} else {
    $arr = [
        'success' => false,
        'message' => "Khong thanh cong",
        'result' => $result 
    ];
}

print_r(json_encode($arr));
?>
