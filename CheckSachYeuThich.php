<?php
// Kết nối tới cơ sở dữ liệu
include "connect.php";

$book_id = $_POST['book_id'];
$user_id = $_POST['user_id'];

$query_check = "SELECT * FROM `sachyeuthich` WHERE `book_id` = $book_id AND `user_id` = $user_id";
$data_check = mysqli_query($conn, $query_check);
$favorite_book = mysqli_fetch_assoc($data_check);

if ($favorite_book) {
    $arr = [
        'success' => true,
        'message' => "Cuốn sách đã có trong danh sách yêu thích",
        'result' => true
    ];
} else {
    $arr = [
        'success' => true,
        'message' => "Cuốn sách chưa có trong danh sách yêu thích",
        'result' => false
    ];
}

echo json_encode($arr);

?>