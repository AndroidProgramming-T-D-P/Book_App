<?php
include "connect.php";
$userName = $_POST['userName'];
$user_id = $_POST['user_id'];

$result = array();

if (!empty($userName) && !empty($user_id)) {
    $query = "UPDATE `nguoidung` SET `userName` = '$userName' WHERE `user_id` = $user_id";
    $data = mysqli_query($conn, $query);

    if ($data) {
        $arr = [
            'success' => true,
            'message' => "Sửa thành công",
            'result' => $result
        ];
    } else {
        $arr = [
            'success' => false,
            'message' => "Không thành công",
            'result' => $result
        ];
    }
} else {
    $arr = [
        'success' => false,
        'message' => "Dữ liệu không hợp lệ",
        'result' => $result
    ];
}

print_r(json_encode($arr));
?>
