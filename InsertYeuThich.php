<?php
// Kết nối tới cơ sở dữ liệu
include "connect.php";

// Lấy dữ liệu từ POST request
$book_id = $_POST['book_id'];
$user_id = $_POST['user_id'];

// Kiểm tra xem dữ liệu có hợp lệ không
if (empty($book_id) || empty($user_id)) {
    $arr = [
        'success' => false,
        'message' => "book_id và user_id là bắt buộc",
        'result' => []
    ];
    print_r(json_encode($arr));
    exit;
}

// Kiểm tra xem sách có tồn tại trong bảng `books` không
$query = "SELECT * FROM `books` WHERE `book_id` = $book_id";
$data = mysqli_query($conn, $query);
$book = mysqli_fetch_assoc($data);

if (!$book) {
    $arr = [
        'success' => false,
        'message' => "Sách không tồn tại",
        'result' => []
    ];
    print_r(json_encode($arr));
    exit;
}

// Kiểm tra xem người dùng đã yêu thích sách này chưa
$query_check = "SELECT * FROM `sachyeuthich` WHERE `book_id` = $book_id AND `user_id` = $user_id";
$data_check = mysqli_query($conn, $query_check);
$existing_favorite = mysqli_fetch_assoc($data_check);

if ($existing_favorite) {
    // Nếu sách đã có trong danh sách yêu thích, trả về thông báo
    $arr = [
        'success' => false,
        'message' => "Sách đã có trong danh sách yêu thích",
        'result' => []
    ];
    print_r(json_encode($arr));
    exit;
}

// Thêm sách vào bảng yêu thích
$query_insert = "INSERT INTO `sachyeuthich` (`book_id`, `user_id`) VALUES ($book_id, $user_id)";
$result_insert = mysqli_query($conn, $query_insert);

if ($result_insert) {
    $arr = [
        'success' => true,
        'message' => "Sách đã được thêm vào yêu thích",
        'result' => [
            'book_id' => $book_id,
            'user_id' => $user_id
        ]
    ];
} else {
    $arr = [
        'success' => false,
        'message' => "Đã có lỗi khi thêm sách vào yêu thích",
        'result' => []
    ];
}

// Trả về kết quả dưới dạng JSON
print_r(json_encode($arr));

?>