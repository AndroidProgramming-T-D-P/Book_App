<?php
include "connect.php";

$book_id = $_POST['book_id'];

//check data
$query = "SELECT dgia.*, nd.userName FROM `danhgia` as dgia, `nguoidung` as nd 
            WHERE dgia.`user_id` = nd.`user_id` and dgia.book_id = $book_id";
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
