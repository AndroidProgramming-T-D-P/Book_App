<?php
include "connect.php";

// Lấy ngày hiện tại
$currentDate = date('Y-m-d H:i:s');

// Tính ngày cách đây 10 ngày
$tenDaysAgo = date('Y-m-d H:i:s', strtotime('-10 days'));

// Sửa query để thêm điều kiện lọc
$query = "SELECT * FROM books WHERE created_at BETWEEN '$tenDaysAgo' AND '$currentDate'";
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
