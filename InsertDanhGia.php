<?php
// Kết nối tới cơ sở dữ liệu
include "connect.php";

// Lấy dữ liệu từ POST request
$book_id = $_POST['book_id'];
$user_id = $_POST['user_id'];
$rating = $_POST['rating'];
$mota = $_POST['mota'];

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

// Kiểm tra xem người dùng đã bình luận sách này chưa
$query_check = "SELECT * FROM `danhgia` WHERE `book_id` = $book_id AND `user_id` = $user_id";
$data_check = mysqli_query($conn, $query_check);
$existing_favorite = mysqli_fetch_assoc($data_check);

if ($existing_favorite) {
    // Nếu sách đã có trong danh sách yêu thích, trả về thông báo
    $arr = [
        'success' => false,
        'message' => "Bạn đã hết lượt bình luận cho cuốn sách này",
        'result' => []
    ];
    print_r(json_encode($arr));
    exit;
}

// Thêm sách vào bảng yêu thích
$mota_escaped = mysqli_real_escape_string($conn, $mota);
$query_insert = "INSERT INTO `danhgia` (`book_id`, `user_id`, `rating`, `mota`) VALUES ($book_id, $user_id, $rating, N'$mota_escaped')";
$result_insert = mysqli_query($conn, $query_insert);

if ($result_insert) {
    $arr = [
        'success' => true,
        'message' => "Bình luận thành công",
        'result' => [
            'book_id' => $book_id,
            'user_id' => $user_id
        ]
    ];
} else {
    $arr = [
        'success' => false,
        'message' => "Bình luận thất bại",
        'result' => []
    ];
}

// Trả về kết quả dưới dạng JSON
print_r(json_encode($arr));

?>