<?php
include "connect.php";

// Sửa query để chỉ lấy các sách được đề cử
$query = "SELECT * FROM books WHERE is_featured = 1";
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
