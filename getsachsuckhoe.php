<?php
include "connect.php";

// Sửa query để lấy các sách thuộc chủ đề Sức khoẻ
$query = "SELECT * FROM books WHERE category_id = 8";
$data = mysqli_query($conn, $query);
$result = array();

while ($row = mysqli_fetch_assoc($data)) {
    $result[] = ($row);
}

if (!empty($result)) {
    $arr = [
        'success' => true,
        'message' => "thanh cong",
        'result' => $result
    ];
} else {
    $arr = [
        'success' => false,
        'message' => "khong thanh cong",
        'result' => $result
    ];
}

print_r(json_encode($arr));

?>
