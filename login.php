<?php
include "connect.php";
$email = $_POST['email'];
$userPassWord = $_POST['userPassWord'];

//check data
$query = 'SELECT * FROM `nguoidung` WHERE `email`="'.$email.'" AND `userPassWord`="'.$userPassWord.'"';
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
