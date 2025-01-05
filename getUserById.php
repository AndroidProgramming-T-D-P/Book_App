<?php
include "connect.php";

$user_id = $_POST['user_id'];

//check data
$query = "SELECT * FROM `nguoidung` WHERE `user_id` = $user_id";
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
